package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductQueryDTO;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
public interface PmsProductService extends IService<PmsProduct> {

    /**
     * 商品查询
     * @param productQueryDTO
     * @return
     */
    Page fetchList(PmsProductQueryDTO productQueryDTO);

    /**
     * 商品删除
     * @param ids
     * @return
     */
    Boolean updateDeleteStatus(List<Long> ids);
}
