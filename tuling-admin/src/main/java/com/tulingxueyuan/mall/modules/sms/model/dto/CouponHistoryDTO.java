package com.tulingxueyuan.mall.modules.sms.model.dto;

import com.tulingxueyuan.mall.modules.sms.model.SmsCouponHistory;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="优惠卷历史-查询", description="关系DTO")
public class CouponHistoryDTO extends SmsCouponHistory {
    private Integer pageNum;
    private Integer pageSize;
}
