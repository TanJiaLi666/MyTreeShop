package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductAttributeCateDTO;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
public interface PmsProductAttributeCategoryService extends IService<PmsProductAttributeCategory> {

    /**
     * 加载产品
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page loadingCateAttribute(Integer pageNum, Integer pageSize);

    /**
     * 添加商品属性分类
     * @param productAttributeCategory
     * @return
     */
    Boolean createProductAttrCate(PmsProductAttributeCategory productAttributeCategory);

    /**
     * 删除商品属性分类
     * @param id
     * @return
     */
    Boolean deleteProductAttrCate(Long id);

    /**
     * 修改商品属性分类
     * @param productAttributeCategory
     * @return
     */
    Boolean updateProductAttrCate(PmsProductAttributeCategory productAttributeCategory);

    /**
     * 商品属性筛选
     * @return
     */
    List<PmsProductAttributeCateDTO> fetchListWithAttr();
}
