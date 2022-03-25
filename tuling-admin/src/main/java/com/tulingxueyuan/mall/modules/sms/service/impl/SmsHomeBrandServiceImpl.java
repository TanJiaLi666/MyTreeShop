package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.*;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsHomeBrandMapper;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Page<SmsHomeBrand> fetchList(SmsHomeBrand brand) {
        Page<SmsHomeBrand> page = new Page<>(brand.getPageNum(), brand.getPageSize());
        QueryWrapper<SmsHomeBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(brand.getRecommendStatus()!=null, SmsHomeBrand::getRecommendStatus, brand.getRecommendStatus())
                .like(!StringUtils.isEmpty(brand.getBrandName()), SmsHomeBrand::getBrandName, brand.getBrandName())
                .orderByAsc(SmsHomeBrand::getSort);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean createHomeBrand(List<SmsHomeBrand> brands) {
        List<Long> list = brands.stream().map(o -> o.getBrandId()).collect(Collectors.toList());
        QueryWrapper<SmsHomeBrand> queryWrapper = new QueryWrapper<>();
        List<Long> ignoreById = new ArrayList<>();
        for (Long brandId : list) {
            queryWrapper.lambda()
                    .select(SmsHomeBrand::getId)
                    .eq(SmsHomeBrand::getBrandId, brandId);
            if (list(queryWrapper)!=null) {
                for (SmsHomeBrand brand : list(queryWrapper)) {
                    ignoreById.add(brand.getId());
                }
            }
        }
        if (!CollectionUtils.isEmpty(ignoreById)) {
            deleteHomeBrand(ignoreById);
        }
        brands = brands.stream().map(o->{
            o.setSort(0);
            o.setRecommendStatus(0);
            return o;
        }).distinct().collect(Collectors.toList());
        List<Long> brandIds = brands.stream().map(o -> o.getBrandId()).collect(Collectors.toList());
        deleteHomeBrand(brandIds);
        return this.saveBatch(brands);
    }

    @Override
    public boolean deleteHomeBrand(List<Long> ids) {
        return this.removeByIds(ids);
    }

    @Override
    public boolean updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        UpdateWrapper<SmsHomeBrand> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .in(SmsHomeBrand::getId, ids)
                .set(SmsHomeBrand::getRecommendStatus, recommendStatus);
        return this.update(updateWrapper);
    }

    @Override
    public boolean updateHomeBrandSort(Long id, Integer sort) {
        UpdateWrapper<SmsHomeBrand> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .eq(SmsHomeBrand::getId, id)
                .set(SmsHomeBrand::getSort, sort);
        return this.update(updateWrapper);
    }
}
