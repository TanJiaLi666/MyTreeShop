package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnReason;
import com.tulingxueyuan.mall.modules.sms.model.SmsCouponProductCategoryRelation;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotion;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionProductRelation;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionSession;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsFlashPromotionSessionMapper;
import com.tulingxueyuan.mall.modules.sms.model.dto.FlashPromotionProductRelationDTO;
import com.tulingxueyuan.mall.modules.sms.service.SmsFlashPromotionProductRelationService;
import com.tulingxueyuan.mall.modules.sms.service.SmsFlashPromotionSessionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    SmsFlashPromotionProductRelationService relationService;
    @Override
    public Page<SmsFlashPromotionSession> fetchList(Integer pageNum, Integer pageSize, String keyword, Integer status) {
        Page<SmsFlashPromotionSession> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SmsFlashPromotionSession> queryWrapper = new QueryWrapper<>();
        if(status==1) {
            queryWrapper.lambda()
                    .like(!StringUtils.isEmpty(keyword),SmsFlashPromotionSession::getName, keyword)
                    .eq(SmsFlashPromotionSession::getStatus,1)
                    .orderByAsc(SmsFlashPromotionSession::getId);
        }else {
            queryWrapper.lambda()
                    .like(!StringUtils.isEmpty(keyword),SmsFlashPromotionSession::getName, keyword)
                    .orderByAsc(SmsFlashPromotionSession::getId);
        }
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

    @Override
    public boolean createSelectSession(List<SmsFlashPromotionProductRelation> list) {
        List<Long> promotionIds = list.stream().map(SmsFlashPromotionProductRelation::getFlashPromotionId).collect(Collectors.toList());
        List<Long> promotionSessionId = list.stream().map(SmsFlashPromotionProductRelation::getFlashPromotionSessionId).collect(Collectors.toList());
        QueryWrapper<SmsFlashPromotionProductRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .in(SmsFlashPromotionProductRelation::getFlashPromotionId,promotionIds)
                .in(SmsFlashPromotionProductRelation::getFlashPromotionSessionId,promotionSessionId);
        List<SmsFlashPromotionProductRelation> relationList = relationService.list(queryWrapper);
        if (CollectionUtils.isEmpty(relationList)) {
            return relationService.saveBatch(list);
        } else {
            return false;
        }
    }
}
