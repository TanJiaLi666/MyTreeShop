package com.tulingxueyuan.mall.modules.ums.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.ums.mapper.UmsMemberReceiveAddressMapper;
import com.tulingxueyuan.mall.modules.ums.model.UmsMember;
import com.tulingxueyuan.mall.modules.ums.model.UmsMemberReceiveAddress;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberReceiveAddressService;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberService;
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
    public Boolean createAddress(UmsMemberReceiveAddress address) {
        address.setDefaultStatus(0);
        UmsMember member = memberService.getMemberId();
        address.setMemberId(member.getId());
        return save(address);
    }

    @Override
    public List<UmsMemberReceiveAddress> fetchList() {
        UmsMember member = memberService.getMemberId();
        QueryWrapper<UmsMemberReceiveAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsMemberReceiveAddress::getMemberId, member.getId());
        List<UmsMemberReceiveAddress> addressList = memberReceiveAddressService.list(queryWrapper);
        return addressList;
    }

    @Override
    public Boolean updateAddress(UmsMemberReceiveAddress address) {
        return updateById(address);
    }

    @Override
    public Boolean deleteAddress(Long id) {
        return removeById(id);
    }
}
