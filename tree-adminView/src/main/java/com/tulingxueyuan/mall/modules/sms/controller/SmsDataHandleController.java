package com.tulingxueyuan.mall.modules.sms.controller;

import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.sms.model.dto.AnalysisDTO;
import com.tulingxueyuan.mall.modules.sms.model.dto.DataQueryDTO;
import com.tulingxueyuan.mall.modules.sms.model.dto.DataStatisticsDTO;
import com.tulingxueyuan.mall.modules.sms.service.SmsDataHandleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dateHandle")
public class SmsDataHandleController {
    @Autowired
    SmsDataHandleService dataHandleService;

    @ApiOperation("商品销量预测")
    @GetMapping("/forecastShopSale")
    public CommonResult forecastShopSale(DataQueryDTO queryDTO) {
        List<DataStatisticsDTO>  list = dataHandleService.forecastShopSale(queryDTO);
        return CommonResult.success(list);
    }
    @ApiOperation("产品分析")
    @GetMapping("/analysisShop")
    public CommonResult analysisShop(DataQueryDTO queryDTO) {
        AnalysisDTO list = dataHandleService.analysisShop(queryDTO);
        return CommonResult.success(list);
    }
}