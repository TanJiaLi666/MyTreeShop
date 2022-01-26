package com.tulingxueyuan.mall.modules.oms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.tulingxueyuan.mall.modules.oms.model.dto.DefaultListQueryDTO;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("加载订单列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<OmsOrder>> fetchList(DefaultListQueryDTO defaultListQueryDTO) {
        Page<OmsOrder> page = orderService.fetchList(defaultListQueryDTO);
        return CommonResult.success(CommonPage.restPage(page)) ;
    }
}

