package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeNewProduct;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendSubject;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsHomeRecommendSubjectMapper;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeRecommendSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 首页推荐专题表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@Service
public class SmsHomeRecommendSubjectServiceImpl extends ServiceImpl<SmsHomeRecommendSubjectMapper, SmsHomeRecommendSubject> implements SmsHomeRecommendSubjectService {

    @Override
    public Page<SmsHomeRecommendSubject> fetchList(Integer pageNum, Integer pageSize) {
        Page<SmsHomeRecommendSubject> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SmsHomeRecommendSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .orderByAsc(SmsHomeRecommendSubject::getSort);
        return this.page(page, queryWrapper);
    }
}
