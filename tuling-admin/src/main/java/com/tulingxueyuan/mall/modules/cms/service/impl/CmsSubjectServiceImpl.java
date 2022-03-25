package com.tulingxueyuan.mall.modules.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.cms.model.CmsSubject;
import com.tulingxueyuan.mall.modules.cms.mapper.CmsSubjectMapper;
import com.tulingxueyuan.mall.modules.cms.service.CmsSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendSubject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 专题表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-25
 */
@Service
public class CmsSubjectServiceImpl extends ServiceImpl<CmsSubjectMapper, CmsSubject> implements CmsSubjectService {

    @Override
    public List<CmsSubject> fetchListAll() {
        return this.list();
    }

    @Override
    public Page<CmsSubject> fetchList( CmsSubject subject) {
        Page<CmsSubject> page = new Page<>(subject.getPageNum(), subject.getPageSize());
        QueryWrapper<CmsSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(!StringUtils.isEmpty(subject.getKeyword()), CmsSubject::getTitle, subject.getKeyword())
                .eq(subject.getShowStatus()!=null, CmsSubject::getShowStatus, subject.getShowStatus())
                .eq(subject.getRecommendStatus()!=null, CmsSubject::getRecommendStatus, subject.getRecommendStatus());
        return this.page(page, queryWrapper);
    }
}
