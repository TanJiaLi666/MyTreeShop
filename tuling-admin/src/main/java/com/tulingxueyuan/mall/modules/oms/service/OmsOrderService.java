package com.tulingxueyuan.mall.modules.oms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.oms.model.dto.DefaultListQueryDTO;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-25
 */
public interface OmsOrderService extends IService<OmsOrder> {


    /**
     * 分页查询订单列表
     * @param defaultListQueryDTO
     * @return
     */
    Page<OmsOrder> fetchList(DefaultListQueryDTO defaultListQueryDTO);




}
