package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnReason;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionProductRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionSession;
import com.tulingxueyuan.mall.modules.sms.model.dto.FlashPromotionProductRelationDTO;

import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
public interface SmsFlashPromotionProductRelationService extends IService<SmsFlashPromotionProductRelation> {

    List<FlashPromotionProductRelationDTO> fetchSelectList(FlashPromotionProductRelationDTO flashPromotionProductRelationDTO);

    Page<FlashPromotionProductRelationDTO> fetchList(FlashPromotionProductRelationDTO relationDTO);
}
