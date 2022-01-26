package com.tulingxueyuan.mall.modules.oms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsOrderMapper;
import com.tulingxueyuan.mall.modules.oms.model.dto.DefaultListQueryDTO;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.CacheRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

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
}
