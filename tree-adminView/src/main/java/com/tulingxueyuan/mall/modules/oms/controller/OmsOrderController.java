package com.tulingxueyuan.mall.modules.oms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.tulingxueyuan.mall.modules.oms.model.dto.DefaultListQueryDTO;
import com.tulingxueyuan.mall.modules.oms.model.dto.OmsOrderDeliveryDTO;
import com.tulingxueyuan.mall.modules.oms.model.dto.OmsOrderInfoDTO;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderService;
import com.tulingxueyuan.mall.modules.oms.service.UmsMemberReceiveAddressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-25
 */
@RestController
@RequestMapping("/order")
public class OmsOrderController {

    @Autowired
    OmsOrderService orderService;
    @Autowired
    UmsMemberReceiveAddressService receiveAddressService;

    @ApiOperation("加载订单列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<OmsOrder>> fetchList(DefaultListQueryDTO defaultListQueryDTO) {
        Page<OmsOrder> page = orderService.fetchList(defaultListQueryDTO);
        return CommonResult.success(CommonPage.restPage(page)) ;
    }
    @ApiOperation("加载订单详细信息")
    @GetMapping("/{id}")
    public CommonResult<OmsOrderInfoDTO> getOrderDetail(@PathVariable("id") Long id) {
        OmsOrderInfoDTO orderDetail = orderService.getOrderDetail(id);
        return CommonResult.success(orderDetail) ;
    }
    @ApiOperation("修改订单备注信息")
    @PostMapping("/update/note")
    public CommonResult<Boolean> updateOrderNote(@RequestParam("id") Long id,
                                                 @RequestParam("note") String note,
                                                 @RequestParam("status") Integer status) {
        Boolean isSuccess = orderService.updateOrderNote(id, note, status);
        if (isSuccess) {
            return CommonResult.success(true,"修改成功");
        }
        return CommonResult.failed("修改失败") ;
    }
    @ApiOperation("订单的删除")
    @PostMapping("/delete")
    public CommonResult<Boolean> deleteOrder(@RequestParam("ids") List<Long> ids){
        Boolean isSuccess = orderService.deleteOrder(ids);
        if (isSuccess){
            return CommonResult.success(true,"成功删除");
        }
        return CommonResult.failed("删除失败");
    }
    @ApiOperation("订单的发货")
    @PostMapping("/update/delivery")
    public CommonResult<Boolean> deliveryOrder(@RequestBody List<OmsOrderDeliveryDTO> orderDeliveryDTOS){
        Boolean isSuccess = orderService.deliveryOrder(orderDeliveryDTOS);
        if (isSuccess){
            return CommonResult.success(true,"发货成功");
        }
        return CommonResult.failed("未成功");
    }
    @ApiOperation("关闭订单")
    @PostMapping("/update/close")
    public CommonResult<Boolean> closeOrder(@RequestParam("ids") List<Long> ids,
                                            @RequestParam("note") String note){
        Boolean isSuccess = orderService.closeOrder(ids, note);
        if (isSuccess){
            return CommonResult.success(true,"发货成功");
        }
        return CommonResult.failed("未成功");
    }
    @ApiOperation("取消订单")
    @PostMapping("/update/cancel")
    public CommonResult<Boolean> cancelOrder(@RequestParam("ids") List<Long> ids,
                                            @RequestParam("note") String note){
        Boolean isSuccess = orderService.cancelOrder(ids, note);
        if (isSuccess){
            return CommonResult.success(true,"发货成功");
        }
        return CommonResult.failed("未成功");
    }
   @ApiOperation("修改订单收件人信息")
   @PostMapping("/update/receiverInfo")
   public CommonResult<Boolean> receiverInfo(@RequestBody OmsOrder order) {
       Boolean isSuccess = orderService.receiverInfo(order);
       if (isSuccess) {
           return CommonResult.success(true,"修改成功");
       }
       return CommonResult.failed("修改失败") ;
   }
   @ApiOperation("修改订单收件人信息")
   @PostMapping("/update/moneyInfo")
   public CommonResult<Boolean> updateMoneyInfo(@RequestBody OmsOrder order) {
       Boolean isSuccess = orderService.updateMoneyInfo(order);
       if (isSuccess) {
           return CommonResult.success(true,"修改成功");
       }
       return CommonResult.failed("修改失败") ;
   }
}