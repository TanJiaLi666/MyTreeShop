package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.dto.ProductDetailDTO;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-04-03
 */
public interface PmsProductService extends IService<PmsProduct> {

    ProductDetailDTO fetchList(Long id);

    List<PmsProduct> search(String keyword);


    String getProductName(Long productId);
}
