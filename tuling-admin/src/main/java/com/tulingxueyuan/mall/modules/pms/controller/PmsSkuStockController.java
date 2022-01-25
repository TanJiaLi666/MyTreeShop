package com.tulingxueyuan.mall.modules.pms.controller;


import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsSkuStock;
import com.tulingxueyuan.mall.modules.pms.service.PmsSkuStockService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * sku的库存 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
@RestController
@RequestMapping("/sku")
public class PmsSkuStockController {

    @Autowired
    PmsSkuStockService skuStockService;

    @ApiOperation("商品sku数据初始化")
    @GetMapping("/{pid}")
    public CommonResult<List<PmsSkuStock>> fetchSkuStockList(@PathVariable("pid") Long pid,
                                                       @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        List<PmsSkuStock> result = skuStockService.fetchSkuStockList(pid, keyword);
        if (result != null) {
            return CommonResult.success(result);
        }
        return CommonResult.failed("无数据");
    }

    @ApiOperation("")
    @PostMapping("/update/{pid}")
    public CommonResult<Boolean> updateSkuStockList(@RequestBody List<PmsSkuStock> skuStockList,
                               @PathVariable("pid") Long productId) {
        Boolean isSuccess = skuStockService.updateSkuStockList(skuStockList,productId);
        if(isSuccess){
            return CommonResult.success(true,"保存成功！");
        }
        return CommonResult.failed("保存失败！");
    }
}

