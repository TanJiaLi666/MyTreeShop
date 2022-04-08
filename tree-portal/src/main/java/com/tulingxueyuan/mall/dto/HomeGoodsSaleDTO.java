package com.tulingxueyuan.mall.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="推荐分类传输对象", description="")
public class HomeGoodsSaleDTO {

    private String categoryName;

    private String pic;

    @ApiModelProperty(value = "链接地址")
    private String url;

    private List<ProductDTO> productList;

}
