package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.dto.CommentDTO;
import com.tulingxueyuan.mall.modules.pms.model.PmsComment;
import com.tulingxueyuan.mall.modules.pms.model.PmsCommentReplay;

import java.util.List;

/**
 * <p>
 * 商品评价表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-04-03
 */
public interface PmsCommentService extends IService<PmsComment> {

    List<CommentDTO> fetchList(Long id);

    Boolean saveComment(PmsComment comment);

    Boolean saveCommentReply(PmsCommentReplay commentReplay);

    Boolean deleteCommentReply(Long id);

    Boolean deleteReCommentReply(Long id);
}
