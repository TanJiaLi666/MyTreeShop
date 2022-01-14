package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductAttributeCateDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeCategoryService;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 前端控制器（商品类型控制器）
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
@RestController
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {
    @Autowired
    private PmsProductAttributeCategoryService productAttributeCategoryService;


    @ApiOperation("加载产品分类属性(商品类型)列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsProductAttributeCategory>> loadingCateAttribute(@RequestParam(value = "pageNum", defaultValue="1") Integer pageNum,
                                                                                      @RequestParam(value = "pageSize", defaultValue="5") Integer pageSize) {

        Page page = productAttributeCategoryService.loadingCateAttribute(pageNum,pageSize);

        return CommonResult.success(CommonPage.restPage(page)) ;
    }

    @ApiOperation("加载筛选商品属性下拉级联列")
    @GetMapping("/list/withAttr")
    public CommonResult<List<PmsProductAttributeCateDTO>> fetchListWithAttr() {

        List<PmsProductAttributeCateDTO> page = productAttributeCategoryService.fetchListWithAttr();

        return CommonResult.success(page) ;
    }

    @ApiOperation("添加商品属性分类")
    @PostMapping("/create")
    public CommonResult<Boolean> createProductAttrCate(PmsProductAttributeCategory productAttributeCategory){
        Boolean is_success = productAttributeCategoryService.createProductAttrCate(productAttributeCategory);
        if(is_success){
            return CommonResult.success(is_success,"添加成功");
        }
        return CommonResult.failed("失败");
    }

    @ApiOperation("删除商品属性分类")
    @GetMapping("/delete/{id}")
    public CommonResult<Boolean> deleteProductAttrCate(@PathVariable("id") Long id){
        Boolean is_success = productAttributeCategoryService.deleteProductAttrCate(id);
        if(is_success){
            return CommonResult.success(is_success,"删除成功");
        }
        return CommonResult.failed("失败");
    }

    @ApiOperation("编辑商品属性分类")
    @PostMapping("/update/{id}")
    public CommonResult<Boolean> updateProductAttrCate(@PathVariable("id") Long id,
                                                       PmsProductAttributeCategory productAttributeCategory){
        Boolean is_success = productAttributeCategoryService.updateProductAttrCate(productAttributeCategory);
        if(is_success){
            return CommonResult.success(is_success,"修改成功");
        }
        return CommonResult.failed("失败");
    }
}

