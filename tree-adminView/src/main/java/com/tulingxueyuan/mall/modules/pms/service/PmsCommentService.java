package com.tulingxueyuan.mall.modules.pms.service;

import com.tulingxueyuan.mall.modules.pms.model.PmsComment;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
