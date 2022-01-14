package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PmsProductAttributeCateDTO筛选属性的数据传输对象", description="数据传输对象，商品类型级联属性")
public class PmsProductAttributeCateDTO {
    //商品类型id
    private Long id;
    //商品类型名称
    private String name;
    //商品类型对应的属性/参数
    private List<PmsProductAttribute> productAttributeList;
}
