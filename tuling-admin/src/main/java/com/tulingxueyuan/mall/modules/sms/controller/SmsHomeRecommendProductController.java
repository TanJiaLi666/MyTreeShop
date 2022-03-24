package com.tulingxueyuan.mall.modules.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendProduct;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeRecommendProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 人气推荐商品表 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@RestController
@RequestMapping("/home/recommendProduct")
public class SmsHomeRecommendProductController {

    @Autowired
    SmsHomeRecommendProductService recommendProductService;

    @ApiOperation("加载人气推荐列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsHomeRecommendProduct>> fetchList(SmsHomeRecommendProduct recommendProduct) {
        Page<SmsHomeRecommendProduct> page = recommendProductService.fetchList(recommendProduct);
        return CommonResult.success(CommonPage.restPage(page),"成功加载列表");
    }
    @ApiOperation("修改人气商品是否推荐")
    @PostMapping("/update/recommendStatus")
    public CommonResult<Boolean> updateRecommendStatus(@RequestParam("recommendStatus") Integer recommendStatus,
                                                       @RequestParam("ids") List<Long> ids) {
        boolean updateStatus = recommendProductService.updateRecommendStatus(ids, recommendStatus);
        if (updateStatus) {
            return CommonResult.success(true,"成功") ;
        }
        return CommonResult.failed("失败");
    }
    @ApiOperation("修改人气商品排序")
    @PostMapping("/update/sort/{id}")
    public CommonResult<Boolean> updateHotProductSort(@PathVariable("id") Long id,
                                                      @RequestParam("sort") Integer sort) {
        boolean updateStatus = recommendProductService.updateHotProductSort(id, sort);
        if (updateStatus) {
            return CommonResult.success(true,"成功") ;
        }
        return CommonResult.failed("失败");
    }
    @ApiOperation("人气的删除")
    @PostMapping("/delete")
    public CommonResult<Boolean> deleteHotProduct(@RequestParam("ids") List<Long> ids){
        Boolean isSuccess = recommendProductService.deleteHotProduct(ids);
        if (isSuccess){
            return CommonResult.success(true,"成功删除");
        }
        return CommonResult.failed("删除失败");
    }
    @ApiOperation("人气操作")
    @PostMapping("/create")
    public CommonResult<Boolean> createHotProduct(@RequestBody List<SmsHomeRecommendProduct> recommendProducts) {
        boolean save = recommendProductService.createHotProduct(recommendProducts);
        return CommonResult.success(save) ;
    }
}

