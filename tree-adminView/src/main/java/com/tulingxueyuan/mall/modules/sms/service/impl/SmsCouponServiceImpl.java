package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.sms.model.SmsCoupon;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsCouponMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsCouponProductCategoryRelation;
import com.tulingxueyuan.mall.modules.sms.model.SmsCouponProductRelation;
import com.tulingxueyuan.mall.modules.sms.model.dto.CouponDTO;
import com.tulingxueyuan.mall.modules.sms.service.SmsCouponProductCategoryRelationService;
import com.tulingxueyuan.mall.modules.sms.service.SmsCouponProductRelationService;
import com.tulingxueyuan.mall.modules.sms.service.SmsCouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 优惠卷表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@Service
public class SmsCouponServiceImpl extends ServiceImpl<SmsCouponMapper, SmsCoupon> implements SmsCouponService {
    @Autowired
    SmsCouponProductCategoryRelationService categoryRelationService;
    @Autowired
    SmsCouponProductRelationService productRelationService;

    @Override
    public Page<SmsCoupon> fetchList(CouponDTO couponDTO) {
        Page<SmsCoupon> page = new Page<>(couponDTO.getPageNum(), couponDTO.getPageSize());
        QueryWrapper<SmsCoupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(!StringUtils.isEmpty(couponDTO.getName()),SmsCoupon::getName,couponDTO.getName())
                .eq(couponDTO.getType()!=null,SmsCoupon::getType,couponDTO.getType())
                .orderByAsc(SmsCoupon::getStartTime);
        return this.page(page, queryWrapper);
    }

    @Override
    @Transactional
    public boolean createCoupon(CouponDTO couponDTO) {
        if (couponDTO==null) {
            return false;
        }
        SmsCoupon smsCoupon = new SmsCoupon();
        BeanUtils.copyProperties(couponDTO, smsCoupon);
        /*此算法时间复杂度过高，成品时需要调整*/
        //分三次插入
        //todo 插入优惠券（主表）获得主键
        this.baseMapper.insert(smsCoupon);
        Long couponId = smsCoupon.getId();
        //todo 处理数据层，将主键赋值给关系表中优惠券id
        List<SmsCouponProductCategoryRelation> productCategoryRelationList = couponDTO.getProductCategoryRelationList();
        productCategoryRelationList = productCategoryRelationList.stream().map(o->{
            o.setCouponId(couponId);
            return o;
        }).distinct().collect(Collectors.toList());
        List<SmsCouponProductRelation> productRelationList = couponDTO.getProductRelationList();
        productRelationList = productRelationList.stream().map(o->{
            o.setCouponId(couponId);
            return o;
        }).distinct().collect(Collectors.toList());
        //todo 插入关系表
        categoryRelationService.saveBatch(productCategoryRelationList);
        productRelationService.saveBatch(productRelationList);
        return true;
    }

    @Override
    @Transactional
    public CouponDTO getCoupon(Long id) {
        CouponDTO couponDTO = new CouponDTO();

        SmsCoupon coupon = this.baseMapper.selectById(id);
        BeanUtils.copyProperties(coupon, couponDTO);

        QueryWrapper<SmsCouponProductCategoryRelation> queryWrapper1 = relation(id, SmsCouponProductCategoryRelation::getCouponId);
        List<SmsCouponProductCategoryRelation> categoryRelation = categoryRelationService.list(queryWrapper1);

        QueryWrapper<SmsCouponProductRelation> queryWrapper2 = relation(id, SmsCouponProductRelation::getCouponId);
        List<SmsCouponProductRelation> productRelations = productRelationService.list(queryWrapper2);

        couponDTO.setProductCategoryRelationList(categoryRelation);
        couponDTO.setProductRelationList(productRelations);

        return couponDTO;
    }

    @Override
    @Transactional
    public boolean updateCoupon(CouponDTO couponDTO) {
        //todo 先删后插，用于前端与数据库数据同步时使用
        deleteCoupon(couponDTO.getId());
        createCoupon(couponDTO);
        return true;
    }


    @Override
    @Transactional
    public boolean deleteCoupon(Long id) {
        this.baseMapper.deleteById(id);
        QueryWrapper<SmsCouponProductCategoryRelation> queryWrapper1 = relation(id, SmsCouponProductCategoryRelation::getCouponId);
        categoryRelationService.remove(queryWrapper1);
        QueryWrapper<SmsCouponProductRelation> queryWrapper2 = relation(id, SmsCouponProductRelation::getCouponId);
        productRelationService.remove(queryWrapper2);
        return true;
    }

    /**
     * 创建构造器查询
     * @param id
     * @param sFunction
     * @param <T>
     * @return
     */
    private <T> QueryWrapper<T> relation(Long id , SFunction<T,?> sFunction) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(sFunction,id);
        return queryWrapper;
    }

}
