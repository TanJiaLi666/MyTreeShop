package com.tulingxueyuan.mall.modules.oms.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.dto.ConfirmOrderDTO;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsOrderMapper;
import com.tulingxueyuan.mall.modules.oms.model.OmsCartItem;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.tulingxueyuan.mall.modules.oms.service.OmsCartItemService;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderService;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import com.tulingxueyuan.mall.modules.ums.model.UmsMember;
import com.tulingxueyuan.mall.modules.ums.model.UmsMemberReceiveAddress;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberReceiveAddressService;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberService;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-25
 */
@Slf4j
@Service
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements OmsOrderService {
    @Autowired
    OmsCartItemService cartItemService;
    @Autowired
    UmsMemberReceiveAddressService memberReceiveAddressService;
    @Autowired
    PmsProductService productService;

    @Override
    public ConfirmOrderDTO fetchList(List<Long> ids) {
        ConfirmOrderDTO confirmOrderDTO =  new ConfirmOrderDTO();
        //todo 查询购物车相应商品信息
        List<OmsCartItem> omsCartItems = cartItemService.listByIds(ids);
        confirmOrderDTO.setCartList(omsCartItems);
        //todo 计算商品金额
        BigDecimal priceTotal = new BigDecimal(0);
        BigDecimal freightAmount = new BigDecimal(0);
        BigDecimal payAmount = new BigDecimal(0);
        Integer productTotal = 0;
        for (OmsCartItem omsCartItem : omsCartItems) {

            PmsProduct product = productService.getById(omsCartItem.getProductId());
            String serviceIds = product.getServiceIds();
            String[] split = serviceIds.split(",");
            if (split.length>0) {
                if (ArrayUtil.contains(split,"3")) {
                    //暂时默认10元运费，每单商品
                    freightAmount = freightAmount.add(new BigDecimal(10));
                }
            }
            productTotal += omsCartItem.getQuantity();
            priceTotal = priceTotal.add(omsCartItem.getPrice().multiply(new BigDecimal(omsCartItem.getQuantity())));
        }
        payAmount = priceTotal.add(freightAmount);
        confirmOrderDTO.setFreightAmount(freightAmount);
        confirmOrderDTO.setProductTotal(productTotal);
        confirmOrderDTO.setPriceTotal(priceTotal);
        confirmOrderDTO.setPayAmount(payAmount);
        //todo 地址
        List<UmsMemberReceiveAddress> addressList = memberReceiveAddressService.fetchList();
        confirmOrderDTO.setAddressList(addressList);
        return confirmOrderDTO;
    }
}