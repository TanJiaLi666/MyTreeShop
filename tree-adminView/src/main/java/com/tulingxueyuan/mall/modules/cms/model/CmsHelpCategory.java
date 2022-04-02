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
 * 帮助分类表
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cms_help_category")
@ApiModel(value="CmsHelpCategory对象", description="帮助分类表")
public class CmsHelpCategory implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    @ApiModelProperty(value = "分类图标")
    private String icon;

    @ApiModelProperty(value = "专题数量")
    private Integer helpCount;

    private Integer showStatus;

    private Integer sort;


}
