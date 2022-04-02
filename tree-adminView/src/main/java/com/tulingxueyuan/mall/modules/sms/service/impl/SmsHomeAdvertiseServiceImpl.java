package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotion;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsHomeAdvertiseMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeBrand;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeAdvertiseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class SmsHomeAdvertiseServiceImpl extends ServiceImpl<SmsHomeAdvertiseMapper, SmsHomeAdvertise> implements SmsHomeAdvertiseService {

    @Override
    public Page<SmsHomeAdvertise> fetchList(SmsHomeAdvertise advertise) {
        Page<SmsHomeAdvertise> page = new Page<>(advertise.getPageNum(), advertise.getPageSize());
        QueryWrapper<SmsHomeAdvertise> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(advertise.getType()!=null, SmsHomeAdvertise::getType, advertise.getType())
                .like(!StringUtils.isEmpty(advertise.getName()), SmsHomeAdvertise::getName, advertise.getName())
                .likeRight(advertise.getEndsTime()!=null, SmsHomeAdvertise::getEndTime, advertise.getEndsTime())
                .orderByAsc(SmsHomeAdvertise::getSort);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean updateStatus(Long id, Integer status) {
        UpdateWrapper<SmsHomeAdvertise> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .in(SmsHomeAdvertise::getId, id)
                .set(SmsHomeAdvertise::getStatus, status);
        return this.update(updateWrapper);
    }

    @Override
    public SmsHomeAdvertise getHomeAdvertise(Long id) {
        return this.getById(id);
    }

    @Override
    public Boolean deleteHomeAdvertise(List<Long> ids) {
        return this.removeByIds(ids);
    }

    @Override
    public Boolean updateHomeAdvertise(SmsHomeAdvertise advertise) {
        return this.updateById(advertise);
    }

    @Override
    public boolean createHomeAdvertise(SmsHomeAdvertise advertise) {
        return this.save(advertise);
    }
}
