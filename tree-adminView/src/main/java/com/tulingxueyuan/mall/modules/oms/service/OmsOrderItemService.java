package com.tulingxueyuan.mall.modules.oms.service;

import com.tulingxueyuan.mall.modules.oms.model.OmsOrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.sms.model.dto.DataStatisticsDTO;

import java.util.List;

/**
 * <p>
 * 订单中所包含的商品 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-25
 */
public interface OmsOrderItemService extends IService<OmsOrderItem> {

    List<DataStatisticsDTO> getOrderProduct(Long productId);
}
