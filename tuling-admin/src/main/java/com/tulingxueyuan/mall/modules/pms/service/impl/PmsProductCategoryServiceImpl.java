package com.tulingxueyuan.mall.modules.pms.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductCategoryMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategoryAttributeRelation;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCateDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryAttributeRelationService;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryMapper, PmsProductCategory> implements PmsProductCategoryService {

    @Autowired
    private PmsProductCategoryAttributeRelationService productCategoryAttributeRelationService;


    /**
     * 查询商品分类列表
     * @param parentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page loadingCategory(Long parentId, Integer pageNum, Integer pageSize) {
        Page<PmsProductCategory> mypage = new Page<>(pageNum,pageSize);
        QueryWrapper<PmsProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(PmsProductCategory::getParentId,parentId);
        return this.page(mypage,queryWrapper);
    }

    /**
     * 删除商品分类
     * @param id
     * @return
     */
    @Override
    public Boolean deleteCategory(Long id) {
        boolean removeById = this.removeById(id);
        return removeById;
    }

    /**
     * 展示分类至导航栏
     * @param id
     * @param navStatus
     * @return
     */
    @Override
    public Boolean updatenavstatus(List<Long> id, Integer navStatus) {
        UpdateWrapper<PmsProductCategory> updateNavstatus = new UpdateWrapper<>();
        updateNavstatus.lambda()
                .set(PmsProductCategory::getNavStatus,navStatus)
                .in(PmsProductCategory::getId,id);
        return this.update(updateNavstatus);
    }

    /**
     * 是否显示分类
     * @param id
     * @param showStatus
     * @return
     */
    @Override
    public Boolean updateshowstatus(List<Long> id, Integer showStatus) {
        UpdateWrapper<PmsProductCategory> updateShowstatus = new UpdateWrapper<>();
        updateShowstatus.lambda()
                .set(PmsProductCategory::getShowStatus,showStatus)
                .in(PmsProductCategory::getId,id);

        return this.update(updateShowstatus);
    }

    /**
     * 添加商品分类
     * @param productCateDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean creatCategory(PmsProductCateDTO productCateDTO) {

        PmsProductCategory productCategory = new PmsProductCategory();
        BeanUtils.copyProperties(productCateDTO,productCategory);
        Boolean is_save_one = productCateResult(productCategory,false);
        Boolean is_save_two = cateAttrRelation(productCategory, productCateDTO,false);

        return is_save_one||is_save_two;
    }

    /**
     * id获取商品分类
     * @param id
     * @return
     */
    @Override
    public PmsProductCategory getCategory(Long id) {
        return this.getById(id);
    }

    /**
     * 编辑商品分类
     * @param productCateDTO
     * @return
     */
    @Override
    public Boolean updateCategory(PmsProductCateDTO productCateDTO) {
        PmsProductCategory productCategory = new PmsProductCategory();
        BeanUtils.copyProperties(productCateDTO,productCategory);
        Boolean is_save_one = productCateResult(productCategory,true);
        Boolean is_save_two = cateAttrRelation(productCategory, productCateDTO,true);

        return is_save_one||is_save_two;
    }

    /**
     * 获取商品分类名称，一级分类/二级分类
     * @return
     */
    @Override
    public List<PmsProductCateDTO> fetchListWithChildren() {
        List<PmsProductCateDTO> productCateDTOList = this.getBaseMapper().fetchListWithChildren();
        return productCateDTOList;
    }

    /**
     * 商品分类信息处理保存
     * @param productCategory
     * @return
     */
    private  Boolean productCateResult(PmsProductCategory productCategory,Boolean isEdit){
        Boolean is_save_one;
        if (productCategory.getParentId()==0){
            productCategory.setLevel(0);
        }else{
            productCategory.setLevel(1);
        }
        if (isEdit){
            is_save_one = this.updateById(productCategory);
        }else {
            productCategory.setProductCount(0);
            is_save_one = this.save(productCategory);
        }
        return is_save_one;
    }

    /**
     * 商品分类与商品属性绑定操作
     * @param productCategory
     * @param productCateDTO
     * @return
     */
    private Boolean cateAttrRelation(PmsProductCategory productCategory,PmsProductCateDTO productCateDTO,Boolean isEdit){
        Boolean is_save_two;
        List list = new ArrayList();
        List<Long> productAttributeIdList = productCateDTO.getProductAttributeIdList();
        for (Long item : productAttributeIdList){
            PmsProductCategoryAttributeRelation cateAttrRelation = new PmsProductCategoryAttributeRelation();
            cateAttrRelation.setProductAttributeId(item);
            cateAttrRelation.setProductCategoryId(productCategory.getId());
            list.add(cateAttrRelation);
        }
        if (isEdit){
            //删除商品分类对应的属性
            QueryWrapper<PmsProductCategoryAttributeRelation> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                    .eq(PmsProductCategoryAttributeRelation::getProductCategoryId,productCategory.getId());
            productCategoryAttributeRelationService.remove(queryWrapper);
            is_save_two = productCategoryAttributeRelationService.saveBatch(list);
        }else {
            is_save_two = productCategoryAttributeRelationService.saveBatch(list);
        }
        return is_save_two;
    }


}
