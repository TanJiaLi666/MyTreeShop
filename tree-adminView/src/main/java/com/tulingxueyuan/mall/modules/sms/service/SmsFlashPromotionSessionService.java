package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsCouponProductCategoryRelation;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionProductRelation;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionSession;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.sms.model.dto.FlashPromotionProductRelationDTO;

import java.util.List;

/**
 * <p>
 * 限时购场次表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
public interface SmsFlashPromotionSessionService extends IService<SmsFlashPromotionSession> {

    Page<SmsFlashPromotionSession> fetchList(Integer pageNum, Integer pageSize, String keyword, Integer status);

    boolean updateStatus(Long id, Integer status);

    boolean createSession(SmsFlashPromotionSession flashPromotionSession);

    boolean createSelectSession(List<SmsFlashPromotionProductRelation> list);
}
