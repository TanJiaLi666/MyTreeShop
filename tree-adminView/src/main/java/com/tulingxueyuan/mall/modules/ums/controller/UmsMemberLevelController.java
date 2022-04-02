package com.tulingxueyuan.mall.modules.ums.controller;


import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.ums.model.UmsMemberLevel;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 会员等级表 前端控制器
 * </p>
 *
 * @author tanjiali
 * @since 2022-1-17
 */
@RestController
@RequestMapping("/memberLevel")
public class UmsMemberLevelController {

    @Autowired
    UmsMemberLevelService memberLevelService;

    /**
     *   url:'/memberLevel/list',
     *     method:'get',
     *     params:{defaultStatus: 0}
     */
    @GetMapping(value="/list")
    public CommonResult list(@RequestParam(value="defaultStatus",defaultValue = "0") Integer defaultStatus) {

        List<UmsMemberLevel> list= memberLevelService.list(defaultStatus);
        return CommonResult.success(list);
    }
}

