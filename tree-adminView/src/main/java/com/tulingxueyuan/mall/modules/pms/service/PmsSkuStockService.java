package com.tulingxueyuan.mall.modules.pms.service;

import com.tulingxueyuan.mall.modules.pms.model.PmsSkuStock;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * sku的库存 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
public interface PmsSkuStockService extends IService<PmsSkuStock> {

    /**
     * 商品sku数据查询
     * @param pid
     * @param keyword
     * @return
     */
    List<PmsSkuStock> fetchSkuStockList(Long pid, String keyword);

    /**
     * 更新sku数据表---依据商品id
     * @param skuStockList
     * @param productId
     * @return
     */
    Boolean updateSkuStockList(List<PmsSkuStock> skuStockList, Long productId);
}
