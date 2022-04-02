package com.tulingxueyuan.mall.modules.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeAdvertiseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@RestController
@RequestMapping("/home/advertise")
public class SmsHomeAdvertiseController {

    @Autowired
    SmsHomeAdvertiseService advertiseService;

    @ApiOperation("加载广告列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsHomeAdvertise>> fetchList(SmsHomeAdvertise advertise) {
        Page<SmsHomeAdvertise> page = advertiseService.fetchList(advertise);
        return CommonResult.success(CommonPage.restPage(page),"成功加载列表");
    }

    @ApiOperation("新增操作")
    @PostMapping("/create")
    public CommonResult<Boolean> createHomeAdvertise(@RequestBody SmsHomeAdvertise advertise) {
        boolean save = advertiseService.createHomeAdvertise(advertise);
        return CommonResult.success(save) ;
    }
    @ApiOperation("修改订单备注信息")
    @PostMapping("/update/{id}")
    public CommonResult<Boolean> updateHomeAdvertise(@PathVariable("id") Long id,
                                                     @RequestBody SmsHomeAdvertise advertise) {
        advertise.setId(id);
        Boolean isSuccess = advertiseService.updateHomeAdvertise(advertise);
        if (isSuccess) {
            return CommonResult.success(true,"修改成功");
        }
        return CommonResult.failed("修改失败") ;
    }
    @ApiOperation("修改广告上线状态")
    @PostMapping("/update/status/{id}")
    public CommonResult<Boolean> updateStatus(@PathVariable("id") Long id,
                                              @RequestParam("status") Integer status) {
        boolean updateStatus = advertiseService.updateStatus(id, status);
        if (updateStatus) {
            return CommonResult.success(true,"成功") ;
        }
        return CommonResult.failed("失败");
    }
    @ApiOperation("加载广告详细信息")
    @GetMapping("/{id}")
    public CommonResult<SmsHomeAdvertise> getHomeAdvertise(@PathVariable("id") Long id) {
        SmsHomeAdvertise orderDetail = advertiseService.getHomeAdvertise(id);
        return CommonResult.success(orderDetail) ;
    }
    @ApiOperation("广告的删除")
    @PostMapping("/delete")
    public CommonResult<Boolean> deleteHomeAdvertise(@RequestParam("ids") List<Long> ids){
        Boolean isSuccess = advertiseService.deleteHomeAdvertise(ids);
        if (isSuccess){
            return CommonResult.success(true,"成功删除");
        }
        return CommonResult.failed("删除失败");
    }

}

