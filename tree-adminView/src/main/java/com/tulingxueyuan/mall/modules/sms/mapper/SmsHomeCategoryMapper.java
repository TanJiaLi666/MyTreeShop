package com.tulingxueyuan.mall.modules.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeCategory;

/**
 * <p>
 * 首页轮播广告表 Mapper 接口
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
public interface SmsHomeCategoryMapper extends BaseMapper<SmsHomeCategory> {

    SmsHomeCategory getHomeCategory(Long id);
}
