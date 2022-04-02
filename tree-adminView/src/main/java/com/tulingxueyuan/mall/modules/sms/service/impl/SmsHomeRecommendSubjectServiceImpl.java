package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeNewProduct;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendProduct;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendSubject;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsHomeRecommendSubjectMapper;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeRecommendSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
    public Page<SmsHomeRecommendSubject> fetchList(SmsHomeRecommendSubject recommendSubject) {
        Page<SmsHomeRecommendSubject> page = new Page<>(recommendSubject.getPageNum(), recommendSubject.getPageSize());
        QueryWrapper<SmsHomeRecommendSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(!StringUtils.isEmpty(recommendSubject.getSubjectName()), SmsHomeRecommendSubject::getSubjectName, recommendSubject.getSubjectName())
                .eq(recommendSubject.getRecommendStatus()!=null, SmsHomeRecommendSubject::getRecommendStatus, recommendSubject.getRecommendStatus())
                .orderByAsc(SmsHomeRecommendSubject::getSort);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        UpdateWrapper<SmsHomeRecommendSubject> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .in(SmsHomeRecommendSubject::getId, ids)
                .set(SmsHomeRecommendSubject::getRecommendStatus, recommendStatus);
        return this.update(updateWrapper);
    }

    @Override
    public boolean updateHomeSubjectSort(Long id, Integer sort) {
        UpdateWrapper<SmsHomeRecommendSubject> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .eq(SmsHomeRecommendSubject::getId , id)
                .set(SmsHomeRecommendSubject::getSort, sort);
        return this.update(updateWrapper);
    }

    @Override
    public Boolean deleteHomeSubject(List<Long> ids) {
        return this.removeByIds(ids);
    }

    @Override
    public boolean createHomeSubject(List<SmsHomeRecommendSubject> recommendSubjects) {
        List<Long> subjectsIds = recommendSubjects.stream().map(o -> o.getSubjectId()).collect(Collectors.toList());
        QueryWrapper<SmsHomeRecommendSubject> queryWrapper = new QueryWrapper<>();
        List<Long> ignoreById = new ArrayList<>();
        for (Long subjectsId : subjectsIds) {
            queryWrapper
                    .lambda()
                    .select(SmsHomeRecommendSubject::getId)
                    .eq(SmsHomeRecommendSubject::getSubjectId, subjectsId);
            if (list(queryWrapper)!=null) {
                for (SmsHomeRecommendSubject subject : list(queryWrapper)) {
                    ignoreById.add(subject.getId());
                }
            }
        }
        if (!CollectionUtils.isEmpty(ignoreById)) {
            deleteHomeSubject(ignoreById);
        }
        recommendSubjects = recommendSubjects.stream().map(o->{
            o.setRecommendStatus(0);
            o.setSort(0);
            return o;
        }).distinct().collect(Collectors.toList());
        return this.saveBatch(recommendSubjects);
    }
}
