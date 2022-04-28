package com.tulingxueyuan.mall.modules.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductFetchInfoDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductQueryDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductDTO;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
public interface PmsProductMapper extends BaseMapper<PmsProduct> {

    PmsProductFetchInfoDTO getProduct(Long id);

    void setSale(@Param("id") Long id);

    Page<ProductDTO> fetchList(IPage<PmsProduct> myPage, PmsProductQueryDTO productQueryDTO);
}
