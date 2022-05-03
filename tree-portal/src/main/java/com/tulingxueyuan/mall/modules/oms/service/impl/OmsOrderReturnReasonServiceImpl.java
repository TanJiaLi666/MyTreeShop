package com.tulingxueyuan.mall.modules.oms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsOrderReturnReasonMapper;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnReason;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderReturnReasonService;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    OmsOrderService orderService;
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

    @Override
    public List<OmsOrderReturnReason> getReturnReason() {
        QueryWrapper<OmsOrderReturnReason> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(OmsOrderReturnReason::getStatus, 1)
                .orderByAsc(OmsOrderReturnReason::getSort);
        return this.list(queryWrapper);
    }

    @Override
    @Transactional
    public Boolean addReturnReason(String txt, Long id) {
        QueryWrapper<OmsOrderReturnReason> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(OmsOrderReturnReason::getName, txt);
        OmsOrderReturnReason one = getOne(queryWrapper);
        if (one == null) {
            OmsOrderReturnReason reason = new OmsOrderReturnReason();
            reason.setName(txt);
            reason.setCreateTime(new Date());
            reason.setSort(0);
            reason.setStatus(1);
            reason.setNum(1);
            save(reason);
        }
        else {
            one.setNum(one.getNum()+1);
            updateById(one);
        }
        UpdateWrapper<OmsOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().set(OmsOrder::getStatus, 6).eq(OmsOrder::getId, id);
        orderService.update(updateWrapper);
        return true;
    }
}
