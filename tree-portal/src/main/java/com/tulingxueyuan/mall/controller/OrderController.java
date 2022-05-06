package com.tulingxueyuan.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.dto.CartDTO;
import com.tulingxueyuan.mall.dto.ConfirmOrderDTO;
import com.tulingxueyuan.mall.dto.OrderDTO;
import com.tulingxueyuan.mall.dto.OrderItemDTO;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnReason;
import com.tulingxueyuan.mall.modules.oms.service.OmsCartItemService;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderReturnReasonService;
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
    @Autowired
    OmsCartItemService cartItemService;
    @Autowired
    OmsOrderReturnReasonService reasonService;

    @ApiOperation(value = "加载商品列表")
    @PostMapping("/generateConfirmOrder")
    public CommonResult fetchList(@RequestParam(value = "itemIds") List<Long> ids) {
        ConfirmOrderDTO list = orderService.fetchList(ids);
        if (list != null) {
            return CommonResult.success(list, "加载成功！");
        }else {
            return CommonResult.failed("加载失败");
        }
    }
    @ApiOperation(value = "生成订单")
    @PostMapping("/generateOrder")
    public CommonResult generateOrder(@RequestBody OrderDTO orderDTO) {
        Long save = orderService.generateOrder(orderDTO);
        if (save != null) {
            return CommonResult.success(save, "成功！");
        }
        return CommonResult.failed("提交失败");
    }

    @ApiOperation(value = "生成订单信息")
    @GetMapping("/orderDetail")
    public CommonResult orderDetail(@RequestParam("orderId") Long orderId) {
        OrderItemDTO dto = orderService.orderDetail(orderId);
        if (dto != null) {
            return CommonResult.success(dto, "成功！");
        }
        return CommonResult.failed("失败");
    }
    @ApiOperation(value = "订单列表")
    @PostMapping("/list/userOrder")
    public CommonResult<CommonPage> userOrderList(@RequestParam("pageNum") Integer pageNum,
                                                  @RequestParam("pageSize") Integer pageSize) {
        Page<OrderItemDTO> dto = orderService.userOrderList(pageNum, pageSize);
        if (dto != null) {
            return CommonResult.success(CommonPage.restPage(dto), "成功！");
        }
        return CommonResult.failed("失败");
    }


    @ApiOperation(value = "订单支付成功")
    @PostMapping("/paySuccess")
    public CommonResult paySuccess(@RequestParam("orderId") Long orderId,
                                    @RequestParam("payType") Integer payType) {
        Boolean success = orderService.paySuccess(orderId, payType);
        if (success) {
            return CommonResult.success(success, "成功！");
        }
        return CommonResult.failed("失败");

    }
    @ApiOperation(value = "立即购买")
    @PostMapping("/add")
    public CommonResult addOrder(@RequestBody CartDTO cartDTO) {
        Long orderId = cartItemService.addOrder(cartDTO);
        return CommonResult.success(orderId);
    }
    @ApiOperation(value = "获取退货原因")
    @PostMapping("/getReturnReason")
    public CommonResult getReturnReason() {
        List<OmsOrderReturnReason> list = reasonService.getReturnReason();
        return CommonResult.success(list);
    }
    @ApiOperation(value = "获取退货原因")
    @PostMapping("/addReturnReason")
    public CommonResult addReturnReason(String text, Long id) {
        Boolean save = reasonService.addReturnReason(text, id);
        return CommonResult.success(save);
    }
}
