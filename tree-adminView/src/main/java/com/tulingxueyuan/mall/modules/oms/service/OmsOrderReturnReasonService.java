package com.tulingxueyuan.mall.modules.oms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnReason;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 退货原因表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-25
 */
public interface OmsOrderReturnReasonService extends IService<OmsOrderReturnReason> {

    Page<OmsOrderReturnReason> fetchList(Integer pageNum, Integer pageSize);

    boolean updateStatus(List<Long> ids, Integer status);

    boolean addReason(OmsOrderReturnReason reason);
}
