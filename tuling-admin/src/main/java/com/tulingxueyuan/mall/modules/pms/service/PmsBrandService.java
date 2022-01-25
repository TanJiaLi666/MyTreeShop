package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsBrand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCateDTO;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
public interface PmsBrandService extends IService<PmsBrand> {

    /**
     * 加载品牌列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page fetchList(Integer pageNum, Integer pageSize,String keyword);

    /**
     * 添加品牌信息
     * @param pmsBrand
     * @return
     */
    Boolean createBrand(PmsBrand pmsBrand);

    /**
     * 加载品牌详细信息
     * @param id
     * @return
     */
    PmsBrand fetchSkuStockList(Long id);

    /**
     * 更新品牌
     * @param pmsBrand
     * @return
     */
    Boolean updateBrand(PmsBrand pmsBrand);

    /**
     * 删除品牌
     * @param id
     * @return
     */
    Boolean deleteBrand(Long id);
}
