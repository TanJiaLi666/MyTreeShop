package com.tulingxueyuan.mall.modules.cms.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 专题商品关系表
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cms_subject_product_relation")
@ApiModel(value="CmsSubjectProductRelation对象", description="专题商品关系表")
public class CmsSubjectProductRelation implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long subjectId;

    private Long productId;


}
