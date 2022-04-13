package com.tulingxueyuan.mall.modules.oms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.dto.OrderItemDTO;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-25
 */
public interface OmsOrderMapper extends BaseMapper<OmsOrder> {

    OrderItemDTO orderDetail(Long orderId);

    /**
     * 获取超时时间
     * @return
     */
    OrderItemDTO getOverTime();

    /**
     * 获取订单列表
     * @param page
     * @return
     */
    Page<OrderItemDTO> userOrderList(IPage<?> page, @Param(value = "id") Long id);
}
