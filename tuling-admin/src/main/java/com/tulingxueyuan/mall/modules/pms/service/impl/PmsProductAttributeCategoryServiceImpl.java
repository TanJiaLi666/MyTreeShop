package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductAttributeCategoryMapper;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductAttributeCateDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
@Service
public class PmsProductAttributeCategoryServiceImpl extends ServiceImpl<PmsProductAttributeCategoryMapper, PmsProductAttributeCategory> implements PmsProductAttributeCategoryService {

    @Autowired
    PmsProductAttributeCategoryMapper AttributeCategoryMapper;


    @Override
    public Page loadingCateAttribute(Integer pageNum, Integer pageSize) {
        Page<PmsProductAttributeCategory> page = new Page<>(pageNum,pageSize);
        return this.page(page);
    }

    @Override
    public Boolean createProductAttrCate(PmsProductAttributeCategory productAttributeCategory) {
        return this.save(productAttributeCategory);
    }

    @Override
    public Boolean deleteProductAttrCate(Long id) {
        return this.removeById(id);
    }

    @Override
    public Boolean updateProductAttrCate(PmsProductAttributeCategory productAttributeCategory) {
        return this.updateById(productAttributeCategory);
    }

    @Override
    public List<PmsProductAttributeCateDTO> fetchListWithAttr() {
        List<PmsProductAttributeCateDTO> dtoList = AttributeCategoryMapper.fetchListWithAttr();
        return dtoList;
    }
}
