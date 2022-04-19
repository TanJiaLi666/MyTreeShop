package com.tulingxueyuan.mall.modules.home.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="用户统计结果DTO", description="用户信息")
public class AdminDTO {
    @ApiModelProperty("用户注册量")
    private Integer memberSum;
    @ApiModelProperty("今日注册用户")
    private Integer todayMemberSum;
    @ApiModelProperty("昨日注册用户")
    private Integer yesterdayMemberSum;
    @ApiModelProperty("月注册用户")
    private Integer monthMemberSum;
}
