package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsBrand;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsBrandMapper;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCateDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {

    @Override
    public Page fetchList(Integer pageNum, Integer pageSize,String keyword) {
        Page<PmsBrand> mypage = new Page<>(pageNum,pageSize);
        QueryWrapper<PmsBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByAsc(PmsBrand::getSort);
        if (keyword.isEmpty()){
            return this.page(mypage);
        }else{
            queryWrapper.lambda().like(PmsBrand::getName,keyword);
            return this.page(mypage,queryWrapper);
        }


    }

    @Override
    public Boolean createBrand(PmsBrand pmsBrand) {
        return this.save(pmsBrand);
    }
}
