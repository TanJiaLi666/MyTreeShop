package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsHomeRecommendProductMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeBrand;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendProduct;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendSubject;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeRecommendProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Page<SmsHomeRecommendProduct> fetchList(SmsHomeRecommendProduct recommendProduct) {
        Page<SmsHomeRecommendProduct> page = new Page<>(recommendProduct.getPageNum(), recommendProduct.getPageSize());
        QueryWrapper<SmsHomeRecommendProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(recommendProduct.getRecommendStatus()!=null, SmsHomeRecommendProduct::getRecommendStatus, recommendProduct.getRecommendStatus())
                .like(!StringUtils.isEmpty(recommendProduct.getProductName()), SmsHomeRecommendProduct::getProductName, recommendProduct.getProductName())
                .orderByAsc(SmsHomeRecommendProduct::getSort);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        UpdateWrapper<SmsHomeRecommendProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .in(SmsHomeRecommendProduct::getId, ids)
                .set(SmsHomeRecommendProduct::getRecommendStatus, recommendStatus);
        return this.update(updateWrapper);
    }

    @Override
    public Boolean deleteHotProduct(List<Long> ids) {
        return this.removeByIds(ids);
    }

    @Override
    public boolean createHotProduct(List<SmsHomeRecommendProduct> recommendProducts) {
        List<Long> list = recommendProducts.stream().map(o -> o.getProductId()).collect(Collectors.toList());
        QueryWrapper<SmsHomeRecommendProduct> queryWrapper = new QueryWrapper<>();
        List<Long> ignoreById = new ArrayList<>();
        for (Long productId : list) {
            queryWrapper.lambda()
                    .select(SmsHomeRecommendProduct::getId)
                    .eq(SmsHomeRecommendProduct::getProductId, productId);
            if (list(queryWrapper)!=null) {
                for (SmsHomeRecommendProduct recommendProduct : list(queryWrapper)) {
                    ignoreById.add(recommendProduct.getId());
                }
            }
        }
        if (!CollectionUtils.isEmpty(ignoreById)) {
            deleteHotProduct(ignoreById);
        }
        recommendProducts = recommendProducts.stream().map(o->{
            o.setRecommendStatus(0);
            o.setSort(0);
            return o;
        }).distinct().collect(Collectors.toList());
        return this.saveBatch(recommendProducts);
    }

    @Override
    public boolean updateHotProductSort(Long id, Integer sort) {
        UpdateWrapper<SmsHomeRecommendProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .eq(SmsHomeRecommendProduct::getId , id)
                .set(SmsHomeRecommendProduct::getSort, sort);
        return this.update(updateWrapper);
    }
}
