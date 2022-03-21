package com.tulingxueyuan.mall.modules.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnReason;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotion;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionSession;
import com.tulingxueyuan.mall.modules.sms.model.dto.FlashPromotionProductRelationDTO;
import com.tulingxueyuan.mall.modules.sms.service.SmsFlashPromotionProductRelationService;
import com.tulingxueyuan.mall.modules.sms.service.SmsFlashPromotionService;
import com.tulingxueyuan.mall.modules.sms.service.SmsFlashPromotionSessionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 限时购场次表 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@RestController
@RequestMapping("/flashSession")
public class SmsFlashPromotionSessionController {

    @Autowired
    SmsFlashPromotionSessionService flashPromotionSessionService;
    @Autowired
    SmsFlashPromotionProductRelationService relationService;

    @ApiOperation("加载秒杀时间段列表")
    @GetMapping("/list")
    public CommonResult<List<SmsFlashPromotionSession>> fetchList() {
        List<SmsFlashPromotionSession> list = flashPromotionSessionService.list();
        return CommonResult.success(list,"成功加载列表");
    }

    @ApiOperation("加载活动内秒杀时间段详细信息")
    @GetMapping("/selectList")
    public CommonResult<List<FlashPromotionProductRelationDTO>> fetchSelectList(FlashPromotionProductRelationDTO flashPromotionProductRelationDTO) {
        List<FlashPromotionProductRelationDTO> session = relationService.fetchSelectList(flashPromotionProductRelationDTO);
        return CommonResult.success(session,"成功加载列表");
    }
    @ApiOperation("修改秒杀活动操作")
    @PostMapping("/update/{id}")
    public CommonResult<Boolean> updateSession(@PathVariable("id") Long id,
                                              @RequestBody SmsFlashPromotionSession flashPromotionSession) {
        flashPromotionSession.setId(id);
        boolean update = flashPromotionSessionService.updateById(flashPromotionSession);
        if (update) {
            return CommonResult.success(true,"成功") ;
        }
        return CommonResult.failed("失败");
    }
    @ApiOperation("活动启用状态操作")
    @PostMapping("/update/status/{id}")
    public CommonResult<Boolean> updateStatus(@PathVariable("id") Long id,
                                               @RequestParam("status") Integer status) {
        boolean update = flashPromotionSessionService.updateStatus(id, status);
        if (update) {
            return CommonResult.success(true,"成功") ;
        }
        return CommonResult.failed("失败");
    }
    @ApiOperation("新增操作")
    @PostMapping("/create")
    public CommonResult<Boolean> createSession(@RequestBody SmsFlashPromotionSession flashPromotionSession) {
        boolean save = flashPromotionSessionService.createSession(flashPromotionSession);
        return CommonResult.success(save) ;
    }
    @ApiOperation("批量删除操作")
    @PostMapping("/delete")
    public CommonResult<Boolean> deleteSession(@RequestParam("id") Long id) {
        boolean removeByIds = flashPromotionSessionService.removeById(id);
        return CommonResult.success(removeByIds) ;
    }
}

