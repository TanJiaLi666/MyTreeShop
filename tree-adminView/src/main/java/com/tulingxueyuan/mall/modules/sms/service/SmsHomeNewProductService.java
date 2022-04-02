package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeNewProduct;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 新鲜好物表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
public interface SmsHomeNewProductService extends IService<SmsHomeNewProduct> {

    Page<SmsHomeNewProduct> fetchList(SmsHomeNewProduct newProduct);

    boolean createNewProduct(List<SmsHomeNewProduct> newProduct);

    Boolean deleteNewProduct(List<Long> ids);

    boolean updateNewProductSort(Long id, Integer sort);

    boolean updateRecommendStatus(List<Long> ids, Integer recommendStatus);
}
