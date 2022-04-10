package com.tulingxueyuan.mall.controller;

import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.ums.model.UmsMemberReceiveAddress;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberReceiveAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "用户个人信息Controller")
@RequestMapping("/member")
public class MemberController {
    @Autowired
    UmsMemberReceiveAddressService addressService;

    @ApiOperation(value = "添加收货地址")
    @PostMapping("/address/add")
    public CommonResult createAddress(@RequestBody UmsMemberReceiveAddress address) {
        Boolean save = addressService.createAddress(address);
        if (save) {
            return CommonResult.success(true, "添加成功！");
        }
        return CommonResult.failed("失败");
    }
    @ApiOperation(value = "修改收货地址")
    @PostMapping("address/update/{id}")
    public CommonResult updateAddress(@PathVariable("id") Long id, @RequestBody UmsMemberReceiveAddress address) {
        address.setId(id);
        Boolean save = addressService.updateAddress(address);
        if (save) {
            return CommonResult.success(true, "修改成功！");
        }
        return CommonResult.failed("失败");
    }
    @ApiOperation(value = "修改收货地址")
    @PostMapping("address/delete/{id}")
    public CommonResult deleteAddress(@PathVariable("id") Long id) {
        Boolean save = addressService.deleteAddress(id);
        if (save) {
            return CommonResult.success(true, "修改成功！");
        }
        return CommonResult.failed("失败");
    }

    @ApiOperation(value = "加载地址")
    @GetMapping("/address/list")
    public CommonResult fetchList() {
        List<UmsMemberReceiveAddress> list = addressService.fetchList();
        if ( list != null) {
            return CommonResult.success(list, "加载成功！");
        }
        return CommonResult.failed("失败");
    }

}
