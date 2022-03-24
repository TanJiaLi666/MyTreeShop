package com.tulingxueyuan.mall.modules.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeNewProduct;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeNewProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 新鲜好物表 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@RestController
@RequestMapping("/home/newProduct")
public class SmsHomeNewProductController {

    @Autowired
    SmsHomeNewProductService newProductService;

    @ApiOperation("加载新品好物列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsHomeNewProduct>> fetchList(SmsHomeNewProduct newProduct) {
        Page<SmsHomeNewProduct> page = newProductService.fetchList(newProduct);
        return CommonResult.success(CommonPage.restPage(page),"成功加载列表");
    }

    @ApiOperation("修改新品商品是否推荐")
    @PostMapping("/update/recommendStatus")
    public CommonResult<Boolean> updateRecommendStatus(@RequestParam("recommendStatus") Integer recommendStatus,
                                                       @RequestParam("ids") List<Long> ids) {
        boolean updateStatus = newProductService.updateRecommendStatus(ids, recommendStatus);
        if (updateStatus) {
            return CommonResult.success(true,"成功") ;
        }
        return CommonResult.failed("失败");
    }
    @ApiOperation("修改新品商品排序")
    @PostMapping("/update/sort/{id}")
    public CommonResult<Boolean> updateNewProductSort(@PathVariable("id") Long id,
                                              @RequestParam("sort") Integer sort) {
        boolean updateStatus = newProductService.updateNewProductSort(id, sort);
        if (updateStatus) {
            return CommonResult.success(true,"成功") ;
        }
        return CommonResult.failed("失败");
    }
    @ApiOperation("新品的删除")
    @PostMapping("/delete")
    public CommonResult<Boolean> deleteNewProduct(@RequestParam("ids") List<Long> ids){
        Boolean isSuccess = newProductService.deleteNewProduct(ids);
        if (isSuccess){
            return CommonResult.success(true,"成功删除");
        }
        return CommonResult.failed("删除失败");
    }
    @ApiOperation("新增操作")
    @PostMapping("/create")
    public CommonResult<Boolean> createNewProduct(@RequestBody List<SmsHomeNewProduct> newProduct) {
        boolean save = newProductService.createNewProduct(newProduct);
        return CommonResult.success(save) ;
    }
}

