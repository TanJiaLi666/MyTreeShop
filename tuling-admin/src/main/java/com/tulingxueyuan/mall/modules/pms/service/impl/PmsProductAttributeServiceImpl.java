package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductAttributeCategoryMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductAttributeMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeCategoryService;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
@Service
public class PmsProductAttributeServiceImpl extends ServiceImpl<PmsProductAttributeMapper, PmsProductAttribute> implements PmsProductAttributeService {

    @Autowired
    PmsProductAttributeMapper productAttributeMapper;

    @Autowired
    PmsProductAttributeCategoryService AttCategoryService;



    @Override
    public Page<PmsProductAttribute> lodingList(Integer cid, Integer pageNum, Integer pageSize, Integer type) {
        Page<PmsProductAttribute> mypage = new Page<>(pageNum,pageSize);
        QueryWrapper<PmsProductAttribute> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(PmsProductAttribute::getProductAttributeCategoryId,cid)
                .eq(PmsProductAttribute::getType,type);
        return this.page(mypage,queryWrapper);
    }

    @Override
    public PmsProductAttribute getProductAttr(Long id) {
        return this.getById(id);
    }

    @Override
    public Boolean updateProductAttr(PmsProductAttribute productAttribute) {
        return this.updateById(productAttribute);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createProductAttr(PmsProductAttribute productAttribute) {
        boolean b = this.save(productAttribute);
        if(b){
            UpdateWrapper<PmsProductAttributeCategory> categoryUpdateWrapper = new UpdateWrapper<>();
            //商品属性--0，商品参数--1
            if (productAttribute.getType() == 0){
                categoryUpdateWrapper.lambda()
                        .setSql("attribute_count = attribute_count+1")
                        .eq(PmsProductAttributeCategory::getId,productAttribute.getProductAttributeCategoryId());
            }else{
                categoryUpdateWrapper.lambda()
                        .setSql("param_count = param_count+1")
                        .eq(PmsProductAttributeCategory::getId,productAttribute.getProductAttributeCategoryId());
            }
            AttCategoryService.update(categoryUpdateWrapper);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteProductAttr(List<Long> ids) {
        if(CollectionUtils.isEmpty(ids)){
            return false;
        }

        //判断删除数据是否有效,获取商品是属性还是参数
        PmsProductAttribute productAttribute = null;
        for (Long id : ids) {
            productAttribute = this.getById(id);
            if (productAttribute != null){
                break;
            }
        }

        //删除商品属性后得到删除数量
        int leagth = productAttributeMapper.deleteBatchIds(ids);
        if (leagth > 0 && productAttribute != null) {
            UpdateWrapper<PmsProductAttributeCategory> categoryUpdateWrapper = new UpdateWrapper<>();
            //商品属性--0，商品参数--1
            if (productAttribute.getType() == 0) {
                categoryUpdateWrapper.lambda()
                        .setSql("attribute_count = attribute_count-"+leagth)
                        .eq(PmsProductAttributeCategory::getId, productAttribute.getProductAttributeCategoryId());
            } else {
                categoryUpdateWrapper.lambda()
                        .setSql("param_count = param_count-"+leagth)
                        .eq(PmsProductAttributeCategory::getId, productAttribute.getProductAttributeCategoryId());
            }
            AttCategoryService.update(categoryUpdateWrapper);
            return true;
        }
        return false;
    }
}
