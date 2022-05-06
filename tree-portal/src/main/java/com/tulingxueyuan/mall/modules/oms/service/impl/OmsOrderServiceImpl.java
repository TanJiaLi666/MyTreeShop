package com.tulingxueyuan.mall.modules.oms.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.common.exception.ApiException;
import com.tulingxueyuan.mall.common.service.RedisService;
import com.tulingxueyuan.mall.common.util.ComConstants;
import com.tulingxueyuan.mall.dto.ConfirmOrderDTO;
import com.tulingxueyuan.mall.dto.OrderDTO;
import com.tulingxueyuan.mall.dto.OrderItemDTO;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsOrderMapper;
import com.tulingxueyuan.mall.modules.oms.model.OmsCartItem;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderItem;
import com.tulingxueyuan.mall.modules.oms.service.OmsCartItemService;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderItemService;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderService;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.PmsSkuStock;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import com.tulingxueyuan.mall.modules.pms.service.PmsSkuStockService;
import com.tulingxueyuan.mall.modules.ums.model.UmsMember;
import com.tulingxueyuan.mall.modules.ums.model.UmsMemberReceiveAddress;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberReceiveAddressService;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    UmsMemberService memberService;
    @Autowired
    UmsMemberReceiveAddressService memberReceiveAddressService;
    @Autowired
    PmsProductService productService;
    @Autowired
    RedisService redisService;
    @Autowired
    OmsOrderItemService orderItemService;
    @Autowired
    PmsSkuStockService skuStockService;
    @Autowired
    OmsOrderService orderService;


    @Override
    public ConfirmOrderDTO fetchList(List<Long> ids) {
        ConfirmOrderDTO confirmOrderDTO =  new ConfirmOrderDTO();
        //todo 查询购物车相应商品信息
        List<OmsCartItem> omsCartItems = cartItemService.listByIds(ids);
        confirmOrderDTO.setCartList(omsCartItems);
        //todo 计算商品金额
        calculate(confirmOrderDTO);
        //todo 地址
        List<UmsMemberReceiveAddress> addressList = memberReceiveAddressService.fetchList();
        confirmOrderDTO.setAddressList(addressList);
        return confirmOrderDTO;
    }

    @Override
    @Transactional
    public Long generateOrder(OrderDTO orderDTO) {
        //购物车ID
        List<Long> itemIds = orderDTO.getItemIds();
        //收货地址ID
        Long addressId = orderDTO.getMemberReceiveAddressId();
        //支付类别
        Integer payType = orderDTO.getPayType();
        //用户信息
        UmsMember umsMember =memberService.getMemberId();
        //todo 判断库存
        List<String> proNameList = new ArrayList<>();
        List<Long> cartIds = new ArrayList<>();
        for (Long itemId : itemIds) {
            String productName = cartItemService.handleStock(itemId);
            if (productName != null) {
                proNameList.add(productName);
            } else {
                cartIds.add(itemId);
            }
        }
        if (CollectionUtil.isNotEmpty(proNameList)) {
            String s = String.join(",", proNameList);
            throw new ApiException("所购商品"+s+"已售空！！");
        }
        //todo 获得购物车商品内容信息
        QueryWrapper<OmsCartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OmsCartItem::getMemberId, umsMember.getId())
                .in(OmsCartItem::getId, cartIds);
        List<OmsCartItem> cartItemList = cartItemService.list(queryWrapper);
        //todo 保存订单表oms_order
        OmsOrder omsOrder = saveOrder(cartItemList, umsMember, payType, addressId);
        save(omsOrder);
        //todo 保存订单详情表
        List<OmsOrderItem> list = saveOrderItem(cartItemList, omsOrder);
        orderItemService.saveBatch(list);
        //todo 锁库存
        lockStock(list);
        //todo 删除购物车
        cartItemService.deleteCar(cartIds);
        return omsOrder.getId();
    }

    @Override
    public OrderItemDTO orderDetail(Long orderId) {
        return this.baseMapper.orderDetail(orderId);
    }

    /**
     * 订单超时处理
     */
    @Override
    public void orderOverTimeHandle() {
        //todo 获取规定的超时时间
        OrderItemDTO orderItemDTO = this.baseMapper.getOverTime();
        Integer overTime = orderItemDTO.getNormalOrderOvertime();
        //查询超时的订单表
        QueryWrapper<OmsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OmsOrder::getStatus, ComConstants.ORDER_STATUS)
                .le(OmsOrder::getCreateTime, DateUtil.offset(new Date(), DateField.MINUTE, -overTime));
        List<OmsOrder> orderList = orderService.list(queryWrapper);
        //todo 关闭订单
        if (CollectionUtil.isEmpty(orderList)) {
            log.info("无超时订单！！");
            return;
        }
        for (OmsOrder order : orderList) {
            order.setStatus(ComConstants.ORDER_STATUS_SHUT);
            order.setModifyTime(new Date());
        }
        this.updateBatchById(orderList);
        //todo 获取订单详情
        List<Long> orderIds = orderList.stream().map(o -> o.getId()).collect(Collectors.toList());
        QueryWrapper<OmsOrderItem> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.lambda().in(OmsOrderItem::getId, orderIds);
        List<OmsOrderItem> orderItemList = orderItemService.list(itemQueryWrapper);
        //todo 归还锁定库存
        orderItemList = orderItemList.stream().map(o->{
            Integer quantity = o.getProductQuantity();
            Long skuId = o.getProductSkuId();
            UpdateWrapper<PmsSkuStock> updateWrapper = new UpdateWrapper<>();
            updateWrapper.setSql("`lock_stock` = `lock_stock` - "+quantity).lambda().eq(PmsSkuStock::getId, skuId);
            skuStockService.update(updateWrapper);
            return o;
        }).collect(Collectors.toList());
    }

    @Override
    public Page<OrderItemDTO> userOrderList(Integer pageNum, Integer pageSize) {
        Page<OrderItemDTO> page = new Page<>(pageNum, pageSize);
        UmsMember member = memberService.getMemberId();
        return this.baseMapper.userOrderList(page, member.getId());
    }

    @Override
    @Transactional
    public Boolean paySuccess(Long orderId, Integer payType) {
        //todo 修改订单状态
        OmsOrder order = new OmsOrder();
        order.setPaymentTime(new Date());
        order.setStatus(1);//代发货状态
        order.setPayType(payType);
        order.setId(orderId);
        boolean update = updateById(order);
        QueryWrapper<OmsOrderItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OmsOrderItem::getOrderId, orderId);
        List<OmsOrderItem> orderItemList = orderItemService.list(queryWrapper);
        //todo 修改库存
        UpdateWrapper<PmsSkuStock> updateWrapper = new UpdateWrapper<>();
        orderItemList.stream().map(o -> {
            Long skuId = o.getProductSkuId();
            updateWrapper.setSql("`stock` = `stock`-"+ o.getProductQuantity())
                    .setSql("`lock_stock` = `lock_stock`-"+ o.getProductQuantity())
                    .lambda()
                    .eq(PmsSkuStock::getId, skuId);
            boolean update1 = skuStockService.update(updateWrapper);
            return o;
        }).collect(Collectors.toList());
        return true;
    }

    /**
     * 锁定库存数目
     * @param list
     */
    private void lockStock(List<OmsOrderItem> list) {
        UpdateWrapper<PmsSkuStock> updateWrapper = new UpdateWrapper<>();
        for (OmsOrderItem omsOrderItem : list) {
            Long skuId = omsOrderItem.getProductSkuId();
            Integer productQuantity = omsOrderItem.getProductQuantity();
            updateWrapper.setSql("`lock_stock` = `lock_stock` +"+productQuantity).lambda()
                    .eq(PmsSkuStock::getId, skuId);
            skuStockService.update(updateWrapper);
        }
    }

    /**
     * 获取订单详情对象信息
     * @param cartItemList
     * @param omsOrder
     * @return
     */
    private List<OmsOrderItem> saveOrderItem(List<OmsCartItem> cartItemList, OmsOrder omsOrder) {
        List<OmsOrderItem> list = new ArrayList<>();
        for (OmsCartItem cartItem : cartItemList) {
            OmsOrderItem omsOrderItem = new OmsOrderItem();
            omsOrderItem.setOrderId(omsOrder.getId());
            omsOrderItem.setOrderSn(omsOrder.getOrderSn());
            omsOrderItem.setProductId(cartItem.getProductId());
            omsOrderItem.setProductPic(cartItem.getProductPic());
            omsOrderItem.setProductName(cartItem.getProductName());
            omsOrderItem.setProductBrand(cartItem.getProductBrand());
            omsOrderItem.setProductSn(cartItem.getProductSn());
            omsOrderItem.setProductPrice(cartItem.getPrice());
            omsOrderItem.setProductQuantity(cartItem.getQuantity());
            omsOrderItem.setProductSkuId(cartItem.getProductSkuId());
            omsOrderItem.setProductSkuCode(cartItem.getProductSkuCode());
            omsOrderItem.setProductCategoryId(cartItem.getProductCategoryId());
            omsOrderItem.setSp1(cartItem.getSp1());
            omsOrderItem.setSp2(cartItem.getSp2());
            omsOrderItem.setSp3(cartItem.getSp3());
            omsOrderItem.setPromotionName(null);
            omsOrderItem.setPromotionAmount(null);
            omsOrderItem.setCouponAmount(null);
            omsOrderItem.setIntegrationAmount(null);
            omsOrderItem.setRealAmount(null);
            omsOrderItem.setGiftGrowth(null);
            omsOrderItem.setGiftIntegration(null);
            omsOrderItem.setProductAttr(null);
            list.add(omsOrderItem);
        }
        return list;
    }

    /**
     * 生成订单对象
     * @param cartItemList
     * @return
     */
    private OmsOrder saveOrder(List<OmsCartItem> cartItemList ,UmsMember umsMember ,Integer payType, Long addressId) {
        OmsOrder omsOrder = new OmsOrder();
        UmsMemberReceiveAddress address = memberReceiveAddressService.getById(addressId);
        //计算金额
        ConfirmOrderDTO confirmOrderDTO = new ConfirmOrderDTO();
        confirmOrderDTO.setCartList(cartItemList);
        confirmOrderDTO = calculate(confirmOrderDTO);

        Date createTime = new Date();
        omsOrder.setMemberId(umsMember.getId());
        omsOrder.setCreateTime(createTime);
        omsOrder.setMemberUsername(umsMember.getUsername());
        omsOrder.setTotalAmount(confirmOrderDTO.getPriceTotal());
        omsOrder.setPayAmount(confirmOrderDTO.getPayAmount());
        omsOrder.setFreightAmount(confirmOrderDTO.getFreightAmount());
        omsOrder.setPayType(payType);
        omsOrder.setStatus(0);
        omsOrder.setSourceType(0);
        omsOrder.setReceiverName(address.getName());
        omsOrder.setReceiverPhone(address.getPhoneNumber());
        omsOrder.setReceiverPostCode(address.getPostCode());
        omsOrder.setReceiverProvince(address.getProvince());
        omsOrder.setReceiverCity(address.getCity());
        omsOrder.setReceiverRegion(address.getRegion());
        omsOrder.setReceiverDetailAddress(address.getDetailAddress());
        omsOrder.setModifyTime(createTime);
        //生成订单编号
        String orderSn = generationOrederSn(createTime, payType);
        omsOrder.setOrderSn(orderSn);
        return omsOrder;
    }

    /**
     * 订单号生成器
     * 8位时间+2位支付类型+4位自增序列
     * @param createTime
     * @param payType
     * @return
     */
    private String generationOrederSn(Date createTime, Integer payType) {
        StringBuilder stringBuilder = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        stringBuilder.append(format.format(createTime));
        stringBuilder.append(String.format("%02d", payType));
        String key = "redis_auto_key" + format.format(createTime);
        Long incr = redisService.incr(key, 1);
        if (incr.toString().length() < 6) {
            stringBuilder.append(String.format("%06d", incr));
        }
        stringBuilder.append(incr);
        return stringBuilder.toString();
    }

    /**
     * 金额计算
     * @param confirmOrderDTO
     * @return
     */
    private ConfirmOrderDTO calculate(ConfirmOrderDTO confirmOrderDTO) {
        List<OmsCartItem> omsCartItems = confirmOrderDTO.getCartList();
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
        return confirmOrderDTO;
    }
}