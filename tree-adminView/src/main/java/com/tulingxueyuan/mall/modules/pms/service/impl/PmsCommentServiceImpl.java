package com.tulingxueyuan.mall.modules.pms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.home.service.UmsMemberService;
import com.tulingxueyuan.mall.modules.pms.model.PmsComment;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsCommentMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsCommentReplay;
import com.tulingxueyuan.mall.modules.pms.service.PmsCommentReplayService;
import com.tulingxueyuan.mall.modules.pms.service.PmsCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.ums.model.UmsAdmin;
import com.tulingxueyuan.mall.modules.ums.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品评价表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
@Service
public class PmsCommentServiceImpl extends ServiceImpl<PmsCommentMapper, PmsComment> implements PmsCommentService {
    @Autowired
    UmsAdminService adminService;
    @Autowired
    PmsCommentReplayService commentReplayService;

    @Override
    public Boolean sendComment(PmsComment comment) {
        comment.setShowStatus(0);
        comment.setCreateTime(new Date());
        comment.setSort(100);
        UmsAdmin admin = adminService.getAdmin();
        comment.setMemberIp(admin.getId().toString());
        comment.setMemberIcon(admin.getIcon());
        comment.setMemberNickName(admin.getNickName());
        comment.setReplayCount(0);
        return save(comment);
    }

    @Override
    public Page<PmsComment> fetchList(PmsComment comment) {
        Page<PmsComment> iPage = new Page<>(comment.getPageNum(), comment.getPageSize());
        QueryWrapper<PmsComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsComment::getProductId, comment.getProductId())
                .like(StrUtil.isNotBlank(comment.getMemberNickName()), PmsComment::getMemberNickName, comment.getMemberNickName())
                .eq(comment.getShowStatus() != null,PmsComment::getShowStatus, comment.getShowStatus())
                .orderByDesc(PmsComment::getSort,PmsComment::getCreateTime);
        return page(iPage, queryWrapper);
    }

    @Override
    public Boolean updateStatus(List<Long> ids, Integer showStatus) {
        UpdateWrapper<PmsComment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(PmsComment::getShowStatus, showStatus)
                .in(PmsComment::getId, ids);
        return update(updateWrapper);
    }

    @Override
    @Transactional
    public Boolean deleteReply(List<Long> ids) {
        QueryWrapper<PmsCommentReplay> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(PmsCommentReplay::getCommentId, ids);
        commentReplayService.remove(queryWrapper);
        return removeByIds(ids);
    }

    @Override
    public Boolean updateSort(Long id, Integer sort) {
        UpdateWrapper<PmsComment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(PmsComment::getSort, sort)
                .eq(PmsComment::getId, id);
        return update(updateWrapper);
    }

    @Override
    public Page<PmsCommentReplay> getCommentReply(Long id,  PmsCommentReplay commentReplay) {
        Page<PmsCommentReplay> iPage = new Page<>(commentReplay.getPageNum(), commentReplay.getPageSize());
        QueryWrapper<PmsCommentReplay> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsCommentReplay::getCommentId, id)
                .like(StrUtil.isNotBlank(commentReplay.getMemberNickName()),
                        PmsCommentReplay::getMemberNickName, commentReplay.getMemberNickName())
                .eq(commentReplay.getType()!=null,PmsCommentReplay::getType, commentReplay.getType())
                .orderByDesc(PmsCommentReplay::getCreateTime);
        return commentReplayService.page(iPage, queryWrapper);
    }

    @Override
    @Transactional
    public Boolean deleteReplyIn(List<Long> ids) {
        UpdateWrapper<PmsComment> updateWrapper = new UpdateWrapper<>();
        ids.stream().map(o->{
            PmsCommentReplay replay = commentReplayService.getById(o);
            Long commentId = replay.getCommentId();
            updateWrapper.setSql("`replay_count` = `replay_count` - 1").lambda().eq(PmsComment::getId, commentId);
            update(updateWrapper);
            commentReplayService.removeById(o);
            return o;
        }).collect(Collectors.toList());
        return true;
    }
    @Override
    @Transactional
    public Boolean sendCommentReply(PmsCommentReplay comment) {
        comment.setCreateTime(new Date());
        UmsAdmin admin = adminService.getAdmin();
        comment.setMemberIcon(admin.getIcon());
        comment.setMemberNickName(admin.getNickName());
        comment.setType(0);
        UpdateWrapper<PmsComment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("`replay_count` = `replay_count` + 1").lambda().eq(PmsComment::getId, comment.getCommentId());
        update(updateWrapper);
        return commentReplayService.save(comment);
    }

    @Override
    public Double getScore(Long productId) {
        QueryWrapper<PmsComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("AVG(`star`) score").lambda().eq(PmsComment::getProductId, productId);
        Map<String, Object> map = this.getMap(queryWrapper);
        if (CollUtil.isEmpty(map)){
            return 0d;
        }
        Object score = map.get("score");
        return Double.parseDouble(score.toString());
    }
}
