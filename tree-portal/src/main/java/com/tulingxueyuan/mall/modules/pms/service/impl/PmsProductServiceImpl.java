package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductMapper;
import com.tulingxueyuan.mall.dto.ProductDetailDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-04-03
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {

    @Override
    public ProductDetailDTO fetchList( Long id) {
        return this.baseMapper.fetchList(id);
    }

    @Override
    public List<PmsProduct> search(String keyword) {
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(PmsProduct::getName, keyword).or().like(PmsProduct::getBrandName, keyword)
                .or().like(PmsProduct::getProductCategoryName, keyword);
        return this.list(queryWrapper);
    }

    @Override
    public String getProductName(Long productId) {
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().select(PmsProduct::getName).eq(PmsProduct::getId, productId);
        Map<String, Object> map = this.getMap(queryWrapper);
        String name = (String)map.get("name");
        return name;
    }
}
