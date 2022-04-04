package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.dto.HomeCateDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryService;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-04-03
 */
@Api(tags = "商城首页")
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    PmsProductCategoryService categoryService;

    @ApiOperation("获取首页分类列表")
    @GetMapping("/productCateList")
    public CommonResult<List<HomeCateDTO>> getCateList() {
        List<HomeCateDTO> cateList  = categoryService.getCateList();
        return CommonResult.success(cateList,"加载成功！");
    }
}

