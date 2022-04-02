package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeNewProduct;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendSubject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页推荐专题表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
public interface SmsHomeRecommendSubjectService extends IService<SmsHomeRecommendSubject> {

    Page<SmsHomeRecommendSubject> fetchList(SmsHomeRecommendSubject recommendSubject);

    boolean updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    boolean updateHomeSubjectSort(Long id, Integer sort);

    Boolean deleteHomeSubject(List<Long> ids);

    boolean createHomeSubject(List<SmsHomeRecommendSubject> recommendSubjects);
}
