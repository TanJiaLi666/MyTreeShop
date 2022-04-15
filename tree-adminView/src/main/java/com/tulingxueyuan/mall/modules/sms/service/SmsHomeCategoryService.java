package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeCategory;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
public interface SmsHomeCategoryService extends IService<SmsHomeCategory> {

    Page<SmsHomeCategory> fetchList(SmsHomeCategory homeCategory);

    Boolean deleteHomeCategory(List<Long> ids);

    boolean updateStatus(Long id, Integer status);

    SmsHomeCategory getHomeCategory(Long id);

    boolean createHomeCategory(SmsHomeCategory homeCategory);

    Boolean updateHomeCategory(SmsHomeCategory category);


/*    boolean updateStatus(Long id, Integer status);

    SmsHomeAdvertise getHomeAdvertise(Long id);

    Boolean deleteHomeAdvertise(List<Long> ids);

    Boolean updateHomeAdvertise(SmsHomeAdvertise advertise);

    boolean createHomeAdvertise(SmsHomeAdvertise advertise);*/
}


