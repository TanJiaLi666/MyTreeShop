package com.tulingxueyuan.mall.modules.home.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="订单统计结果DTO", description="订单信息")
public class OrderListDTO {
    @ApiModelProperty("订单表")
    private List<OrderVO> orderList;
    @ApiModelProperty("订单总数")
    private Integer sum;
    @ApiModelProperty("今日订单总数")
    private Integer todaySum;
    @ApiModelProperty("今日销售总额")
    private BigDecimal todaySaleSum;
    @ApiModelProperty("昨日销售总额")
    private BigDecimal yesterdaySaleSum;
    @ApiModelProperty("近七天销售总额")
    private BigDecimal recentSaleSum;

    @ApiModelProperty("订单状态统计")
    private Integer a;
    private Integer b;
    private Integer c;
    private Integer d;
    private Integer e;
    private Integer f;

    @ApiModelProperty("订单曲线图统计")
    //周信息
    private Integer weekOrderSum;
    private Integer lastWeekOrderSum;
    private BigDecimal weekOrderSale;
    private BigDecimal lastWeekOrderSale;
    //月信息
    private Integer monthOrderSum;
    private Integer lastMonthOrderSum;
    private BigDecimal monthOrderSale;
    private BigDecimal lastMonthOrderSale;


}
