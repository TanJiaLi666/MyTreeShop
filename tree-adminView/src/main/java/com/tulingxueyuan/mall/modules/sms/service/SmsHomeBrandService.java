package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeBrand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页推荐品牌表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
public interface SmsHomeBrandService extends IService<SmsHomeBrand> {

    Page<SmsHomeBrand> fetchList(SmsHomeBrand brand);

    boolean createHomeBrand(List<SmsHomeBrand> brands);

    boolean deleteHomeBrand(List<Long> ids);

    boolean updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    boolean updateHomeBrandSort(Long id, Integer sort);
}
