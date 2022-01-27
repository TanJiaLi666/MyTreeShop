package com.tulingxueyuan.mall.modules.oms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnApply;
import com.tulingxueyuan.mall.modules.oms.model.dto.ApplyDefaultListQueryDTO;
import com.tulingxueyuan.mall.modules.oms.model.dto.DefaultListQueryDTO;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderReturnApplyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单退货申请 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-25
 */
@RestController
@RequestMapping("/returnApply")
public class OmsOrderReturnApplyController {
    @Autowired
    OmsOrderReturnApplyService applyService;

    /**
     * export function fetchList(params) {
     *   return request({
     *     url:'/returnApply/list',
     *     method:'get',
     *     params:params
     *   })
     * }
     */
    @ApiOperation("加载退货申请")
    @GetMapping("/list")
    public CommonResult<CommonPage<OmsOrderReturnApply>> fetchList(ApplyDefaultListQueryDTO applyDefaultListQueryDTO) {
        Page<OmsOrderReturnApply> page = applyService.fetchList(applyDefaultListQueryDTO);
        return CommonResult.success(CommonPage.restPage(page)) ;
    }

}

