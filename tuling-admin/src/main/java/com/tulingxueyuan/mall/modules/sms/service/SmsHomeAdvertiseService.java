package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotion;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
public interface SmsHomeAdvertiseService extends IService<SmsHomeAdvertise> {

    Page<SmsHomeAdvertise> fetchList(SmsHomeAdvertise advertise);

    boolean updateStatus(Long id, Integer status);

    SmsHomeAdvertise getHomeAdvertise(Long id);

    Boolean deleteHomeAdvertise(List<Long> ids);

    Boolean updateHomeAdvertise(SmsHomeAdvertise advertise);

    boolean createHomeAdvertise(SmsHomeAdvertise advertise);
}


