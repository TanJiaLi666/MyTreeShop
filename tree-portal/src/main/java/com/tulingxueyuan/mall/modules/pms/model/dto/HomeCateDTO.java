package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="首页分类", description="数据传输对象，商品一级、二级分类")
public class HomeCateDTO {
    //商品一级分类id
    private Long id;
    //商品一级分类名称
    private String name;
    //商品一级分类对应的二级分类id/name
    private List<ProductDTO> productList;
}
