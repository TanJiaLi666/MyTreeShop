package com.tulingxueyuan.mall.modules.pms.mapper;

import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCateDTO;

import java.util.List;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
public interface PmsProductCategoryMapper extends BaseMapper<PmsProductCategory> {

    List<PmsProductCateDTO> fetchListWithChildren();
}
