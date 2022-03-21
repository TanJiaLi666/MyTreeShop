package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsCoupon;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeNewProduct;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsHomeNewProductMapper;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeNewProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 新鲜好物表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@Service
public class SmsHomeNewProductServiceImpl extends ServiceImpl<SmsHomeNewProductMapper, SmsHomeNewProduct> implements SmsHomeNewProductService {

    @Override
    public Page<SmsHomeNewProduct> fetchList(Integer pageNum, Integer pageSize) {
        Page<SmsHomeNewProduct> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SmsHomeNewProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .orderByAsc(SmsHomeNewProduct::getSort);
        return this.page(page, queryWrapper);
    }
}
