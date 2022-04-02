package com.tulingxueyuan.mall.modules.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnReason;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotion;
import com.tulingxueyuan.mall.modules.sms.service.SmsFlashPromotionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 限时购表 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@RestController
@RequestMapping("/flash")
public class SmsFlashPromotionController {
    @Autowired
    SmsFlashPromotionService flashPromotionService;

    @ApiOperation("加载秒杀活动列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsFlashPromotion>> fetchList(@RequestParam(value = "pageNum", defaultValue="1") Integer pageNum,
                                                                 @RequestParam(value = "pageSize", defaultValue="5") Integer pageSize,
                                                                 @RequestParam(value = "keyword", defaultValue="") String keyword) {
        Page<SmsFlashPromotion> page = flashPromotionService.fetchList(pageNum, pageSize, keyword);
        return CommonResult.success(CommonPage.restPage(page),"成功加载列表");
    }

    @ApiOperation("新增秒杀活动列表")
    @PostMapping("/create")
    public CommonResult<Boolean> createFlash(@RequestBody SmsFlashPromotion flashPromotion) {
        Boolean save = flashPromotionService.createFlash(flashPromotion);
        return CommonResult.success(save,"成功加载列表");
    }

    @ApiOperation("修改秒杀状态操作")
    @PostMapping("/update/status/{id}")
    public CommonResult<Boolean> updateStatus(@PathVariable("id") Long id,
                                              @RequestParam("status") Integer status) {
        boolean update = flashPromotionService.updateStatus(id, status);
        if (update) {
            return CommonResult.success(true,"成功") ;
        }
        return CommonResult.failed("失败");
    }

    @ApiOperation("批量删除操作")
    @PostMapping("/delete/{id}")
    public CommonResult<Boolean> deleteApply(@PathVariable("id") Long id) {
        boolean removeByIds = flashPromotionService.removeById(id);
        return CommonResult.success(removeByIds) ;
    }

    @ApiOperation("修改秒杀操作")
    @PostMapping("/update/{id}")
    public CommonResult<Boolean> updateReason(@PathVariable("id") Long id,
                                              @RequestBody SmsFlashPromotion flashPromotion) {
        flashPromotion.setId(id);
        boolean update = flashPromotionService.updateById(flashPromotion);
        if (update) {
            return CommonResult.success(true,"成功") ;
        }
        return CommonResult.failed("失败");
    }
}

