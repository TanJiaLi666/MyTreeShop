package com.tulingxueyuan.mall.controller;


import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.dto.ProductDetailDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class ProductController {
    @Autowired
    PmsProductService productService;

    @ApiOperation("加载商品信息")
    @GetMapping("/detail/{id}")
    public CommonResult<ProductDetailDTO> fetchList(@PathVariable("id") Long id) {
        ProductDetailDTO productInfo = productService.fetchList(id);
        return CommonResult.success(productInfo, "返回成功");
    }
}