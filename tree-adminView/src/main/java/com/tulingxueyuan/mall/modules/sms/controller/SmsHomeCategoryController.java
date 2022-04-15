package com.tulingxueyuan.mall.modules.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeCategory;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeCategoryService;
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
@RequestMapping("/home/category")
public class SmsHomeCategoryController {

    @Autowired
    SmsHomeCategoryService homeCategoryService;

    @ApiOperation("加载分类列表")
    @GetMapping("/fetchList")
    public CommonResult<CommonPage<SmsHomeCategory>> fetchList(SmsHomeCategory category) {
        Page<SmsHomeCategory> page = homeCategoryService.fetchList(category);
        return CommonResult.success(CommonPage.restPage(page),"成功加载列表");
    }

    @ApiOperation("分类的删除")
    @PostMapping("/delete")
    public CommonResult<Boolean> deleteHomeCategory(@RequestParam("ids") List<Long> ids){
        Boolean isSuccess = homeCategoryService.deleteHomeCategory(ids);
        if (isSuccess){
            return CommonResult.success(true,"成功删除");
        }
        return CommonResult.failed("删除失败");
    }
    @ApiOperation("修改上线状态")
    @PostMapping("/update/status/{id}")
    public CommonResult<Boolean> updateStatus(@PathVariable("id") Long id,
                                              @RequestParam("status") Integer status) {
        boolean updateStatus = homeCategoryService.updateStatus(id, status);
        if (updateStatus) {
            return CommonResult.success(true,"成功") ;
        }
        return CommonResult.failed("失败");
    }
    @ApiOperation("加载分类详细信息")
    @GetMapping("/{id}")
    public CommonResult<SmsHomeCategory> getHomeCategory(@PathVariable("id") Long id) {
        SmsHomeCategory orderDetail = homeCategoryService.getHomeCategory(id);
        return CommonResult.success(orderDetail) ;
    }
    @ApiOperation("新增操作")
    @PostMapping("/create")
    public CommonResult<Boolean> createHomeCategory(@RequestBody SmsHomeCategory homeCategory) {
        boolean save = homeCategoryService.createHomeCategory(homeCategory);
        return CommonResult.success(save) ;
    }
    @ApiOperation("修改订单备注信息")
    @PostMapping("/update/{id}")
    public CommonResult<Boolean> updateHomeCategory(@PathVariable("id") Long id,
                                                     @RequestBody SmsHomeCategory category) {
        category.setId(id);
        Boolean isSuccess = homeCategoryService.updateHomeCategory(category);
        if (isSuccess) {
            return CommonResult.success(true,"修改成功");
        }
        return CommonResult.failed("修改失败") ;
    }
}

