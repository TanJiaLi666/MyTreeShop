package com.tulingxueyuan.mall.modules.oms.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderSetting;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderSettingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 订单设置表 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-25
 */
@RestController
@RequestMapping("/orderSetting")
public class OmsOrderSettingController {
    @Autowired
    OmsOrderSettingService orderSettingService;

    @ApiOperation("获取商品配置")
    @GetMapping("/{id}")
    public CommonResult<OmsOrderSetting> getOrderSetting(@PathVariable("id") Integer id) {
        OmsOrderSetting orderSetting = orderSettingService.getById(id);
        if (orderSetting == null) {
            return CommonResult.failed("配置获取失败！");
        }
        return CommonResult.success(orderSetting);
    }
    @ApiOperation("更新商品配置")
    @PostMapping("/update/{id}")
    public CommonResult<Boolean> updateOrderSetting(@PathVariable("id") Integer id,@RequestBody OmsOrderSetting orderSetting) {
        boolean update = orderSettingService.saveOrUpdate(orderSetting);
        if (update) {
            return CommonResult.success(update,"保存成功");
        }
        return CommonResult.failed("保存失败");
    }

}

