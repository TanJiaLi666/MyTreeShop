package com.tulingxueyuan.mall.modules.oms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderItem;
import com.tulingxueyuan.mall.modules.sms.model.dto.DataStatisticsDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 * 订单中所包含的商品 Mapper 接口
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-25
 */
public interface OmsOrderItemMapper extends BaseMapper<OmsOrderItem> {

    List<DataStatisticsDTO> getOrderProduct(@Param("productId") Long productId);
}
