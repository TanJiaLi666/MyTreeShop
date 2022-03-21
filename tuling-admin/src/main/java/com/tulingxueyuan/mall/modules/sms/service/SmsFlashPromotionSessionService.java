package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionSession;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 限时购场次表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
public interface SmsFlashPromotionSessionService extends IService<SmsFlashPromotionSession> {

    Page<SmsFlashPromotionSession> fetchList(Integer pageNum, Integer pageSize);

    boolean updateStatus(Long id, Integer status);

    boolean createSession(SmsFlashPromotionSession flashPromotionSession);
}
