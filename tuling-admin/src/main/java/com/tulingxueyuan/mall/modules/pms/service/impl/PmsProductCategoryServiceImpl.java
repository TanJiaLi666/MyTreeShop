package com.tulingxueyuan.mall.modules.pms.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductCategoryMapper;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
     * @param productCategory
     * @return
     */
    @Override
    public Boolean creatCategory(PmsProductCategory productCategory) {
        boolean save = this.save(productCategory);
        return save;
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
     * @param productCategory
     * @return
     */
    @Override
    public Boolean updateCategory(PmsProductCategory productCategory) {
        return this.updateById(productCategory);
    }


}
