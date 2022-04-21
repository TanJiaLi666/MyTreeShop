package com.tulingxueyuan.mall.modules.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.oms.model.UmsMemberReceiveAddress;


import java.util.List;

/**
 * <p>
 * 会员收货地址表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-04-07
 */
public interface UmsMemberReceiveAddressService extends IService<UmsMemberReceiveAddress> {



    Boolean updateAddress(UmsMemberReceiveAddress address);

    Boolean deleteAddress(Long id);
}
