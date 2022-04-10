package com.tulingxueyuan.mall.controller;

import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.dto.ConfirmOrderDTO;
import com.tulingxueyuan.mall.modules.oms.model.OmsCartItem;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "订单Controller")
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OmsOrderService orderService;

    @ApiOperation(value = "加载商品列表")
    @PostMapping("/generateConfirmOrder")
    public CommonResult fetchList(@RequestParam(value = "itemIds") List<Long> ids) {
        ConfirmOrderDTO list = orderService.fetchList(ids);
        return CommonResult.success(list, "加载成功！");
    }
}
