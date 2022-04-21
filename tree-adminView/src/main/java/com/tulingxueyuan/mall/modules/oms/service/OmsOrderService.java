package com.tulingxueyuan.mall.modules.oms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.tulingxueyuan.mall.modules.oms.model.dto.DefaultListQueryDTO;
import com.tulingxueyuan.mall.modules.oms.model.dto.OmsOrderDeliveryDTO;
import com.tulingxueyuan.mall.modules.oms.model.dto.OmsOrderInfoDTO;

import java.util.List;

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

    /**
     * 加载订单详细信息
     * @param id
     * @return
     */
    OmsOrderInfoDTO getOrderDetail(Long id);

    /**
     * 更新订单备注
     * @param id
     * @param note
     * @param status
     * @return
     */
    Boolean updateOrderNote(Long id, String note, Integer status);

    /**
     * 删除订单
     * @param ids
     * @return
     */
    Boolean deleteOrder(List<Long> ids);

    /**
     * 保存送货信息
     * @param orderDeliveryDTOS
     * @return
     */
    Boolean deliveryOrder(List<OmsOrderDeliveryDTO> orderDeliveryDTOS);

    Boolean closeOrder(List<Long> ids, String note);

    Boolean cancelOrder(List<Long> ids, String note);

    Boolean receiverInfo(OmsOrder order);

    Boolean updateMoneyInfo(OmsOrder order);
}
