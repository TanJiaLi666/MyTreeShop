package com.tulingxueyuan.mall.modules.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionProductRelation;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionSession;
import com.tulingxueyuan.mall.modules.sms.model.dto.FlashPromotionProductRelationDTO;
import com.tulingxueyuan.mall.modules.sms.service.SmsFlashPromotionProductRelationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("新增操作")
    @PostMapping("/create")
    public CommonResult<Boolean> createFlashProductRelation(@RequestBody List<SmsFlashPromotionProductRelation> list) {
        boolean save = relationService.createFlashProductRelation(list);
        if (save) {
            return CommonResult.success(true) ;
        }
        return CommonResult.failed("添加失败，数据错误或商品可能已经存在！");
    }
    @ApiOperation("修改商品折扣信息")
    @PostMapping("/update/{id}")
    public CommonResult<Boolean> updateFlashProductRelation(@PathVariable("id") Long id,
                                               @RequestBody FlashPromotionProductRelationDTO flashPromotionProductRelationDTO) {
        flashPromotionProductRelationDTO.setId(id);
        boolean update = relationService.updateById(flashPromotionProductRelationDTO);
        if (update) {
            return CommonResult.success(true,"成功") ;
        }
        return CommonResult.failed("失败");
    }
    @ApiOperation("删除操作")
    @PostMapping("/delete/{id}")
    public CommonResult<Boolean> deleteFlashProductRelation(@PathVariable("id") Long id) {
        boolean removeById = relationService.removeById(id);
        return CommonResult.success(removeById) ;
    }
}

