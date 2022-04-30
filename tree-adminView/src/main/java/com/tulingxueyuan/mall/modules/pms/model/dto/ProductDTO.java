package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PmsProductCategory传输对象", description="商品包装对象")
public class ProductDTO extends PmsProduct {
    private Integer commentSum;
    private BigDecimal score;
}
