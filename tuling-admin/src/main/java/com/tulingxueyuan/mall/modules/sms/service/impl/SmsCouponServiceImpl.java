package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsCoupon;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsCouponMapper;
import com.tulingxueyuan.mall.modules.sms.service.SmsCouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠卷表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@Service
public class SmsCouponServiceImpl extends ServiceImpl<SmsCouponMapper, SmsCoupon> implements SmsCouponService {

    @Override
    public Page<SmsCoupon> fetchList(Integer pageNum, Integer pageSize) {
        Page<SmsCoupon> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SmsCoupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .orderByAsc(SmsCoupon::getStartTime);
        return this.page(page, queryWrapper);
    }
}
