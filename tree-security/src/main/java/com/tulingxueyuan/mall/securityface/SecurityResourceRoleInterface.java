package com.tulingxueyuan.mall.securityface;

import java.util.List;
import java.util.Map;

public interface SecurityResourceRoleInterface {
    /**
     * 资源对象
     * @return
     */
    Map<String, List<String>> getResource();
}
