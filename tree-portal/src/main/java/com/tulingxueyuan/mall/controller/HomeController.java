package com.tulingxueyuan.mall.controller;


import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.dto.HomeCateAndBannerDTO;
import com.tulingxueyuan.mall.dto.HomeCateDTO;
import com.tulingxueyuan.mall.dto.HomeGoodsSaleDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryService;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeAdvertiseService;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-04-03
 */
@Api(tags = "商城首页")
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    PmsProductCategoryService categoryService;

    @Autowired
    SmsHomeAdvertiseService advertiseService;

    @Autowired
    SmsHomeCategoryService homeCategoryService;

    @ApiOperation("获取首页分类列表和广告横幅")
    @GetMapping("/productCateAndBannerList")
    public CommonResult getCateList() {
        //todo 查询分类及分类下的推荐商品
        List<HomeCateDTO> cateList  = categoryService.getCateList();
        //todo 查询广告横幅列表
        List<SmsHomeAdvertise> advertises = advertiseService.getBannerList();
        HomeCateAndBannerDTO homeCateAndBannerDTO = new HomeCateAndBannerDTO();
        homeCateAndBannerDTO.setHomeCateDTO(cateList);
        homeCateAndBannerDTO.setAdvertisesList(advertises);
        return CommonResult.success(homeCateAndBannerDTO,"加载成功！");
    }
    @ApiOperation("获取推荐分类及商品列表")
    @GetMapping("/goods_sale")
    public CommonResult getGoodsSaleList() {
        //todo 查询分类及分类下的推荐商品
        List<HomeGoodsSaleDTO> list  = homeCategoryService.getGoodsSaleList();
        return CommonResult.success(list,"加载成功！");
    }
}

