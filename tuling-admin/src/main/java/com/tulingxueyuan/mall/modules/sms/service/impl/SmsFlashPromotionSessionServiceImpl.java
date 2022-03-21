package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnReason;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotion;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionSession;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsFlashPromotionSessionMapper;
import com.tulingxueyuan.mall.modules.sms.service.SmsFlashPromotionSessionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * <p>
 * 限时购场次表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@Service
public class SmsFlashPromotionSessionServiceImpl extends ServiceImpl<SmsFlashPromotionSessionMapper, SmsFlashPromotionSession> implements SmsFlashPromotionSessionService {

    @Override
    public Page<SmsFlashPromotionSession> fetchList(Integer pageNum, Integer pageSize) {
        Page<SmsFlashPromotionSession> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SmsFlashPromotionSession> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .orderByAsc(SmsFlashPromotionSession::getId);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean updateStatus(Long id, Integer status) {
        UpdateWrapper<SmsFlashPromotionSession> updateWrapper = new UpdateWrapper();
        updateWrapper.lambda()
                .set(SmsFlashPromotionSession::getStatus, status)
                .eq(SmsFlashPromotionSession::getId, id);
        return this.update(updateWrapper);
    }

    @Override
    public boolean createSession(SmsFlashPromotionSession flashPromotionSession) {
        Date date = new Date();
        flashPromotionSession.setCreateTime(date);
        return this.save(flashPromotionSession);
    }
}
