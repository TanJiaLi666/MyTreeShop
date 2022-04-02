package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnReason;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotion;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsFlashPromotionMapper;
import com.tulingxueyuan.mall.modules.sms.service.SmsFlashPromotionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * <p>
 * 限时购表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@Service
public class SmsFlashPromotionServiceImpl extends ServiceImpl<SmsFlashPromotionMapper, SmsFlashPromotion> implements SmsFlashPromotionService {

    @Override
    public Page<SmsFlashPromotion> fetchList(Integer pageNum, Integer pageSize, String keyword) {
        Page<SmsFlashPromotion> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SmsFlashPromotion> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(!StringUtils.isEmpty(keyword),SmsFlashPromotion::getTitle, keyword)
                .orderByAsc(SmsFlashPromotion::getId);
        return this.page(page, queryWrapper);
    }

    @Override
    public Boolean createFlash(SmsFlashPromotion flashPromotion) {
        Date date = new Date();
        flashPromotion.setCreateTime(date);
        return this.save(flashPromotion);
    }

    @Override
    public boolean updateStatus(Long id, Integer status) {
        UpdateWrapper<SmsFlashPromotion> updateWrapper = new UpdateWrapper();
        updateWrapper.lambda()
                .set(SmsFlashPromotion::getStatus, status)
                .eq(SmsFlashPromotion::getId, id);
        return this.update(updateWrapper);
    }
}
