package com.tulingxueyuan.mall.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="购物车信息id")
public class CartDTO {
    private Long productId;
    private Long productSkuId;
    private Integer quantity;
}
