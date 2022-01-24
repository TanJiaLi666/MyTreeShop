package com.tulingxueyuan.mall.modules.pms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductMapper;
import com.tulingxueyuan.mall.modules.pms.model.*;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductFetchInfoDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductInfoDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductQueryDTO;
import com.tulingxueyuan.mall.modules.pms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
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
        boolean save = this.save(product);
        Long productId = product.getId();
        //保存成功才做后续相关联的表操作
        if (save) {
            //判断促销类型
            switch (productInfoDTO.getPromotionType()) {
                case 2:
                    //保存会员价格表信息
                    List<PmsMemberPrice> memberPriceList = productInfoDTO.getMemberPriceList();
                    saveManyList(memberPriceList,productId,memberPriceService);
                    break;
                case 3:
                    //保存阶梯价格信息表
                    List<PmsProductLadder> productLadderList = productInfoDTO.getProductLadderList();
                    saveManyList(productLadderList,productId,productLadderService);
                    break;
                case 4:
                    //保存满减价格表信息
                    List<PmsProductFullReduction> productFullReductionList = productInfoDTO.getProductFullReductionList();
                    saveManyList(productFullReductionList,productId,productFullReductionService);
                    break;
            }
            //保存sku表
            List<PmsSkuStock> skuStockList = productInfoDTO.getSkuStockList();
            saveManyList(skuStockList,productId,skuStockService);
            //保存商品属性值
            List<PmsProductAttributeValue> productAttributeValueList = productInfoDTO.getProductAttributeValueList();
            saveManyList(productAttributeValueList,productId,productAttributeValueService);
        }
        return true;
    }

    @Override
    public PmsProductFetchInfoDTO getProduct(Long id) {
        PmsProductFetchInfoDTO list = this.baseMapper.getProduct(id);
        return list;
    }

    /**
     * 商品信息预处理
     * @param list
     * @param productId
     * @param service
     */
    public void saveManyList(List<?> list, Long productId, IService service){
        if (list.isEmpty()) return;
        try {
            for (Object obj: list) {
                Method setProductId = obj.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(obj,productId);
            }
            service.saveBatch(list);
        } catch (Exception e) {
            throw new  RuntimeException(e);
        }
    }
}
