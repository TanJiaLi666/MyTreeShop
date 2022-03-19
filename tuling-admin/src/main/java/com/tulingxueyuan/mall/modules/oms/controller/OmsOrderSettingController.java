package com.tulingxueyuan.mall.modules.oms.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    /**
     * export function getOrderSetting(id) {
     *   return request({
     *     url:'/orderSetting/'+id,
     *     method:'get',
     *   })
     * }
     *  id: null,
     *     flashOrderOvertime: 0,
     *     normalOrderOvertime: 0,
     *     confirmOvertime: 0,
     *     finishOvertime: 0,
     *     commentOvertime: 0
     */
    @GetMapping("/{id}")
    public void getOrderSetting(@PathVariable("id") Integer id) {

    }

}

