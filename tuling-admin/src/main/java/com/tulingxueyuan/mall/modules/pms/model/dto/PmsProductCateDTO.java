package com.tulingxueyuan.mall.modules.pms.model.dto;


import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 商品分类所属属性
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PmsProductCategory传输对象", description="产品分类")
public class PmsProductCateDTO extends PmsProductCategory {
    private List<Long> productAttributeIdList;
}
