package com.tulingxueyuan.mall.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="商品对象DTO")
public class ProductDTO {
    private Long id;
    private String name;
    private String pic;
    private BigDecimal price;
    @ApiModelProperty(value = "促销价格")
    private BigDecimal promotionPrice;
    @ApiModelProperty(value = "市场价")
    private BigDecimal originalPrice;
    @ApiModelProperty(value = "副标题")
    private String subTitle;
    private Integer sub;

}
