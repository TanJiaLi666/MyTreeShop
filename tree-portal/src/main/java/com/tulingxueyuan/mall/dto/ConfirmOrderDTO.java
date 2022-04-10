package com.tulingxueyuan.mall.dto;

import com.tulingxueyuan.mall.modules.oms.model.OmsCartItem;
import com.tulingxueyuan.mall.modules.ums.model.UmsMemberReceiveAddress;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="订单确认DTO")
public class ConfirmOrderDTO {
    @ApiModelProperty("购物车所选商品")
    private List<OmsCartItem> cartList;
    @ApiModelProperty("收获地址")
    private List<UmsMemberReceiveAddress> addressList;
    @ApiModelProperty("商品总价")
    private BigDecimal priceTotal;
    @ApiModelProperty("运费金额")
    private BigDecimal freightAmount;
    @ApiModelProperty("实付金额")
    private BigDecimal payAmount;
    @ApiModelProperty("商品件数")
    private Integer productTotal;
}
