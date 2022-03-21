package com.tulingxueyuan.mall.modules.sms.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionProductRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.sms.model.dto.FlashPromotionProductRelationDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表 Mapper 接口
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
public interface SmsFlashPromotionProductRelationMapper extends BaseMapper<SmsFlashPromotionProductRelation> {

    List<FlashPromotionProductRelationDTO> fetchSelectList(FlashPromotionProductRelationDTO flashPromotionProductRelationDTO);

    Page<FlashPromotionProductRelationDTO> fetchList(IPage<?> page, @Param("relationDTO") FlashPromotionProductRelationDTO relationDTO);
}
