package com.tulingxueyuan.mall.modules.oms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsOrderMapper;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderItem;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderOperateHistory;
import com.tulingxueyuan.mall.modules.oms.model.dto.DefaultListQueryDTO;
import com.tulingxueyuan.mall.modules.oms.model.dto.OmsOrderDeliveryDTO;
import com.tulingxueyuan.mall.modules.oms.model.dto.OmsOrderInfoDTO;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderItemService;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderOperateHistoryService;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    OmsOrderItemService orderItemService;
    @Autowired
    OmsOrderOperateHistoryService historyService;
    @Override
    public Page<OmsOrder> fetchList(DefaultListQueryDTO defaultListQueryDTO) {
        String orderSn = defaultListQueryDTO.getOrderSn();
        String createTime = defaultListQueryDTO.getCreateTime();
        Integer orderType = defaultListQueryDTO.getOrderType();
        String receiverKeyword = defaultListQueryDTO.getReceiverKeyword();
        Integer sourceType = defaultListQueryDTO.getSourceType();
        Integer status = defaultListQueryDTO.getStatus();
        Page<OmsOrder> myPage = new Page<>(defaultListQueryDTO.getPageNum(),defaultListQueryDTO.getPageSize());
        QueryWrapper<OmsOrder> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<OmsOrder> lambda = queryWrapper.lambda();
        lambda.orderByAsc(OmsOrder::getCreateTime)
                .eq(!StringUtils.isEmpty(orderSn),OmsOrder::getOrderSn,orderSn)
                .eq(!StringUtils.isEmpty(orderType),OmsOrder::getOrderType,orderType)
                .eq(!StringUtils.isEmpty(sourceType),OmsOrder::getSourceType,sourceType)
                .eq(!StringUtils.isEmpty(status),OmsOrder::getStatus,status)
                .likeRight(!StringUtils.isEmpty(createTime),OmsOrder::getCreateTime,createTime);
        if (!StringUtils.isEmpty(receiverKeyword)) {
            lambda.eq(OmsOrder::getReceiverName,receiverKeyword)
                    .or()
                    .eq(OmsOrder::getReceiverPhone,receiverKeyword);
        }
        return this.page(myPage,queryWrapper);
    }

    @Override
    public OmsOrderInfoDTO getOrderDetail(Long id) {
        //查询订单表
        OmsOrder order = this.getById(id);
        //查询订单详细信息表
        QueryWrapper<OmsOrderItem> queryItem = new QueryWrapper<>();
        queryItem.lambda().eq(OmsOrderItem::getOrderId,id);
        List<OmsOrderItem> orderItem = orderItemService.list(queryItem);
        //查询订单操作历史记录
        QueryWrapper<OmsOrderOperateHistory> historyWrapper = new QueryWrapper<>();
        historyWrapper.lambda().eq(OmsOrderOperateHistory::getOrderId,id);
        List<OmsOrderOperateHistory> historyList = historyService.list(historyWrapper);
        //存入传输对象
        OmsOrderInfoDTO orderInfoDTO = new OmsOrderInfoDTO();
        BeanUtil.copyProperties(order,orderInfoDTO);
        orderInfoDTO.setOrderItemList(orderItem);
        orderInfoDTO.setHistoryList(historyList);
        return orderInfoDTO;
    }

    @Override
    public Boolean updateOrderNote(Long id, String note, Integer status) {
        UpdateWrapper<OmsOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(OmsOrder::getNote, note)
                .eq(OmsOrder::getId, id)
                .eq(OmsOrder::getStatus, status);
        return this.update(updateWrapper);
    }

    @Override
    public Boolean deleteOrder(List<Long> ids) {
        return this.removeByIds(ids);
    }

    @Override
    public Boolean deliveryOrder(List<OmsOrderDeliveryDTO> orderDeliveryDTOs) {
        orderDeliveryDTOs.forEach(x->{
            String address = x.getAddress();
            String[] strings = address.split("省");
            if (strings.length == 2) {
                x.setReceiverProvince(strings[0]+"省");
                String[] s = strings[1].split("市");
                x.setReceiverCity(s[0]+"市");
                String[] s1 = s[1].split("区");
                x.setReceiverRegion(s1[0]+"区");
                x.setReceiverDetailAddress(s1[1]);
            }else if (strings.length == 1){
                String[] s = strings[0].split("市");
                x.setReceiverProvince(s[0]+"市");
                String[] s1 = s[1].split("区");
                x.setReceiverRegion(s1[0]+"区");
                x.setReceiverDetailAddress(s1[1]);
            }
            UpdateWrapper<OmsOrder> updateWrapper = new UpdateWrapper<>();
            updateWrapper.lambda()
                    .set(OmsOrder::getStatus,2)
                    .set(OmsOrder::getDeliveryCompany,x.getDeliveryCompany())
                    .set(OmsOrder::getDeliverySn,x.getDeliverySn())
                    .set(OmsOrder::getOrderSn,x.getOrderSn())
                    .set(OmsOrder::getReceiverPhone,x.getReceiverPhone())
                    .set(OmsOrder::getReceiverName,x.getReceiverName())
                    .set(OmsOrder::getReceiverPostCode,x.getReceiverPostCode())
                    .set(OmsOrder::getReceiverProvince,x.getReceiverProvince())
                    .set(OmsOrder::getReceiverCity,x.getReceiverCity())
                    .set(OmsOrder::getReceiverRegion,x.getReceiverRegion())
                    .set(OmsOrder::getReceiverDetailAddress,x.getReceiverDetailAddress())
                    .eq(OmsOrder::getId,x.getOrderId());
            this.update(updateWrapper);
        });
        return true;
    }
}