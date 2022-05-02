package com.tulingxueyuan.mall.modules.pms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
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
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductDTO;
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
    public Page<ProductDTO> fetchList(PmsProductQueryDTO productQueryDTO) {
        Page<PmsProduct> myPage = new Page<>(productQueryDTO.getPageNum(), productQueryDTO.getPageSize());
        return this.baseMapper.fetchList(myPage, productQueryDTO);
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
        List<PmsSkuStock> skuStockList = productInfoDTO.getSkuStockList();
        int sum = skuStockList.stream().mapToInt(PmsSkuStock::getLowStock).sum();
        //保存商品基本信息
        PmsProduct product = new PmsProduct();
        BeanUtil.copyProperties(productInfoDTO,product);
        product.setLowStock(sum);
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
            saveManyList(skuStockList,productId,skuStockService);
            //保存商品属性值
            List<PmsProductAttributeValue> productAttributeValueList = productInfoDTO.getProductAttributeValueList();
            saveManyList(productAttributeValueList,productId,productAttributeValueService);
            return true;
        }
        return false;
    }

    @Override
    public PmsProductFetchInfoDTO getProduct(Long id) {
        //维护商品销量
        this.baseMapper.setSale(id);
        PmsProductFetchInfoDTO list = this.baseMapper.getProduct(id);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateProduct(PmsProductInfoDTO productParam) {
        //保存商品基本信息
        PmsProduct product = new PmsProduct();
        BeanUtil.copyProperties(productParam,product);
        boolean update = this.updateById(product);
        Long productId = product.getId();
        //保存成功才做后续相关联的表操作
        if (update) {
            //判断促销类型
            switch (productParam.getPromotionType()) {
                case 2:
                    //保存会员价格表信息
                    List<PmsMemberPrice> memberPriceList = productParam.getMemberPriceList();
                    deleteManyList(productId,memberPriceService);
                    saveManyList(memberPriceList,productId,memberPriceService);
                    break;
                case 3:
                    //保存阶梯价格信息表
                    List<PmsProductLadder> productLadderList = productParam.getProductLadderList();
                    deleteManyList(productId,productLadderService);
                    saveManyList(productLadderList,productId,productLadderService);
                    break;
                case 4:
                    //保存满减价格表信息
                    List<PmsProductFullReduction> productFullReductionList = productParam.getProductFullReductionList();
                    deleteManyList(productId,productFullReductionService);
                    saveManyList(productFullReductionList,productId,productFullReductionService);
                    break;
            }
            //保存sku表
            List<PmsSkuStock> skuStockList = productParam.getSkuStockList();
            deleteManyList(productId,skuStockService);
            saveManyList(skuStockList,productId,skuStockService);
            //保存商品属性值
            List<PmsProductAttributeValue> productAttributeValueList = productParam.getProductAttributeValueList();
            deleteManyList(productId,productAttributeValueService);
            saveManyList(productAttributeValueList,productId,productAttributeValueService);
            return true;
        }
        return false;
    }

    @Override
    public List<PmsProduct> fetchSimpleList(PmsProductQueryDTO productQueryDTO) {
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("id,name,product_sn")
                .lambda()
                .like(StrUtil.isNotBlank(productQueryDTO.getKeyword()),PmsProduct::getName,productQueryDTO.getKeyword())
                .or()
                .like(StrUtil.isNotBlank(productQueryDTO.getKeyword()),PmsProduct::getKeywords,productQueryDTO.getKeyword())
                .or()
                .like(StrUtil.isNotBlank(productQueryDTO.getKeyword()),PmsProduct::getProductSn,productQueryDTO.getKeyword());
        return this.list(queryWrapper);
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
                //在修改时需要清除主键id
                Method setId = obj.getClass().getMethod("setId", Long.class);
                setId.invoke(obj,(Long)null);
                setProductId.invoke(obj,productId);
            }
            service.saveBatch(list);
        } catch (Exception e) {
            throw new  RuntimeException(e);
        }
    }

    /**
     * 更新前的删除操作
     * @param productId
     * @param iService
     */
    public void deleteManyList(Long productId, IService iService) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_id", productId);
        iService.remove(queryWrapper);
    }
}
