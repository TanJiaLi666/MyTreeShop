package com.tulingxueyuan.mall.modules.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsBrand;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductMapper;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductQueryDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jdk.nashorn.internal.objects.annotations.Where;
import org.apache.http.util.TextUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return this.page(mypage, queryWrapper);
    }

    @Override
    public Boolean updateDeleteStatus(List<Long> ids) {
        return this.removeByIds(ids);
    }
}
