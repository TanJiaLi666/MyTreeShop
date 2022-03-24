package com.tulingxueyuan.mall.modules.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeBrand;
import com.tulingxueyuan.mall.modules.sms.model.dto.CouponDTO;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeBrandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页推荐品牌表 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@RestController
@RequestMapping("/home/brand")
public class SmsHomeBrandController {

    @Autowired
    SmsHomeBrandService brandService;

    @ApiOperation("加载品牌列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsHomeBrand>> fetchList(SmsHomeBrand brand) {
        Page<SmsHomeBrand> page = brandService.fetchList(brand);
        return CommonResult.success(CommonPage.restPage(page),"成功加载列表");
    }

    @ApiOperation("新增品牌")
    @PostMapping("/create")
    public CommonResult<Boolean> createHomeBrand(@RequestBody List<SmsHomeBrand> brands) {
        boolean save = brandService.createHomeBrand(brands);
        return CommonResult.success(save) ;
    }

    @ApiOperation("删除操作")
    @PostMapping("/delete")
    public CommonResult<Boolean> deleteHomeBrand(@RequestParam("ids")List<Long> ids) {
        boolean removeById = brandService.deleteHomeBrand(ids);
        return CommonResult.success(removeById);
    }

    @ApiOperation("修改品牌推荐状态操作")
    @PostMapping("/update/recommendStatus")
    public CommonResult<Boolean> updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                                       @RequestParam("recommendStatus") Integer recommendStatus) {
        boolean updateStatus = brandService.updateRecommendStatus(ids, recommendStatus);
        if (updateStatus) {
            return CommonResult.success(true,"成功") ;
        }
        return CommonResult.failed("失败");
    }
    @ApiOperation("修改品牌排序操作")
    @PostMapping("/update/sort/{id}")
    public CommonResult<Boolean> updateHomeBrandSort(@PathVariable("id") Long id,
                                                       @RequestParam("sort") Integer sort) {
        boolean updateStatus = brandService.updateHomeBrandSort(id, sort);
        if (updateStatus) {
            return CommonResult.success(true,"成功") ;
        }
        return CommonResult.failed("失败");
    }
}

