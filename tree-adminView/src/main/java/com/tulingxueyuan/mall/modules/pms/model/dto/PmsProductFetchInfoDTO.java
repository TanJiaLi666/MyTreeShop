package com.tulingxueyuan.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PmsProduct响应对象", description="商品信息")
public class PmsProductFetchInfoDTO extends PmsProductInfoDTO{
    /**
     * 一级分类ID
     */
    private Long cateParentId;
}
