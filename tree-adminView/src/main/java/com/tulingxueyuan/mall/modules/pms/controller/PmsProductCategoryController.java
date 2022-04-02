package com.tulingxueyuan.mall.modules.pms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCateDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
@RestController
@RequestMapping("/productCategory")
public class PmsProductCategoryController {
    @Autowired
    PmsProductCategoryService pmsProductCategoryService;

    @ApiOperation("加载产品分类列表")
    @GetMapping("/list/{parentId}")
    public CommonResult<CommonPage<PmsProductCategory>> loadingCategory(@PathVariable("parentId") Long parentId,
                                                               @RequestParam(value = "pageNum", defaultValue="1") Integer pageNum,
                                                               @RequestParam(value = "pageSize", defaultValue="5") Integer pageSize) {

        Page page = pmsProductCategoryService.loadingCategory(parentId,pageNum,pageSize);

        return CommonResult.success(CommonPage.restPage(page)) ;
    }

    @ApiOperation("初始化商品添加页面，商品分类加载")
    @GetMapping("/list/withChildren")
    public CommonResult<List<PmsProductCateDTO>> fetchListWithChildren() {

        List<PmsProductCateDTO> pmsProductCategories = pmsProductCategoryService.fetchListWithChildren();

        return CommonResult.success(pmsProductCategories) ;
    }
    @ApiOperation("通过id获取商品分类对象")
    @GetMapping("/{id}")
    public CommonResult<PmsProductCategory> getCategory(@PathVariable("id") Long id) {

        PmsProductCategory category = pmsProductCategoryService.getCategory(id);
        if(null != category){
            return CommonResult.success(category) ;
        }
        return CommonResult.failed("失败");

    }

    @ApiOperation("添加商品分类")
    @PostMapping("/create")
    public CommonResult<Boolean> creatCategory(@RequestBody PmsProductCateDTO productCateDTO){
        Boolean is_success = pmsProductCategoryService.creatCategory(productCateDTO);
        if(is_success){
            return CommonResult.success(is_success,"保存成功！");
        }
        return CommonResult.failed("保存失败！");

    }

    @ApiOperation("编辑商品分类")
    @PostMapping("/update/{id}")
    public CommonResult<Boolean> updateCategory(@RequestBody PmsProductCateDTO productCateDTO){
        Boolean is_success = pmsProductCategoryService.updateCategory(productCateDTO);
        if(is_success){
            return CommonResult.success(is_success,"保存成功！");
        }
        return CommonResult.failed("保存失败！");

    }

    @ApiOperation("改变分类是否显示")
    @PostMapping("/update/showStatus")
    public CommonResult<Boolean> updateshowstatus(@RequestParam(value = "ids") List<Long> id,
                       @RequestParam(value = "showStatus", defaultValue="0") Integer showStatus){
        Boolean is_succes = pmsProductCategoryService.updateshowstatus(id,showStatus);
        if(is_succes){
            return CommonResult.success(is_succes,"修改成功");
        }
        return CommonResult.failed("失败");
    }

    @ApiOperation("改变分类是否显示在导航栏")
    @PostMapping("/update/navStatus")
    public CommonResult<Boolean> updatenavstatus(@RequestParam(value = "ids") List<Long> id,
                                        @RequestParam(value = "navStatus", defaultValue="0") Integer navStatus){
        Boolean is_succes = pmsProductCategoryService.updatenavstatus(id,navStatus);
        if(is_succes){
            return CommonResult.success(is_succes,"修改成功");
        }
        return CommonResult.failed("失败");
    }

    @ApiOperation("商品分类的删除")
    @PostMapping("/delete/{id}")
    public CommonResult<Boolean> deleteCategory(@PathVariable("id") Long id){
        Boolean is_success = pmsProductCategoryService.deleteCategory(id);
        if (is_success){
            return CommonResult.success(is_success,"成功删除");
        }
        return CommonResult.failed();
    }




}

