package com.tulingxueyuan.mall.modules.oms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnApply;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsOrderReturnApplyMapper;
import com.tulingxueyuan.mall.modules.oms.model.dto.ApplyDefaultListQueryDTO;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderReturnApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

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
        String createTime = applyDefaultListQueryDTO.getCreateTime();
        String handleMan = applyDefaultListQueryDTO.getHandleMan();
        String handleTime = applyDefaultListQueryDTO.getHandleTime();
        Long id = applyDefaultListQueryDTO.getId();
        Integer pageNum = applyDefaultListQueryDTO.getPageNum();
        Integer pageSize = applyDefaultListQueryDTO.getPageSize();
        Integer status = applyDefaultListQueryDTO.getStatus();
        Page<OmsOrderReturnApply> page = new Page<>(pageNum,pageSize);
        QueryWrapper<OmsOrderReturnApply> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(!StringUtils.isEmpty(id),OmsOrderReturnApply::getId, id)
                .like(!StringUtils.isEmpty(createTime),OmsOrderReturnApply::getCreateTime, createTime)
                .eq(!StringUtils.isEmpty(handleMan),OmsOrderReturnApply::getHandleMan, handleMan)
                .like(!StringUtils.isEmpty(handleTime),OmsOrderReturnApply::getHandleTime, handleTime)
                .eq(!StringUtils.isEmpty(status),OmsOrderReturnApply::getStatus, status)
                .orderByAsc(OmsOrderReturnApply::getCreateTime);
        return this.page(page, queryWrapper);
    }
}
