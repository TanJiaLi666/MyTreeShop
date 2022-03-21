package com.tulingxueyuan.mall.modules.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnReason;
import com.tulingxueyuan.mall.modules.sms.model.dto.FlashPromotionProductRelationDTO;
import com.tulingxueyuan.mall.modules.sms.service.SmsFlashPromotionProductRelationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@RestController
@RequestMapping("/flashProductRelation")
public class SmsFlashPromotionProductRelationController {
    @Autowired
    SmsFlashPromotionProductRelationService relationService;


    @ApiOperation("加载商品秒杀列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<FlashPromotionProductRelationDTO>> fetchList(FlashPromotionProductRelationDTO relationDTO) {
        Page<FlashPromotionProductRelationDTO> page = relationService.fetchList(relationDTO);
        return CommonResult.success(CommonPage.restPage(page)) ;
    }
}

