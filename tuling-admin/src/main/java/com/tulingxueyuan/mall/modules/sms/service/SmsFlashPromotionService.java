package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotion;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 限时购表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
public interface SmsFlashPromotionService extends IService<SmsFlashPromotion> {

    Page<SmsFlashPromotion> fetchList(Integer pageNum, Integer pageSize, String keyword);

    Boolean createFlash(SmsFlashPromotion flashPromotion);

    boolean updateStatus(Long id, Integer status);

}
