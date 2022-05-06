package com.tulingxueyuan.mall.modules.oms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsOrderItemMapper;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderItem;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderItemService;
import com.tulingxueyuan.mall.modules.sms.model.dto.DataStatisticsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单中所包含的商品 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-25
 */
@Service
public class OmsOrderItemServiceImpl extends ServiceImpl<OmsOrderItemMapper, OmsOrderItem> implements OmsOrderItemService {

    @Override
    public List<DataStatisticsDTO> getOrderProduct(Long productId) {
        return this.baseMapper.getOrderProduct(productId);
    }
}
