package com.tulingxueyuan.mall.modules.oms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.oms.model.OmsCartItem;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 * 购物车表 Mapper 接口
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-25
 */
public interface OmsCartItemMapper extends BaseMapper<OmsCartItem> {

    List<OmsCartItem> fetchList(Long id);

    OmsCartItem getStock(@Param("id") Long id);
}
