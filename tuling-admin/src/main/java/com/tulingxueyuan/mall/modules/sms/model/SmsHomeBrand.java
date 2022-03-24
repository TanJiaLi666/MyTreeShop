package com.tulingxueyuan.mall.modules.sms.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 首页推荐品牌表
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sms_home_brand")
@ApiModel(value="SmsHomeBrand对象", description="首页推荐品牌表")
public class SmsHomeBrand implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long brandId;

    private String brandName;

    private Integer recommendStatus;

    private Integer sort;

    @TableField(exist = false)
    private Integer pageNum;
    @TableField(exist = false)
    private Integer pageSize;


}
