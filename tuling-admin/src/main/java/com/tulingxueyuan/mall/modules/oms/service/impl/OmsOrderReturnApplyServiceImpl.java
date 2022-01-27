package com.tulingxueyuan.mall.modules.oms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnApply;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsOrderReturnApplyMapper;
import com.tulingxueyuan.mall.modules.oms.model.dto.ApplyDefaultListQueryDTO;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderReturnApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单退货申请 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-25
 */
@Service
public class OmsOrderReturnApplyServiceImpl extends ServiceImpl<OmsOrderReturnApplyMapper, OmsOrderReturnApply> implements OmsOrderReturnApplyService {

    @Override
    public Page<OmsOrderReturnApply> fetchList(ApplyDefaultListQueryDTO applyDefaultListQueryDTO) {
        return null;
    }
}
