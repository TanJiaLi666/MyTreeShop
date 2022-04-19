package com.tulingxueyuan.mall.modules.home.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="退货订单统计结果DTO", description="退货订单信息")
public class ReturnOrderDTO {
    @ApiModelProperty("退货状态的数据")
    private Integer return_a;
    private Integer return_b;
    private Integer return_c;
    private Integer return_d;
}
