package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.tulingxueyuan.mall.modules.pms.model.dto.HomeGoodsSaleDTO;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeCategory;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsHomeCategoryMapper;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-04-05
 */
@Service
public class SmsHomeCategoryServiceImpl extends ServiceImpl<SmsHomeCategoryMapper, SmsHomeCategory> implements SmsHomeCategoryService {

    @Override
    public List<HomeGoodsSaleDTO> getGoodsSaleList() {
        return this.baseMapper.getGoodsSaleList();
    }
}
