package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsBrand;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCateDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsBrandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public CommonResult<CommonPage<PmsProductCategory>> fetchList(@RequestParam(value = "pageNum", defaultValue="1") Integer pageNum,
                                                                  @RequestParam(value = "pageSize", defaultValue="10") Integer pageSize) {
        Page page = brandService.fetchList(pageNum,pageSize);
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

}

