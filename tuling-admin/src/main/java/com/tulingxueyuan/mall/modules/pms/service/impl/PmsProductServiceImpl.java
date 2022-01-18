package com.tulingxueyuan.mall.modules.pms.service.impl;

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
        Page<PmsProduct> mypage = new Page<>(productQueryDTO.getPageNum(),productQueryDTO.getPageSize());
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        List<PmsProduct> dept = new ArrayList<>();
        queryWrapper.lambda().eq(PmsProduct::getBrandId,productQueryDTO.getBrandId())
                .eq(PmsProduct::getName,productQueryDTO.getName())
                .eq(PmsProduct::getPublishStatus,productQueryDTO.getPublishStatus())
                .eq(PmsProduct::getVerifyStatus,productQueryDTO.getVerifyStatus())
                .eq(PmsProduct::getProductSn,productQueryDTO.getProductSn())
                .eq(PmsProduct::getProductCategoryId,productQueryDTO.getProductCategoryId());
        return this.page(mypage,queryWrapper);
    }

    /**
     * 判空工具
     * @param list
     * @return
     */
    public Boolean is_exit(List<Object> list){
        while (true){
            for (Object object : list ){
                if(object !=null && object != ""){
                    break;
                }else {
                    return false;
                }
            }
            return true;
        }
    }
}
