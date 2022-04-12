package com.tulingxueyuan.mall.controller;

import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.common.api.ResultCode;
import com.tulingxueyuan.mall.dto.CartDTO;
import com.tulingxueyuan.mall.modules.oms.model.OmsCartItem;
import com.tulingxueyuan.mall.modules.oms.service.OmsCartItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "购物车Controller")
@RequestMapping("/car")
public class CartController {
    @Autowired
    OmsCartItemService cartItemService;

    @ApiOperation(value = "加载购物车列表")
    @GetMapping("/list")
    public CommonResult fetchList() {
        List<OmsCartItem> list = cartItemService.fetchList();
        return CommonResult.success(list, "加载成功！");
    }

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

    @ApiOperation(value = "更新购物车数量")
    @PostMapping("/update/quantity")
    public CommonResult updateQuantity(Long id, Integer quantity) {
        Boolean success = cartItemService.updateQuantity(id, quantity);
        if (success) {
            return CommonResult.success(true, "更新成功！");
        } else {
            return CommonResult.failed(ResultCode.VALIDATE_FAILED);
        }
    }

    @ApiOperation(value = "删除购物车内容")
    @PostMapping("/delete")
    public CommonResult deleteCar(@RequestParam(value = "ids") List<Long> ids) {
        Boolean success = cartItemService.deleteCar(ids);
        if (success) {
            return CommonResult.success(true, "更新成功！");
        } else {
            return CommonResult.failed(ResultCode.VALIDATE_FAILED);
        }
    }

}
