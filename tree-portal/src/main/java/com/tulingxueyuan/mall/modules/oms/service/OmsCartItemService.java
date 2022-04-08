package com.tulingxueyuan.mall.modules.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.dto.CartDTO;
import com.tulingxueyuan.mall.modules.oms.model.OmsCartItem;

/**
 * <p>
 * 购物车表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-25
 */
public interface OmsCartItemService extends IService<OmsCartItem> {

    Boolean addCar(CartDTO cartDTO);

    Integer sum();
}
