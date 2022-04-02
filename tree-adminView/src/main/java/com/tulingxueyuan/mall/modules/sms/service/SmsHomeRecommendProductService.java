package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendProduct;

import java.util.List;

/**
 * <p>
 * 人气推荐商品表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
public interface SmsHomeRecommendProductService extends IService<SmsHomeRecommendProduct> {

    Page<SmsHomeRecommendProduct> fetchList(SmsHomeRecommendProduct recommendProduct);

    boolean updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    Boolean deleteHotProduct(List<Long> ids);

    boolean createHotProduct(List<SmsHomeRecommendProduct> newProduct);

    boolean updateHotProductSort(Long id, Integer sort);
}
