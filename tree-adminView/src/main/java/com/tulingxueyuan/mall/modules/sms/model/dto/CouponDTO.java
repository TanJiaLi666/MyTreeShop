package com.tulingxueyuan.mall.modules.sms.model.dto;

import com.tulingxueyuan.mall.modules.sms.model.SmsCoupon;
import com.tulingxueyuan.mall.modules.sms.model.SmsCouponProductCategoryRelation;
import com.tulingxueyuan.mall.modules.sms.model.SmsCouponProductRelation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="优惠卷-查询", description="关系DTO")
public class CouponDTO extends SmsCoupon {
    private Integer pageNum;
    private Integer pageSize;
    @ApiModelProperty("商品分类列表")
    List<SmsCouponProductCategoryRelation> productCategoryRelationList;
    @ApiModelProperty("商品分类列表")
    List<SmsCouponProductRelation> productRelationList;
}
