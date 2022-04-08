package com.tulingxueyuan.mall.modules.ums.service;


import com.tulingxueyuan.mall.modules.ums.model.UmsMember;

import java.util.List;

/**
 * 后台用户缓存管理Service
 * Created by macro on 2020/3/13.
 */
public interface UmsAdminCacheService {
    /**
     * 删除后台用户缓存
     */
    void delMember(Long adminId);

    /**
     * 删除后台用户资源列表缓存
     */
    void delResourceList(Long adminId);


    /**
     * 获取缓存后台用户信息
     */
    UmsMember getAdmin(String username);

    /**
     * 设置缓存后台用户信息
     */
    void setAdmin(UmsMember admin);

}
