package com.tulingxueyuan.mall.modules.home.controller;

import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.home.dto.AdminDTO;
import com.tulingxueyuan.mall.modules.home.dto.OrderListDTO;
import com.tulingxueyuan.mall.modules.home.dto.ProductDTO;
import com.tulingxueyuan.mall.modules.home.dto.ReturnOrderDTO;
import com.tulingxueyuan.mall.modules.home.service.HomeOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("首页订单数据API")
@RequestMapping("/home/order")
public class HomeOrderController {
    @Autowired
    HomeOrderService orderService;

    @ApiOperation("订单统计")
    @GetMapping("/order_statistics")
    public CommonResult orderStatistics() {
        OrderListDTO list = orderService.orderStatistics();
        if (list != null) {
            return CommonResult.success(list,"成功");
        }
        return CommonResult.failed("订单统计失败");
    }
    @ApiOperation("退货订单统计")
    @GetMapping("/return_order")
    public CommonResult returnOrder() {
        ReturnOrderDTO list = orderService.returnOrder();
        if (list != null) {
            return CommonResult.success(list,"成功");
        }
        return CommonResult.failed("退货订单统计失败");
    }
    @ApiOperation("商品统计")
    @GetMapping("/shop_statistics")
    public CommonResult shopStatistics() {
        ProductDTO list = orderService.shopStatistics();
        if (list != null) {
            return CommonResult.success(list,"成功");
        }
        return CommonResult.failed("商品统计失败");
    }
    @ApiOperation("用户统计")
    @GetMapping("/user_statistics")
    public CommonResult adminStatistics() {
        AdminDTO list = orderService.adminStatistics();
        if (list != null) {
            return CommonResult.success(list,"成功");
        }
        return CommonResult.failed("用户统计失败");
    }

}
