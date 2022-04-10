package com.tulingxueyuan.mall.modules.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.dto.ConfirmOrderDTO;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-25
 */
public interface OmsOrderService extends IService<OmsOrder> {

    /**
     * 查询订单商品信息列表
     * @param ids
     * @return
     */
    ConfirmOrderDTO fetchList(List<Long> ids);
}
