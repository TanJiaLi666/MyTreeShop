package com.tulingxueyuan.mall.modules.home.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="商品统计结果DTO", description="商品信息")
public class ProductDTO {
    @ApiModelProperty("商品统计")
    private Integer productSum;
    private Integer stockLowSum;
    private Integer upProductSum;
    private Integer downProductSum;
}
