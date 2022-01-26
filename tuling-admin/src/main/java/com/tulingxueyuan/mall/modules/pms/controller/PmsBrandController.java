package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsBrand;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.PmsSkuStock;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCateDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsBrandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService brandService;

    @ApiOperation("加载品牌列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsBrand>> fetchList(@RequestParam(value = "keyword", defaultValue="") String keyword,
                                                                  @RequestParam(value = "pageNum", defaultValue="1") Integer pageNum,
                                                                  @RequestParam(value = "pageSize", defaultValue="10") Integer pageSize) {
        Page page = brandService.fetchList(pageNum,pageSize,keyword);
        return CommonResult.success(CommonPage.restPage(page)) ;
    }
    @ApiOperation("添加品牌")
    @PostMapping("/create")
    public CommonResult<Boolean> createBrand(@RequestBody PmsBrand pmsBrand){
        Boolean is_success = brandService.createBrand(pmsBrand);
        if(is_success){
            return CommonResult.success(true,"保存成功！");
        }
        return CommonResult.failed("保存失败！");

    }
    @ApiOperation("商品sku数据初始化")
    @GetMapping("/{id}")
    public CommonResult<PmsBrand> fetchSkuStockList(@PathVariable("id") Long id) {
        PmsBrand result = brandService.fetchSkuStockList(id);
        if (result != null) {
            return CommonResult.success(result);
        }
        return CommonResult.failed("无数据");
    }
    @ApiOperation("更新品牌")
    @PostMapping("/update/{id}")
    public CommonResult<Boolean> updateBrand(@RequestBody PmsBrand pmsBrand,
                                             @PathVariable Long id){
        Boolean isSuccess = brandService.updateBrand(pmsBrand);
        if(isSuccess){
            return CommonResult.success(true,"保存成功！");
        }
        return CommonResult.failed("保存失败！");

    }
    @ApiOperation("删除品牌")
    @GetMapping("/delete/{id}")
    public CommonResult<Boolean> deleteBrand(@PathVariable Long id){
        Boolean isSuccess = brandService.deleteBrand(id);
        if(isSuccess){
            return CommonResult.success(true,"删除成功！");
        }
        return CommonResult.failed("删除失败！");

    }
    @ApiOperation("显示状态改变按钮")
    @PostMapping("/update/showStatus")
    public CommonResult<Boolean> updateShowStatus(@RequestParam(value = "ids") List<Long> ids,
                                                       @RequestParam(value = "showStatus", defaultValue="0") Integer showStatus){
        Boolean isSuccess = brandService.updateStatus(ids,showStatus, PmsBrand::getShowStatus);
        if(isSuccess){
            return CommonResult.success(true,"修改成功");
        }
        return CommonResult.failed("修改失败");
    }
    @ApiOperation("供应商状态改变按钮")
    @PostMapping("/update/factoryStatus")
    public CommonResult<Boolean> updateFactoryStatus(@RequestParam(value = "ids") List<Long> ids,
                                                       @RequestParam(value = "factoryStatus", defaultValue="0") Integer factoryStatus){
        Boolean isSuccess = brandService.updateStatus(ids,factoryStatus, PmsBrand::getFactoryStatus);
        if(isSuccess){
            return CommonResult.success(true,"修改成功");
        }
        return CommonResult.failed("修改失败");
    }

}

