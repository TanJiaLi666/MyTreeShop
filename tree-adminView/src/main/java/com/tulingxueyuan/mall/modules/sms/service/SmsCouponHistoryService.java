package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsCoupon;
import com.tulingxueyuan.mall.modules.sms.model.SmsCouponHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.sms.model.dto.CouponHistoryDTO;

/**
 * <p>
 * 优惠券使用、领取历史表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
public interface SmsCouponHistoryService extends IService<SmsCouponHistory> {

    Page<SmsCouponHistory> fetchList(CouponHistoryDTO historyDTO);
}
