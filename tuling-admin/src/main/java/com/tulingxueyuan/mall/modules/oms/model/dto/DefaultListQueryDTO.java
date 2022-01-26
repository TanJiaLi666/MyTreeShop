package com.tulingxueyuan.mall.modules.oms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="defaultListQueryDTO传输对象", description="默认列表查询")
public class DefaultListQueryDTO {
    private Integer pageNum;
    private Integer pageSize;
    private String orderSn;
    private String receiverKeyword;
    private Integer status;
    private Integer orderType;
    private Integer sourceType;
    private String createTime;
}
