package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductFetchInfoDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductInfoDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductQueryDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
@RestController
@RequestMapping("/product")
public class PmsProductController {
    @Autowired
    PmsProductService productService;

    @ApiOperation("加载商品列表.包括查询")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsProduct>> fetchList(PmsProductQueryDTO productQueryDTO) {
        Page page = productService.fetchList(productQueryDTO);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("商品的删除")
    @PostMapping("/update/deleteStatus")
    public CommonResult<Boolean> updateDeleteStatus(@RequestParam("ids") List<Long> ids){
        Boolean is_success = productService.updateDeleteStatus(ids);
        if (is_success){
            return CommonResult.success(true,"成功删除");
        }
        return CommonResult.failed();
    }

    @ApiOperation("新品状态改变按钮")
    @PostMapping("/update/newStatus")
    public CommonResult<Boolean> updateNewStatus(@RequestParam(value = "ids") List<Long> ids,
                                                  @RequestParam(value = "newStatus", defaultValue="0") Integer newStatus){
        Boolean is_success = productService.updateStatus(ids,newStatus,PmsProduct::getNewStatus);
        if(is_success){
            return CommonResult.success(true,"修改成功");
        }
        return CommonResult.failed("失败");
    }

    @ApiOperation("推荐状态改变按钮")
    @PostMapping("/update/recommendStatus")
    public CommonResult<Boolean> updateRecommendStatus(@RequestParam(value = "ids") List<Long> ids,
                                                 @RequestParam(value = "recommendStatus", defaultValue="0") Integer recommendStatus){
        Boolean is_success = productService.updateStatus(ids,recommendStatus,PmsProduct::getRecommandStatus);
        if(is_success){
            return CommonResult.success(true,"修改成功");
        }
        return CommonResult.failed("失败");
    }

    @ApiOperation("上架状态改变按钮")
    @PostMapping("/update/publishStatus")
    public CommonResult<Boolean> updatePublishStatus(@RequestParam(value = "ids") List<Long> ids,
                                                 @RequestParam(value = "publishStatus", defaultValue="0") Integer publishStatus){
        Boolean is_success = productService.updateStatus(ids,publishStatus,PmsProduct::getPublishStatus);
        if(is_success){
            return CommonResult.success(true,"修改成功");
        }
        return CommonResult.failed("失败");
    }
    @ApiOperation("审核状态改变按钮")
    @PostMapping("/update/verifyStatus")
    public CommonResult<Boolean> updateVerifyStatus(@RequestParam(value = "ids") List<Long> ids,
                                                     @RequestParam(value = "verifyStatus", defaultValue="0") Integer publishStatus){
        Boolean is_success = productService.updateStatus(ids,publishStatus,PmsProduct::getVerifyStatus);
        if(is_success){
            return CommonResult.success(true,"修改成功");
        }
        return CommonResult.failed("失败");
    }

    @ApiOperation("添加商品详细信息")
    @PostMapping("/create")
    public CommonResult<Boolean> createProduct(@RequestBody PmsProductInfoDTO productParam){
        Boolean is_success = productService.createProduct(productParam);
        if(is_success){
            return CommonResult.success(true,"保存成功！");
        }
        return CommonResult.failed("保存失败！");

    }
    @ApiOperation("加载商品详细信息")
    @GetMapping("/updateInfo/{id}")
    public CommonResult<PmsProductFetchInfoDTO> getProduct(@PathVariable("id") Long id) {
        PmsProductFetchInfoDTO getProductInfo = productService.getProduct(id);
        return CommonResult.success(getProductInfo) ;
    }
    @ApiOperation("添加商品详细信息")
    @PostMapping("/update/{id}")
    public CommonResult<Boolean> updateProduct(@RequestBody @Valid PmsProductInfoDTO productParam){
        Boolean isSuccess = productService.updateProduct(productParam);
        if(isSuccess){
            return CommonResult.success(true,"保存成功！");
        }
        return CommonResult.failed("保存失败！");
    }
    @ApiOperation("商品查询")
    @GetMapping("/simpleList")
    public CommonResult<List<PmsProduct>> fetchSimpleList(PmsProductQueryDTO productQueryDTO) {
        List<PmsProduct> list = productService.fetchSimpleList(productQueryDTO);
        return CommonResult.success(list);
    }
}