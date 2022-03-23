package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsCoupon;
import com.tulingxueyuan.mall.modules.sms.model.SmsCouponHistory;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsCouponHistoryMapper;
import com.tulingxueyuan.mall.modules.sms.model.dto.CouponHistoryDTO;
import com.tulingxueyuan.mall.modules.sms.service.SmsCouponHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 优惠券使用、领取历史表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@Service
public class SmsCouponHistoryServiceImpl extends ServiceImpl<SmsCouponHistoryMapper, SmsCouponHistory> implements SmsCouponHistoryService {

    @Override
    public Page<SmsCouponHistory> fetchList(CouponHistoryDTO historyDTO) {
        Page<SmsCouponHistory> page = new Page<>(historyDTO.getPageNum(),historyDTO.getPageSize());
        SmsCouponHistory couponHistory = new SmsCouponHistory();
        BeanUtils.copyProperties(historyDTO, couponHistory);
        QueryWrapper<SmsCouponHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(!StringUtils.isEmpty(historyDTO.getOrderSn()), SmsCouponHistory::getOrderSn, historyDTO.getOrderSn())
                .eq( historyDTO.getCouponId()!=null, SmsCouponHistory::getCouponId, historyDTO.getCouponId())
                .eq(historyDTO.getUseStatus()!=null,SmsCouponHistory::getUseStatus, historyDTO.getUseStatus());
        return this.page(page, queryWrapper);
    }
}
