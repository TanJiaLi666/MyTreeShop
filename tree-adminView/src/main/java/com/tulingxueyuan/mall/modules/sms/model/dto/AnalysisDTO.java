package com.tulingxueyuan.mall.modules.sms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="分析结果", description="产品分析")
public class AnalysisDTO {
    Integer sumCount;
    Integer shopSumCount;
    BigDecimal sumPrice;
    BigDecimal shopSumPrice;
    Integer returnSum;
    Integer shopReturnSum;
}
