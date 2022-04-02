package com.tulingxueyuan.mall.modules.oms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="applyDefaultListQueryDT对象", description="申请退款加载列表")
public class ApplyDefaultListQueryDTO {
    private Integer pageNum;
    private Integer pageSize;
    private Long id;
    private String receiverKeyword;
    private Integer status;
    private String createTime;
    private String handleMan;
    private String handleTime;
}
