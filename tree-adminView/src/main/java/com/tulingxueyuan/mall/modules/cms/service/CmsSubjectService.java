package com.tulingxueyuan.mall.modules.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.cms.model.CmsSubject;

import java.util.List;

/**
 * <p>
 * 专题表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-25
 */
public interface CmsSubjectService extends IService<CmsSubject> {

    List<CmsSubject> fetchListAll();

    Page<CmsSubject> fetchList(CmsSubject subject);
}
