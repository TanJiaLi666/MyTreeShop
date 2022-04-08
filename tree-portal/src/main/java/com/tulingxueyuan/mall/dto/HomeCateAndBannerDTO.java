package com.tulingxueyuan.mall.dto;

import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="首页轮播图、分类")
public class HomeCateAndBannerDTO {
    @ApiModelProperty("分类传输DTO")
    private List<HomeCateDTO> homeCateDTO;
    @ApiModelProperty("广告横幅列")
    private List<SmsHomeAdvertise> advertisesList;
}
