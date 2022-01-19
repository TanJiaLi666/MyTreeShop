package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCateDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductInfoDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductQueryDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return CommonResult.success(CommonPage.restPage(page)) ;
    }

    @ApiOperation("商品的删除")
    @PostMapping("/update/deleteStatus")
    public CommonResult<Boolean> updateDeleteStatus(@RequestParam("ids") List<Long> ids){
        Boolean is_success = productService.updateDeleteStatus(ids);
        if (is_success){
            return CommonResult.success(is_success,"成功删除");
        }
        return CommonResult.failed();
    }

    @ApiOperation("新品状态改变按钮")
    @PostMapping("/update/newStatus")
    public CommonResult<Boolean> updateNewStatus(@RequestParam(value = "ids") List<Long> ids,
                                                  @RequestParam(value = "newStatus", defaultValue="0") Integer newStatus){
        Boolean is_success = productService.updateStatus(ids,newStatus,PmsProduct::getNewStatus);
        if(is_success){
            return CommonResult.success(is_success,"修改成功");
        }
        return CommonResult.failed("失败");
    }

    @ApiOperation("推荐状态改变按钮")
    @PostMapping("/update/recommendStatus")
    public CommonResult<Boolean> updateRecommendStatus(@RequestParam(value = "ids") List<Long> ids,
                                                 @RequestParam(value = "recommandStatus", defaultValue="0") Integer recommandStatus){
        Boolean is_success = productService.updateStatus(ids,recommandStatus,PmsProduct::getRecommandStatus);
        if(is_success){
            return CommonResult.success(is_success,"修改成功");
        }
        return CommonResult.failed("失败");
    }

    @ApiOperation("上架状态改变按钮")
    @PostMapping("/update/publishStatus")
    public CommonResult<Boolean> updatePublishStatus(@RequestParam(value = "ids") List<Long> ids,
                                                 @RequestParam(value = "publishStatus", defaultValue="0") Integer publishStatus){
        Boolean is_success = productService.updateStatus(ids,publishStatus,PmsProduct::getPublishStatus);
        if(is_success){
            return CommonResult.success(is_success,"修改成功");
        }
        return CommonResult.failed("失败");
    }

/*    export function createProduct(data) {
        return request({
                url:'/product/create',
                method:'post',
                data:data
  })
    }*/
    @ApiOperation("添加商品详细信息")
    @PostMapping("/create")
    public CommonResult<Boolean> createProduct(@RequestParam PmsProductInfoDTO productInfoDTO){
        Boolean is_success = productService.createProduct(productInfoDTO);
        if(is_success){
            return CommonResult.success(true,"保存成功！");
        }
        return CommonResult.failed("保存失败！");

    }
}

