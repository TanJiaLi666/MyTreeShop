package com.tulingxueyuan.mall.modules.oms.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.common.api.ResultCode;
import com.tulingxueyuan.mall.common.exception.Asserts;
import com.tulingxueyuan.mall.common.util.ComConstants;
import com.tulingxueyuan.mall.dto.CartDTO;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsCartItemMapper;
import com.tulingxueyuan.mall.modules.oms.model.OmsCartItem;
import com.tulingxueyuan.mall.modules.oms.service.OmsCartItemService;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.PmsSkuStock;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import com.tulingxueyuan.mall.modules.pms.service.PmsSkuStockService;
import com.tulingxueyuan.mall.modules.ums.model.UmsMember;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-25
 */
@Service
public class OmsCartItemServiceImpl extends ServiceImpl<OmsCartItemMapper, OmsCartItem> implements OmsCartItemService {
    @Autowired
    UmsMemberService memberService;
    @Autowired
    PmsSkuStockService skuStockService;
    @Autowired
    PmsProductService productService;

    @Override
    public Boolean addCar(CartDTO cartDTO) {
        OmsCartItem cartItem = new OmsCartItem();
        BeanUtils.copyProperties(cartDTO, cartItem);
        //查询用户信息
        UmsMember member = memberService.getMemberId();
        cartItem.setMemberId(member.getId());
        cartItem.setMemberNickname(member.getNickname());

        //根据用户信息及购物车信息，校验购物车是否存在相同规格产品
        OmsCartItem cart = selectCart(cartDTO.getProductId(), cartDTO.getProductSkuId(), cartItem.getMemberId());
        if (cart == null) {
            //查询商品属性信息
            PmsSkuStock skuStock = skuStockService.getById(cartItem.getProductSkuId());
            if (skuStock == null) Asserts.fail(ResultCode.VALIDATE_FAILED);
            cartItem.setPrice(skuStock.getPrice());
            cartItem.setSp1(skuStock.getSp1());
            cartItem.setSp2(skuStock.getSp2());
            cartItem.setSp3(skuStock.getSp3());
            cartItem.setProductSkuCode(skuStock.getSkuCode());
            //查询商品信息
            PmsProduct product = productService.getById(cartItem.getProductId());
            if (product == null) Asserts.fail(ResultCode.VALIDATE_FAILED);
            cartItem.setProductPic(product.getPic());
            cartItem.setProductName(product.getName());
            cartItem.setProductSubTitle(product.getSubTitle());
            cartItem.setProductBrand(product.getBrandName());
            cartItem.setProductSn(product.getProductSn());
            cartItem.setProductCategoryId(product.getProductCategoryId());
            //时间处理
            Date date = new Date();
            cartItem.setCreateDate(date);
            cartItem.setModifyDate(date);
            return this.save(cartItem);
        } else {
            cart.setQuantity(cart.getQuantity()+1);
            return updateById(cart);
        }
    }

    @Override
    public Integer sum() {
        UmsMember member = memberService.getMemberId();
        Long memberId = member.getId();
        QueryWrapper<OmsCartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("sum(`quantity`) as sum")
                .lambda()
                .eq(OmsCartItem::getMemberId, memberId);
        List<Map<String, Object>> list = this.baseMapper.selectMaps(queryWrapper);
        if (CollectionUtil.isNotEmpty(list) && list.size() == 1) {
            Map<String, Object> map = list.get(0);
            if (map == null) {
                return 0;
            }
            int sum = Integer.parseInt(map.get("sum").toString());
            return sum;
        }
        return 0;
    }

    @Override
    public List<OmsCartItem> fetchList() {
        UmsMember member = memberService.getMemberId();
        return this.baseMapper.fetchList(member.getId());
    }

    @Override
    public Boolean updateQuantity(Long id, Integer quantity) {
        UpdateWrapper<OmsCartItem> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(OmsCartItem::getQuantity, quantity)
                .eq(OmsCartItem::getId, id);
        return update(updateWrapper);
    }

    @Override
    public Boolean deleteCar(List<Long> ids) {
        return this.removeByIds(ids);
    }

    @Override
    public String handleStock(Long id) {
        OmsCartItem cartItem = this.baseMapper.getStock(id);
        PmsSkuStock skuStock = skuStockService.getById(cartItem.getProductSkuId());
        //购物车库存
        Integer cartStock = cartItem.getStock();
        //真实库存
        Integer productStock = skuStock.getStock()-skuStock.getLockStock();
        if (cartStock > productStock) {
            return cartItem.getProductName();
        }
        return null;
    }

    /**
     * 校验购物车
     * */
    private OmsCartItem selectCart(Long productId, Long productSkuId, Long memberId) {
        QueryWrapper<OmsCartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OmsCartItem::getMemberId, memberId)
                .eq(OmsCartItem::getProductId, productId)
                .eq(OmsCartItem::getProductSkuId, productSkuId);
        return this.getOne(queryWrapper);
    }
}
