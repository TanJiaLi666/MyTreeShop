package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsHomeCategoryMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeCategory;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@Service
public class SmsHomeCategoryServiceImpl extends ServiceImpl<SmsHomeCategoryMapper, SmsHomeCategory> implements SmsHomeCategoryService {


    @Override
    public Page<SmsHomeCategory> fetchList(SmsHomeCategory homeCategory) {
        Page<SmsHomeCategory> page = new Page<>(homeCategory.getPageNum(), homeCategory.getPageSize());
        QueryWrapper<SmsHomeCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(homeCategory.getType()!=null, SmsHomeCategory::getType, homeCategory.getType())
                .like(!StringUtils.isEmpty(homeCategory.getCategoryName()), SmsHomeCategory::getCategoryName, homeCategory.getCategoryName())
                .orderByAsc(SmsHomeCategory::getSort);
        return this.page(page, queryWrapper);
    }
    @Override
    public Boolean deleteHomeCategory(List<Long> ids) {
        return this.removeByIds(ids);
    }
    @Override
    public boolean updateStatus(Long id, Integer status) {
        UpdateWrapper<SmsHomeCategory> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .in(SmsHomeCategory::getId, id)
                .set(SmsHomeCategory::getStatus, status);
        return this.update(updateWrapper);
    }
    @Override
    public SmsHomeCategory getHomeCategory(Long id) {

        return this.getById(id);
    }
    @Override
    public boolean createHomeCategory(SmsHomeCategory category) {
        return this.save(category);
    }
    @Override
    public Boolean updateHomeCategory(SmsHomeCategory category) {
        return this.updateById(category);
    }
  /*







   */
}
