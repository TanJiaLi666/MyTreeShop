package com.tulingxueyuan.mall.modules.home.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="订单统计结果DTO", description="订单信息")
public class OrderVO {
    private String date;
    private Integer orderCount;
    private BigDecimal orderAmount;
}
