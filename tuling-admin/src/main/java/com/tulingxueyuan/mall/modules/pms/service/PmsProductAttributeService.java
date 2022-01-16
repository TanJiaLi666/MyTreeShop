package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCateAttrRelatinDTO;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
public interface PmsProductAttributeService extends IService<PmsProductAttribute> {

    /**
     * 加载商品属性
     * @param cid
     * @param pageNum
     * @param pageSize
     * @param type
     * @return
     */
    Page<PmsProductAttribute> lodingList(Integer cid, Integer pageNum, Integer pageSize, Integer type);

    /**
     * 获取商品属性
     * @param id
     * @return
     */
    PmsProductAttribute getProductAttr(Long id);

    /**
     * 商品属性修改
     * @param productAttribute
     * @return
     */
    Boolean updateProductAttr(PmsProductAttribute productAttribute);

    /**
     * 商品属性添加
     * @param productAttribute
     * @return
     */
    Boolean createProductAttr(PmsProductAttribute productAttribute);

    /**
     * 删除商品属性
     * @param ids
     * @return
     */
    Boolean deleteProductAttr(List<Long> ids);

    /**
     * 商品分类编辑时属性初始化
     * @param productCategoryId
     * @return
     */
    List<PmsProductCateAttrRelatinDTO> getProductAttrInfo(Long productCategoryId);
}
