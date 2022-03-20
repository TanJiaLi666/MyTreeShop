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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation("加载退货申请,查询和筛选")
    @GetMapping("/list")
    public CommonResult<CommonPage<OmsOrderReturnApply>> fetchList(ApplyDefaultListQueryDTO applyDefaultListQueryDTO) {
        Page<OmsOrderReturnApply> page = applyService.fetchList(applyDefaultListQueryDTO);
        return CommonResult.success(CommonPage.restPage(page)) ;
    }

    @ApiOperation("批量删除操作")
    @PostMapping("/delete")
    public CommonResult<Boolean> deleteApply(@RequestParam("ids") List<Long> ids) {
        boolean removeByIds = applyService.removeByIds(ids);
        return CommonResult.success(removeByIds) ;
    }

    @ApiOperation("退货商品详细信息")
    @GetMapping("/{id}")
    public CommonResult<OmsOrderReturnApply> getApplyDetail(@PathVariable("id") Long id) {
        OmsOrderReturnApply omsOrderReturnApply = applyService.getById(id);
        return CommonResult.success(omsOrderReturnApply) ;
    }

    @ApiOperation("处理退货商品")
    @PostMapping("/update/status/{id}")
    public CommonResult<Boolean> updateApplyStatus(@PathVariable("id") Long id,
                                                   @RequestBody OmsOrderReturnApply orderReturnApply) {
        orderReturnApply.setId(id);
        boolean update = applyService.updateById(orderReturnApply);
        if (update) {
            return CommonResult.success(true,"成功") ;
        }
        return CommonResult.failed("失败");
    }
}

