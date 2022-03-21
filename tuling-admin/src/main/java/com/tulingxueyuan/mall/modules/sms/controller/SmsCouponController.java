package com.tulingxueyuan.mall.modules.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.sms.model.SmsCoupon;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeBrand;
import com.tulingxueyuan.mall.modules.sms.service.SmsCouponService;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeBrandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 优惠卷表 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@RestController
@RequestMapping("/coupon")
public class SmsCouponController {

    @Autowired
    SmsCouponService couponService;

    @ApiOperation("加载优惠劵列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsCoupon>> fetchList(@RequestParam(value = "pageNum", defaultValue="1") Integer pageNum,
                                                         @RequestParam(value = "pageSize", defaultValue="5") Integer pageSize) {
        Page<SmsCoupon> page = couponService.fetchList(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page),"成功加载列表");
    }

}

