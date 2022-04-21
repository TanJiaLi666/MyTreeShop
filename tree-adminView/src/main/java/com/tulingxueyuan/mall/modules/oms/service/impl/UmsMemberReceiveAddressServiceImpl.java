package com.tulingxueyuan.mall.modules.oms.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.home.service.UmsMemberService;
import com.tulingxueyuan.mall.modules.oms.mapper.UmsMemberReceiveAddressMapper;
import com.tulingxueyuan.mall.modules.oms.model.UmsMemberReceiveAddress;
import com.tulingxueyuan.mall.modules.oms.service.UmsMemberReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会员收货地址表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-04-07
 */
@Service
public class UmsMemberReceiveAddressServiceImpl extends ServiceImpl<UmsMemberReceiveAddressMapper, UmsMemberReceiveAddress> implements UmsMemberReceiveAddressService {

    @Autowired
    UmsMemberService memberService;
    @Autowired
    UmsMemberReceiveAddressService memberReceiveAddressService;
    @Override
    public Boolean updateAddress(UmsMemberReceiveAddress address) {
        return updateById(address);
    }

    @Override
    public Boolean deleteAddress(Long id) {
        return removeById(id);
    }
}
