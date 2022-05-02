package com.tulingxueyuan.mall.modules.sms.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="查询条件", description="销量预测查询条件")
public class DataQueryDTO {
    @ApiModelProperty("商品id")
    private Long id;
}
