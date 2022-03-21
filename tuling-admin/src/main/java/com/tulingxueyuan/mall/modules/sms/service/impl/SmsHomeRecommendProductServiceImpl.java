package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendProduct;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsHomeRecommendProductMapper;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeRecommendProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 人气推荐商品表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@Service
public class SmsHomeRecommendProductServiceImpl extends ServiceImpl<SmsHomeRecommendProductMapper, SmsHomeRecommendProduct> implements SmsHomeRecommendProductService {

    @Override
    public Page<SmsHomeRecommendProduct> fetchList(Integer pageNum, Integer pageSize) {
        Page<SmsHomeRecommendProduct> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SmsHomeRecommendProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .orderByAsc(SmsHomeRecommendProduct::getSort);
        return this.page(page, queryWrapper);
    }
}
