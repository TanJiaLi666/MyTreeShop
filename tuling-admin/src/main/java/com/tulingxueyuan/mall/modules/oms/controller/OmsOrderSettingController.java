package com.tulingxueyuan.mall.modules.oms.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
        QueryWrapper<OmsOrderSetting> queryWrapper =  new QueryWrapper<>();
        queryWrapper.lambda().eq(OmsOrderSetting::getUid, id);
        OmsOrderSetting orderSetting = orderSettingService.getOne(queryWrapper);
        if (orderSetting == null) {
            return CommonResult.failed("配置获取失败！");
        }
        return CommonResult.success(orderSetting);
    }
    @ApiOperation("更新商品配置")
    @PostMapping("/update/{id}")
    public CommonResult<Boolean> updateOrderSetting(@PathVariable("id") Long id,@RequestBody OmsOrderSetting orderSetting) {
        boolean update = false;
        QueryWrapper<OmsOrderSetting> queryWrapper =  new QueryWrapper<>();
        queryWrapper.lambda().eq(OmsOrderSetting::getUid, id);
        OmsOrderSetting setId = orderSettingService.getOne(queryWrapper);
        UpdateWrapper<OmsOrderSetting> updateWrapper = new UpdateWrapper<>();
        if (setId!=null) {
            updateWrapper.lambda().eq(OmsOrderSetting::getUid, id);
            update = orderSettingService.update(orderSetting,updateWrapper);
        } else {
            orderSetting.setUid(id);
            orderSettingService.save(orderSetting);
            update = true;
        }
        if (update) {
            return CommonResult.success(update,"保存成功");
        }
        return CommonResult.failed("保存失败");
    }

}

