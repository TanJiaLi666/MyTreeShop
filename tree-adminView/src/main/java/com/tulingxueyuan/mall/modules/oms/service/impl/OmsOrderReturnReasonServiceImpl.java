package com.tulingxueyuan.mall.modules.oms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnReason;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsOrderReturnReasonMapper;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderReturnReasonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 退货原因表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-25
 */
@Service
public class OmsOrderReturnReasonServiceImpl extends ServiceImpl<OmsOrderReturnReasonMapper, OmsOrderReturnReason> implements OmsOrderReturnReasonService {

    @Override
    public Page<OmsOrderReturnReason> fetchList(Integer pageNum, Integer pageSize) {
        Page<OmsOrderReturnReason> page = new Page<>(pageNum, pageSize);
        QueryWrapper<OmsOrderReturnReason> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .orderByAsc(OmsOrderReturnReason::getSort);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean updateStatus(List<Long> ids , Integer status) {
        UpdateWrapper<OmsOrderReturnReason> updateWrapper = new UpdateWrapper();
        updateWrapper.lambda()
                .set(OmsOrderReturnReason::getStatus, status)
                .in(OmsOrderReturnReason::getId, ids);
        return this.update(updateWrapper);
    }

    @Override
    public boolean addReason(OmsOrderReturnReason reason) {
        Date date = new Date();
        reason.setCreateTime(date);
        return this.save(reason);
    }
}
