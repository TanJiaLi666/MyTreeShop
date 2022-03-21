package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendProduct;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 人气推荐商品表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
public interface SmsHomeRecommendProductService extends IService<SmsHomeRecommendProduct> {

    Page<SmsHomeRecommendProduct> fetchList(Integer pageNum, Integer pageSize);

}
