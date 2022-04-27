package com.tulingxueyuan.mall.modules.pms.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.dto.CommentDTO;
import com.tulingxueyuan.mall.dto.CommentReplyDTO;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsCommentMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsComment;
import com.tulingxueyuan.mall.modules.pms.model.PmsCommentReplay;
import com.tulingxueyuan.mall.modules.pms.service.PmsCommentReplayService;
import com.tulingxueyuan.mall.modules.pms.service.PmsCommentService;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import com.tulingxueyuan.mall.modules.ums.model.UmsMember;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品评价表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-04-03
 */
@Service
public class PmsCommentServiceImpl extends ServiceImpl<PmsCommentMapper, PmsComment> implements PmsCommentService {
    @Autowired
    PmsCommentReplayService commentReplayService;
    @Autowired
    PmsProductService productService;
    @Autowired
    UmsMemberService memberService;

    @Override
    public List<CommentDTO> fetchList(Long id) {
        List<CommentDTO> list = new ArrayList<>();
        QueryWrapper<PmsComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsComment::getProductId, id);
        List<PmsComment> commentList = list(queryWrapper);
        for (PmsComment comment : commentList) {
            CommentDTO dto = new CommentDTO();
            Long commentId = comment.getId();
            QueryWrapper<PmsCommentReplay> replayQueryWrapper = new QueryWrapper<>();
            replayQueryWrapper.lambda().eq(PmsCommentReplay::getCommentId, commentId);
            //查询评论下的所有回复
            List<PmsCommentReplay> replays = commentReplayService.list(replayQueryWrapper);
            List<CommentReplyDTO> replayList = replays.stream().map(o -> {
                CommentReplyDTO replyDTO = new CommentReplyDTO();
                replyDTO.setCommentId(o.getId());
                replyDTO.setParentCommentId(o.getCommentId());
                replyDTO.setCommentContent(o.getContent());
                replyDTO.setToCommentUser(comment.getMemberNickName());
                replyDTO.setUpdatedDate(DateUtil.formatDate(o.getCreateTime()));
                replyDTO.setHeadImg(o.getMemberIcon());
                replyDTO.setCommentUser(o.getMemberNickName());
                replyDTO.setInputShow(false);
                replyDTO.setShow(true);
                return replyDTO;
            }).collect(Collectors.toList());
            //组装
            dto.setCommentId(commentId);
            dto.setCommentUser(comment.getMemberNickName());
            dto.setHeadImg(comment.getMemberIcon());
            dto.setCommentContent(comment.getContent());
            dto.setUpdatedDate(DateUtil.formatDate(comment.getCreateTime()));
            dto.setStar(comment.getStar());
            dto.setShow(comment.getShowStatus() == 0);//0为展示评论
            dto.setInputShow(false);
            dto.setReplyShow(false);
            dto.setReply(replayList);
            list.add(dto);
        }
        return list;
    }

    @Override
    public Boolean saveComment(PmsComment comment) {
        comment.setCreateTime(new Date());
        comment.setShowStatus(0);
        String productName = productService.getProductName(comment.getProductId());
        comment.setProductName(productName);
        UmsMember member = memberService.getMemberId();
        comment.setMemberIp(member.getId().toString());
        return save(comment);
    }

    @Override
    public Boolean saveCommentReply(PmsCommentReplay commentReplay) {
        commentReplay.setCreateTime(new Date());
        commentReplay.setType(0);
        return commentReplayService.save(commentReplay);
    }

    @Override
    @Transactional
    public Boolean deleteCommentReply(Long id) {
        QueryWrapper<PmsCommentReplay> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsCommentReplay::getCommentId, id);
        commentReplayService.remove(queryWrapper);
        return removeById(id);
    }

    @Override
    public Boolean deleteReCommentReply(Long id) {
        return commentReplayService.removeById(id);
    }
}
