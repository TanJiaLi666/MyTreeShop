package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 商品一级分类二级分类关系传输对象
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PmsProCateRelationDTO传输对象", description="数据传输对象，商品一级、二级分类")
public class PmsProCateRelationDTO {
    //商品一级分类id
    private Long id;
    //商品一级分类名称
    private String name;
    //商品一级分类对应的二级分类id/name
    private List<PmsProductCategory> children;
}
