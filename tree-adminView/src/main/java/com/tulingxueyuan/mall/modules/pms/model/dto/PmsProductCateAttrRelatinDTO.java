package com.tulingxueyuan.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品分类与筛选属性的关系
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PmsProductCategory传输对象", description="编辑商品分类的筛选属性信息初始化")
public class PmsProductCateAttrRelatinDTO {
    //商品分类id
    private Long attributeCategoryId;
    //商品属性id
    private Long attributeId;
}
