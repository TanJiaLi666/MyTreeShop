package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
public interface PmsProductCategoryService extends IService<PmsProductCategory> {


    /**
     * 查询商品分类列表
     * @param parentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page loadingCategory(Long parentId, Integer pageNum, Integer pageSize);

    /**
     * 删除商品分类
     * @param id
     * @return
     */
    Boolean deleteCategory(Long id);

    /**
     * 展示分类至导航栏
     * @param id
     * @param navStatus
     * @return
     */
    Boolean updatenavstatus(List<Long> id,Integer navStatus);

    /**
     * 是否显示分类
     * @param id
     * @param showStatus
     * @return
     */
    Boolean updateshowstatus(List<Long> id,Integer showStatus);

    /**
     * 添加商品分类
     * @param productCategory
     * @return
     */
    Boolean creatCategory(PmsProductCategory productCategory);

    /**
     * id获取商品分类
     * @param id
     * @return
     */
    PmsProductCategory getCategory(Long id);

    /**
     * 编辑商品分类
     * @param productCategory
     * @return
     */
    Boolean updateCategory(PmsProductCategory productCategory);
}
