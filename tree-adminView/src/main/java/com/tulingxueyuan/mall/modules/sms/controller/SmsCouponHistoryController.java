package com.tulingxueyuan.mall.modules.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.sms.model.SmsCouponHistory;
import com.tulingxueyuan.mall.modules.sms.model.dto.CouponHistoryDTO;
import com.tulingxueyuan.mall.modules.sms.service.SmsCouponHistoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 优惠券使用、领取历史表 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@RestController
@RequestMapping("/couponHistory")
public class SmsCouponHistoryController {
    @Autowired
    SmsCouponHistoryService couponHistoryService;

    @ApiOperation("加载优惠劵历史列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsCouponHistory>> fetchList(CouponHistoryDTO historyDTO) {
        Page<SmsCouponHistory> page = couponHistoryService.fetchList(historyDTO);
        return CommonResult.success(CommonPage.restPage(page),"成功加载列表");
    }

}

