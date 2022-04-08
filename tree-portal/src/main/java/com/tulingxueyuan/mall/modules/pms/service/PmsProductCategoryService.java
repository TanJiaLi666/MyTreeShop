package com.tulingxueyuan.mall.modules.pms.service;

import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.dto.HomeCateDTO;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-04-03
 */
public interface PmsProductCategoryService extends IService<PmsProductCategory> {

    List<HomeCateDTO> getCateList();

}
