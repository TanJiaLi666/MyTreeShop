package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询商品信息传输对象
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PmsProduct查询信息传输对象", description="商品查询信息")
public class PmsProductQueryDTO extends PmsProduct{
    //当前页
    private Integer pageNum;
    //显示页数
    private Integer pageSize;
    //商品名称模糊查询
    private String keyword;

}
