package com.tulingxueyuan.mall.modules.oms.model.dto;

import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderItem;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderOperateHistory;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="defaultListQueryDTO传输对象", description="默认列表查询")
public class OmsOrderInfoDTO extends OmsOrder {
    /**
     * 订单商品信息
     */
    private List<OmsOrderItem> orderItemList;
    /**
     * 订单操作历史信息
     */
    private List<OmsOrderOperateHistory> historyList;
}
