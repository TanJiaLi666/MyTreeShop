package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
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
}

