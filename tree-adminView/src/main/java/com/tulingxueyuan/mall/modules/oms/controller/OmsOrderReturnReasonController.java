package com.tulingxueyuan.mall.modules.oms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnReason;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderReturnReasonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 退货原因表 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-25
 */
@RestController
@RequestMapping("/returnReason")
public class OmsOrderReturnReasonController {


    @Autowired
    OmsOrderReturnReasonService reasonService;

    @ApiOperation("加载退货申请原因列表,查询和筛选")
    @GetMapping("/list")
    public CommonResult<CommonPage<OmsOrderReturnReason>> fetchList(@RequestParam(value = "pageNum", defaultValue="1") Integer pageNum,
                                                                   @RequestParam(value = "pageSize", defaultValue="10") Integer pageSize) {
        Page<OmsOrderReturnReason> page = reasonService.fetchList(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page)) ;
    }
    @ApiOperation("批量删除操作")
    @PostMapping("/delete")
    public CommonResult<Boolean> deleteApply(@RequestParam("ids") List<Long> ids) {
        boolean removeByIds = reasonService.removeByIds(ids);
        return CommonResult.success(removeByIds) ;
    }
    @ApiOperation("新增操作")
    @PostMapping("/create")
    public CommonResult<Boolean> addReason(@RequestBody OmsOrderReturnReason reason) {
        boolean save = reasonService.addReason(reason);
        return CommonResult.success(save) ;
    }
    @ApiOperation("修改原因操作")
    @PostMapping("/update/{id}")
    public CommonResult<Boolean> updateReason(@PathVariable("id") Long id,
                                                   @RequestBody OmsOrderReturnReason reason) {
        reason.setId(id);
        boolean update = reasonService.updateById(reason);
        if (update) {
            return CommonResult.success(true,"成功") ;
        }
        return CommonResult.failed("失败");
    }
    @ApiOperation("加载原因详细")
    @GetMapping("/{id}")
    public CommonResult<OmsOrderReturnReason> getReasonDetail(@PathVariable("id") Long id) {
        OmsOrderReturnReason reason = reasonService.getById(id);
        return CommonResult.success(reason,"成功") ;
    }
    @ApiOperation("修改原因状态操作")
    @PostMapping("/update/status")
    public CommonResult<Boolean> updateStatus(@RequestParam("ids") List<Long> ids,
                                              @RequestParam("status") Integer status) {
        boolean update = reasonService.updateStatus(ids, status);
        if (update) {
            return CommonResult.success(true,"成功") ;
        }
        return CommonResult.failed("失败");
    }



}

