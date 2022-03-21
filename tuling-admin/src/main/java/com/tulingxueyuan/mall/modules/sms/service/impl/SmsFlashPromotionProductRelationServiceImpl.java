package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionProductRelation;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsFlashPromotionProductRelationMapper;
import com.tulingxueyuan.mall.modules.sms.model.dto.FlashPromotionProductRelationDTO;
import com.tulingxueyuan.mall.modules.sms.service.SmsFlashPromotionProductRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@Service
public class SmsFlashPromotionProductRelationServiceImpl extends ServiceImpl<SmsFlashPromotionProductRelationMapper, SmsFlashPromotionProductRelation> implements SmsFlashPromotionProductRelationService {

    @Override
    public List<FlashPromotionProductRelationDTO> fetchSelectList(FlashPromotionProductRelationDTO flashPromotionProductRelationDTO) {
        return this.baseMapper.fetchSelectList(flashPromotionProductRelationDTO);
    }

    @Override
    public Page<FlashPromotionProductRelationDTO> fetchList(FlashPromotionProductRelationDTO relationDTO) {
        Page<FlashPromotionProductRelationDTO> page = new Page<>(relationDTO.getPageNum(), relationDTO.getPageSize());
        return this.baseMapper.fetchList(page, relationDTO);
    }
}
