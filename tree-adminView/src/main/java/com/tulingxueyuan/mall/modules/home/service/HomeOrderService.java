package com.tulingxueyuan.mall.modules.home.service;

import com.tulingxueyuan.mall.modules.home.dto.AdminDTO;
import com.tulingxueyuan.mall.modules.home.dto.OrderListDTO;
import com.tulingxueyuan.mall.modules.home.dto.ProductDTO;
import com.tulingxueyuan.mall.modules.home.dto.ReturnOrderDTO;

public interface HomeOrderService {

    /**
     * 订单统计
     * @return
     */
    OrderListDTO orderStatistics();

    /**
     * 退货订单统计
     * @return
     */
    ReturnOrderDTO returnOrder();

    /**
     * 商品统计
     * @return
     */
    ProductDTO shopStatistics();

    /**
     * 用户统计
     * @return
     */
    AdminDTO adminStatistics();

    OrderListDTO mapStatistics(String start, String end);
}
