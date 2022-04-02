package com.tulingxueyuan.mall.modules.oms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnApply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.oms.model.dto.ApplyDefaultListQueryDTO;

/**
 * <p>
 * 订单退货申请 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-25
 */
public interface OmsOrderReturnApplyService extends IService<OmsOrderReturnApply> {

    /**
     * 分页查询退货列表
     * @param applyDefaultListQueryDTO
     * @return
     */
    Page<OmsOrderReturnApply> fetchList(ApplyDefaultListQueryDTO applyDefaultListQueryDTO);
}
