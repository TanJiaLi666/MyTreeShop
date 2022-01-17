package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
@RestController
@RequestMapping("/product")
public class PmsProductController {
    @Autowired
    PmsProductService productService;

    @ApiOperation("加载商品列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsProduct>> fetchList(@RequestParam(value = "pageNum", defaultValue="1") Integer pageNum,
                                                          @RequestParam(value = "pageSize", defaultValue="10") Integer pageSize) {
        Page page = productService.fetchList(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(page)) ;
    }
}

