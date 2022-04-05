package com.tulingxueyuan.mall.modules.pms.mapper;

import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductDetailDTO;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-04-03
 */
public interface PmsProductMapper extends BaseMapper<PmsProduct> {

    ProductDetailDTO fetchList(Long id);
}
