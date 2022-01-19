package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.tulingxueyuan.mall.modules.pms.model.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PmsProduct信息传输对象", description="添加|修改商品信息")
public class PmsProductInfoDTO extends PmsProduct {

    //会员价格表
    PmsMemberPrice memberPriceList;

    //商品属性值表
    PmsProductAttributeValue productAttributeValueList;

    //商品满减表
    PmsProductFullReduction productFullReductionList;

    //阶梯价格表
   PmsProductLadder productLadderList;

    //属性值的详细信息，sku表
    PmsSkuStock skuStockList;
}
