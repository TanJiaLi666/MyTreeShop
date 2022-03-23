package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsCoupon;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.sms.model.dto.CouponDTO;

/**
 * <p>
 * 优惠卷表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
public interface SmsCouponService extends IService<SmsCoupon> {

    Page<SmsCoupon> fetchList(CouponDTO couponDTO);

    boolean createCoupon(CouponDTO couponDTO);

    CouponDTO getCoupon(Long id);

    boolean updateCoupon(CouponDTO couponDTO);

    boolean deleteCoupon(Long id);
}
