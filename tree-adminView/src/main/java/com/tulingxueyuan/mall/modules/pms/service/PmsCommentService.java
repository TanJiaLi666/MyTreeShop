package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.PmsCommentReplay;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 商品评价表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
public interface PmsCommentService extends IService<PmsComment> {

    Boolean sendComment(PmsComment comment);

    Page<PmsComment> fetchList(PmsComment comment);

    Boolean updateStatus(List<Long> ids, Integer showStatus);

    Boolean deleteReply(List<Long> ids);

    Boolean updateSort(Long id, Integer sort);

    Page<PmsCommentReplay> getCommentReply(Long id, PmsCommentReplay commentReplay);

    Boolean deleteReplyIn(List<Long> ids);

    Boolean sendCommentReply(PmsCommentReplay comment);

    Double getScore(Long productId);
}
