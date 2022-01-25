package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsSkuStock;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsSkuStockMapper;
import com.tulingxueyuan.mall.modules.pms.service.PmsSkuStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * sku的库存 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
@Service
public class PmsSkuStockServiceImpl extends ServiceImpl<PmsSkuStockMapper, PmsSkuStock> implements PmsSkuStockService {

    @Override
    public List<PmsSkuStock> fetchSkuStockList(Long pid, String keyword) {
        QueryWrapper<PmsSkuStock> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(PmsSkuStock::getProductId, pid);
        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper.lambda().eq(PmsSkuStock::getSkuCode,keyword);
        }
        return this.list(queryWrapper);
    }

    @Override
    public Boolean updateSkuStockList(List<PmsSkuStock> skuStockList, Long productId) {
        UpdateWrapper<PmsSkuStock> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .eq(PmsSkuStock::getProductId,productId);
        for (PmsSkuStock skuStock: skuStockList) {
            this.update(skuStock, updateWrapper);
        }
        return true;
    }
}
