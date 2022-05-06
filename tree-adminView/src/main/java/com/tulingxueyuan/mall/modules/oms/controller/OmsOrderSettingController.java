package com.tulingxueyuan.mall.modules.oms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderSetting;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderSettingService;
import com.tulingxueyuan.mall.modules.ums.model.UmsAdmin;
import com.tulingxueyuan.mall.modules.ums.service.UmsAdminService;
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
    @Autowired
    UmsAdminService adminService;
    @ApiOperation("获取商品配置")
    @GetMapping("/")
    public CommonResult<OmsOrderSetting> getOrderSetting() {
        UmsAdmin admin = adminService.getAdmin();
        QueryWrapper<OmsOrderSetting> queryWrapper =  new QueryWrapper<>();
        queryWrapper.lambda().eq(OmsOrderSetting::getUid, admin.getId());
        OmsOrderSetting orderSetting = orderSettingService.getOne(queryWrapper);
        if (orderSetting == null) {
            return CommonResult.failed("配置获取失败！");
        }
        return CommonResult.success(orderSetting);
    }
    @ApiOperation("更新商品配置")
    @PostMapping("/update/")
    public CommonResult<Boolean> updateOrderSetting(@RequestBody OmsOrderSetting orderSetting) {
        UmsAdmin admin = adminService.getAdmin();
        boolean update = false;
        QueryWrapper<OmsOrderSetting> queryWrapper =  new QueryWrapper<>();
        queryWrapper.lambda().eq(OmsOrderSetting::getUid, admin.getId());
        OmsOrderSetting setId = orderSettingService.getOne(queryWrapper);
        UpdateWrapper<OmsOrderSetting> updateWrapper = new UpdateWrapper<>();
        if (setId!=null) {
            updateWrapper.lambda().eq(OmsOrderSetting::getUid, admin.getId());
            update = orderSettingService.update(orderSetting,updateWrapper);
        } else {
            orderSetting.setUid(admin.getId());
            orderSettingService.save(orderSetting);
            update = true;
        }
        if (update) {
            return CommonResult.success(update,"保存成功");
        }
        return CommonResult.failed("保存失败");
    }

}

