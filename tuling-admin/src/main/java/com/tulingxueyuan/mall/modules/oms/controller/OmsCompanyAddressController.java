package com.tulingxueyuan.mall.modules.oms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.oms.model.OmsCompanyAddress;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnApply;
import com.tulingxueyuan.mall.modules.oms.model.dto.ApplyDefaultListQueryDTO;
import com.tulingxueyuan.mall.modules.oms.service.OmsCompanyAddressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 公司收发货地址表 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-25
 */
@RestController
@RequestMapping("/companyAddress")
public class OmsCompanyAddressController {
    @Autowired
    OmsCompanyAddressService companyAddressService;

    @ApiOperation("加载公司收发地址")
    @GetMapping("/list")
    public CommonResult<List<OmsCompanyAddress>> fetchList() {
        List<OmsCompanyAddress> list = companyAddressService.list();
        return CommonResult.success(list) ;
    }
}

