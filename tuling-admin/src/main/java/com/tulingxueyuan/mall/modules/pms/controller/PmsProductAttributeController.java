package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 商品属性参数表 前端控制器（商品属性/参数控制器（与商品类型关联））
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
@RestController
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {
    @Autowired
    PmsProductAttributeService productAttributeService;

    @ApiOperation("加载商品属性")
    @GetMapping("/list/{cid}")
    public CommonResult<CommonPage<PmsProductAttribute>> lodingList( @RequestParam(value = "pageNum", defaultValue="1") Integer pageNum,
                                                                   @RequestParam(value = "pageSize", defaultValue="5") Integer pageSize,
                                                                   @RequestParam(value = "type", defaultValue="1") Integer type,
                                                                   @PathVariable("cid") Integer cid){
        Page<PmsProductAttribute> page = productAttributeService.lodingList(cid,pageNum,pageSize,type);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("获取商品属性")
    @GetMapping("/{id}")
    public CommonResult<PmsProductAttribute> getProductAttr(@PathVariable("id") Long id){
        PmsProductAttribute productAttr = productAttributeService.getProductAttr(id);
        if(productAttr != null){
            return CommonResult.success(productAttr,"成功");
        }
        return CommonResult.failed("失败");
    }


    @ApiOperation("修改商品属性")
    @PostMapping("/update/{id}")
    public CommonResult<Boolean> updateProductAttr(@RequestBody PmsProductAttribute productAttribute){
        Boolean is_success = productAttributeService.updateProductAttr(productAttribute);
        if (is_success){
            return CommonResult.success(is_success,"修改成功");
        }
        return CommonResult.failed("修改失败");
    }

    @ApiOperation("添加商品属性")
    @PostMapping("/create")
    public CommonResult<Boolean> createProductAttr(@RequestBody PmsProductAttribute productAttribute){
        Boolean is_success = productAttributeService.createProductAttr(productAttribute);
        if (is_success){
            return CommonResult.success(is_success,"添加成功");
        }
        return CommonResult.failed("添加失败");
    }


    @ApiOperation("删除商品属性")
    @PostMapping("/delete")
    public CommonResult<Boolean> deleteProductAttr(@RequestParam("ids") List<Long> ids){
        Boolean is_success = productAttributeService.deleteProductAttr(ids);
        if (is_success){
            return CommonResult.success(is_success,"删除成功");
        }
        return CommonResult.failed("删除失败");
    }

}

