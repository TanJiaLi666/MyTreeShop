package com.tulingxueyuan.mall.modules.sms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="数据结果DTO", description="商品销量预测")
public class DataStatisticsDTO {
    private String time;
    private Integer sale;
    private Double y;
    private Long x;
}
