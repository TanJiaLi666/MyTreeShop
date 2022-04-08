package com.tulingxueyuan.mall.controller;

import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.common.api.ResultCode;
import com.tulingxueyuan.mall.dto.CartDTO;
import com.tulingxueyuan.mall.modules.oms.service.OmsCartItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "购物车Controller")
@RequestMapping("/car")
public class CartController {
    @Autowired
    OmsCartItemService cartItemService;

    @ApiOperation(value = "加入购物车")
    @PostMapping("/add")
    public CommonResult addCar(@RequestBody CartDTO cartDTO) {
        Boolean success = cartItemService.addCar(cartDTO);
        if (success) {
            return CommonResult.success(true, "添加成功！");
        } else {
             return CommonResult.failed(ResultCode.VALIDATE_FAILED);
        }
    }
    @ApiOperation(value = "加载购物车数量")
    @GetMapping("/products/sum")
    public CommonResult sum() {
        Integer sum = cartItemService.sum();
        return CommonResult.success(sum, "加载成功！");

    }
}
