package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeValue;
import com.tulingxueyuan.mall.modules.pms.model.PmsSkuStock;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="商品信息DTO")
public class ProductDetailDTO extends PmsProduct {
    private List<PmsProductAttributeValue> attributeList;
    private List<PmsSkuStock> skuStockList;
}
