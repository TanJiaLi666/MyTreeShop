package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
public interface PmsProductService extends IService<PmsProduct> {

    Page fetchList(Integer pageNum, Integer pageSize);
}
