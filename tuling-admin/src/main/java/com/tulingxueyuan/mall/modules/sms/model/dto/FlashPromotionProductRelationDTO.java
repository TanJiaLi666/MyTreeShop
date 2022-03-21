package com.tulingxueyuan.mall.modules.sms.model.dto;

import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionProductRelation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="活动名称-商品-秒杀时间段", description="关系DTO")
public class FlashPromotionProductRelationDTO extends SmsFlashPromotionProductRelation {
    private Integer pageNum;
    private Integer pageSize;
    @ApiModelProperty(value = "秒杀时间段名称")
    private String name;
    @ApiModelProperty(value = "活动内详细开始时间-范围/天")
    private Date startTime;
    @ApiModelProperty(value = "活动内详细结束时间-范围/天")
    private Date endTime;
    @ApiModelProperty(value = "参与该时间段活动的商品数量")
    private Integer productCount;
    @ApiModelProperty(value = "商品信息")
    private PmsProduct product;
}
