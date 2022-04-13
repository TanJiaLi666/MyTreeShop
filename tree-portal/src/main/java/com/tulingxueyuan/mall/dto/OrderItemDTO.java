package com.tulingxueyuan.mall.dto;

import com.tulingxueyuan.mall.modules.oms.model.OmsOrderItem;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="订单商品核对信息ID")
public class OrderItemDTO {
    private List<OmsOrderItem> orderItemList;
    private String receiverProvince;
    private String receiverCity;
    private String receiverRegion;
    private String receiverDetailAddress;
    private BigDecimal payAmount;
    private String orderSn;
    private Integer normalOrderOvertime;

    //订单列表冗余使用->
    private String createTime;
    private String receiverName;
    private Integer status;
    private Long id;
    private BigDecimal totalAmount;

}
