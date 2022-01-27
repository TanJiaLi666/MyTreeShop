package com.tulingxueyuan.mall.modules.oms.model.dto;

import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="OmsOrderDeliveryDTO", description="物流信息")
public class OmsOrderDeliveryDTO extends OmsOrder {
    private Long orderId;
    private String address;
}
