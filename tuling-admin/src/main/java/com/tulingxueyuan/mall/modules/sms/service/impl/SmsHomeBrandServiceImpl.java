package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeBrand;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsHomeBrandMapper;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 首页推荐品牌表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@Service
public class SmsHomeBrandServiceImpl extends ServiceImpl<SmsHomeBrandMapper, SmsHomeBrand> implements SmsHomeBrandService {

    @Override
    public Page<SmsHomeBrand> fetchList(Integer pageNum, Integer pageSize) {
        Page<SmsHomeBrand> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SmsHomeBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .orderByAsc(SmsHomeBrand::getSort);
        return this.page(page, queryWrapper);
    }
}
