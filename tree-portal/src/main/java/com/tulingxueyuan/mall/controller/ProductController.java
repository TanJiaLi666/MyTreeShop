package com.tulingxueyuan.mall.controller;


import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.dto.ProductDetailDTO;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
public class ProductController {
    @Autowired
    PmsProductService productService;

    @ApiOperation("加载商品信息")
    @GetMapping("/detail/{id}")
    public CommonResult<ProductDetailDTO> fetchList(@PathVariable("id") Long id) {
        ProductDetailDTO productInfo = productService.fetchList(id);
        return CommonResult.success(productInfo, "返回成功");
    }
    @ApiOperation("加载商品信息")
    @PostMapping("/search/simple")
    public CommonResult<List<PmsProduct>> search(String keyword) {
        List<PmsProduct> product = productService.search(keyword);
        return CommonResult.success(product, "返回成功");
    }
}