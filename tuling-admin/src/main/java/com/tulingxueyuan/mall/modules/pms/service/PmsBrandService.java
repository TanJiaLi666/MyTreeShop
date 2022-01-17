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
    Page fetchList(Integer pageNum, Integer pageSize);

    /**
     * 添加品牌信息
     * @param pmsBrand
     * @return
     */
    Boolean createBrand(PmsBrand pmsBrand);
}
