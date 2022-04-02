package com.tulingxueyuan.mall.modules.cms.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 帮助表
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cms_help")
@ApiModel(value="CmsHelp对象", description="帮助表")
public class CmsHelp implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long categoryId;

    private String icon;

    private String title;

    private Integer showStatus;

    private Date createTime;

    private Integer readCount;

    private String content;


}
