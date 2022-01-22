package com.tulingxueyuan.mall.modules.pms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.*;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductMapper;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductInfoDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductQueryDTO;
import com.tulingxueyuan.mall.modules.pms.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {

    @Autowired
    PmsMemberPriceService memberPriceService;

    @Autowired
    PmsProductLadderService productLadderService;

    @Autowired
    PmsProductFullReductionService productFullReductionService;

    @Autowired
    PmsSkuStockService skuStockService;

    @Autowired
    PmsProductAttributeValueService productAttributeValueService;

    @Override
    public Page fetchList(PmsProductQueryDTO productQueryDTO) {
        Page<PmsProduct> mypage = new Page<>(productQueryDTO.getPageNum(), productQueryDTO.getPageSize());
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<PmsProduct> lambda = queryWrapper.lambda();
        //关键字
        if (!StrUtil.isBlank(productQueryDTO.getKeyword())) {
            lambda.like(PmsProduct::getName, productQueryDTO.getKeyword());
        }
        //商品编号
        if (productQueryDTO.getProductSn() != null) {
            lambda.eq(PmsProduct::getProductSn, productQueryDTO.getProductSn());
        }
        //商品分类id
        if (productQueryDTO.getProductCategoryId() != null && productQueryDTO.getProductCategoryId() >= 0) {
            lambda.eq(PmsProduct::getProductCategoryId, productQueryDTO.getProductCategoryId());
        }
        //上架状态
        if (productQueryDTO.getPublishStatus() != null && productQueryDTO.getPublishStatus() >= 0) {
            lambda.eq(PmsProduct::getPublishStatus, productQueryDTO.getPublishStatus());
        }
        //品牌id
        if (productQueryDTO.getBrandId() != null && productQueryDTO.getBrandId() >= 0) {
            lambda.eq(PmsProduct::getBrandId, productQueryDTO.getBrandId());
        }
        //审核状态
        if (productQueryDTO.getVerifyStatus() != null && productQueryDTO.getVerifyStatus() >= 0) {
            lambda.eq(PmsProduct::getVerifyStatus, productQueryDTO.getVerifyStatus());
        }
        lambda.orderByAsc(PmsProduct::getSort);
        return this.page(mypage, queryWrapper);
    }

    @Override
    public Boolean updateDeleteStatus(List<Long> ids) {
        return this.removeByIds(ids);
    }

    @Override
    public Boolean updateStatus(List<Long> ids, Integer updateStatus, SFunction<PmsProduct,?> getStatus) {
        UpdateWrapper<PmsProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(getStatus,updateStatus)
                .in(PmsProduct::getId,ids);
        return this.update(updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createProduct(PmsProductInfoDTO productInfoDTO) {
        //保存商品基本信息
        PmsProduct product = new PmsProduct();
        BeanUtil.copyProperties(productInfoDTO,product);
        this.save(product);
        //保存会员价格表信息
        List<PmsMemberPrice> memberPriceList = productInfoDTO.getMemberPriceList();
        for (PmsMemberPrice i: memberPriceList) {
            i.setProductId(product.getId());
        }
        memberPriceService.saveBatch(memberPriceList);
        //保存阶梯价格信息表
        List<PmsProductLadder> productLadderList = productInfoDTO.getProductLadderList();
        for (PmsProductLadder i: productLadderList) {
            i.setProductId(product.getId());
        }
        productLadderService.saveBatch(productLadderList);
        //保存满减价格表信息
        List<PmsProductFullReduction> productFullReductionList = productInfoDTO.getProductFullReductionList();
        for (PmsProductFullReduction i: productFullReductionList) {
            i.setProductId(product.getId());
        }
        productFullReductionService.saveBatch(productFullReductionList);
        //保存sku表
        List<PmsSkuStock> skuStockList = productInfoDTO.getSkuStockList();
        for (PmsSkuStock i: skuStockList) {
            i.setProductId(product.getId());
        }
        skuStockService.saveBatch(skuStockList);
        //保存商品属性值
        List<PmsProductAttributeValue> productAttributeValueList = productInfoDTO.getProductAttributeValueList();
        for (PmsProductAttributeValue i: productAttributeValueList) {
            i.setProductId(product.getId());
        }
        productAttributeValueService.saveBatch(productAttributeValueList);
        return true;
    }
}
