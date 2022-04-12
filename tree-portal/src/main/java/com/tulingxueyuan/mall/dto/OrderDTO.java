package com.tulingxueyuan.mall.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="订单提交信息ID")
public class OrderDTO {
    private List<Long> itemIds;     // 购物id
    private Long memberReceiveAddressId;  // 地址id
    private Integer payType;  // 支付类型（支付宝）
}
