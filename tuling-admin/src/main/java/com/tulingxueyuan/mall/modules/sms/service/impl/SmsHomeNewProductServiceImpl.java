package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsCoupon;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeNewProduct;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsHomeNewProductMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendProduct;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendSubject;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeNewProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Page<SmsHomeNewProduct> fetchList(SmsHomeNewProduct newProduct) {
        Page<SmsHomeNewProduct> page = new Page<>(newProduct.getPageNum(), newProduct.getPageSize());
        QueryWrapper<SmsHomeNewProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(!StringUtils.isEmpty(newProduct.getProductName()), SmsHomeNewProduct::getProductName, newProduct.getProductName())
                .eq(newProduct.getRecommendStatus()!=null, SmsHomeNewProduct::getRecommendStatus, newProduct.getRecommendStatus())
                .orderByAsc(SmsHomeNewProduct::getSort);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean createNewProduct(List<SmsHomeNewProduct> newProduct) {
        List<Long> list = newProduct.stream().map(o -> o.getProductId()).collect(Collectors.toList());
        QueryWrapper<SmsHomeNewProduct> queryWrapper = new QueryWrapper<>();
        List<Long> ignoreById = new ArrayList<>();
        for (Long productId : list) {
            queryWrapper.lambda()
                    .select(SmsHomeNewProduct::getId)
                    .eq(SmsHomeNewProduct::getProductId, productId);
            if (list(queryWrapper)!=null) {
                for (SmsHomeNewProduct product : list(queryWrapper)) {
                    ignoreById.add(product.getId());
                }
            }
        }
        if (!CollectionUtils.isEmpty(ignoreById)) {
            deleteNewProduct(ignoreById);
        }
        newProduct = newProduct.stream().map(o->{
            o.setRecommendStatus(0);
            o.setSort(0);
            return o;
        }).distinct().collect(Collectors.toList());
        return this.saveBatch(newProduct);
    }

    @Override
    public Boolean deleteNewProduct(List<Long> ids) {
        return this.removeByIds(ids);
    }

    @Override
    public boolean updateNewProductSort(Long id, Integer sort) {
        UpdateWrapper<SmsHomeNewProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .eq(SmsHomeNewProduct::getId , id)
                .set(SmsHomeNewProduct::getSort, sort);
        return this.update(updateWrapper);
    }

    @Override
    public boolean updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        UpdateWrapper<SmsHomeNewProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .in(SmsHomeNewProduct::getId, ids)
                .set(SmsHomeNewProduct::getRecommendStatus, recommendStatus);
        return this.update(updateWrapper);
    }
}
