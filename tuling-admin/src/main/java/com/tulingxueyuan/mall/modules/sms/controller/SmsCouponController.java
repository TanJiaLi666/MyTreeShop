package com.tulingxueyuan.mall.modules.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnReason;
import com.tulingxueyuan.mall.modules.sms.model.SmsCoupon;
import com.tulingxueyuan.mall.modules.sms.model.dto.CouponDTO;
import com.tulingxueyuan.mall.modules.sms.service.SmsCouponService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public CommonResult<CommonPage<SmsCoupon>> fetchList(CouponDTO couponDTO) {
        Page<SmsCoupon> page = couponService.fetchList(couponDTO);
        return CommonResult.success(CommonPage.restPage(page),"成功加载列表");
    }

    @ApiOperation("新增操作")
    @PostMapping("/create")
    public CommonResult<Boolean> createCoupon(@RequestBody CouponDTO couponDTO) {
        boolean save = couponService.createCoupon(couponDTO);
        return CommonResult.success(save) ;
    }
    @ApiOperation("查看优惠券信息")
    @GetMapping("/{id}")
    public CommonResult<CouponDTO> getCoupon(@PathVariable("id") Long id) {
        CouponDTO coupon = couponService.getCoupon(id);
        return CommonResult.success(coupon) ;
    }
    @ApiOperation("修改优惠券操作")
    @PostMapping("/update/{id}")
    public CommonResult<Boolean> updateCoupon(@PathVariable("id") Long id,
                                              @RequestBody CouponDTO couponDTO) {
        couponDTO.setId(id);
        boolean update = couponService.updateCoupon(couponDTO);
        if (update) {
            return CommonResult.success(true,"成功") ;
        }
        return CommonResult.failed("失败");
    }

    @ApiOperation("删除操作")
    @PostMapping("/delete/{id}")
    public CommonResult<Boolean> deleteCoupon(@PathVariable("id") Long id) {
        boolean removeById = couponService.deleteCoupon(id);
        return CommonResult.success(removeById);
    }
}

