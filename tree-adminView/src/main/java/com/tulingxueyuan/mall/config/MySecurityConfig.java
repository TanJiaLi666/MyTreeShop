package com.tulingxueyuan.mall.config;

import com.tulingxueyuan.mall.dynamicSecurity.DynamicSecurityService;
import com.tulingxueyuan.mall.modules.ums.dto.domain.ResourceVO;
import com.tulingxueyuan.mall.modules.ums.service.UmsAdminService;
import com.tulingxueyuan.mall.modules.ums.service.UmsResourceService;
import com.tulingxueyuan.mall.securityface.SecurityResourceRoleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends SecurityConfig{

    @Bean
    public UserDetailsService userDetailsService(UmsAdminService adminService) {
        return name -> adminService.loadMemberByUsername(name);
    }
    //静态获取(暂作废)未启用
    //@Bean
    public SecurityResourceRoleInterface getResource(UmsAdminService adminService) {
        return () -> {
            List<ResourceVO> list = adminService.getResource();
            Map<String, List<String>> map = new HashMap<>();
            for (ResourceVO resourceVO : list) {
                List<String> roleNamelist= resourceVO.getRoleList()
                        .stream()
                        .map(role-> role.getName())
                        .collect(Collectors.toList());
                map.put(resourceVO.getUrl(), roleNamelist);
            }
            return map;
        };
    }
    // 获取最新的资源角色信息(动态)
    @Bean("dynamicSecurityService")
    public DynamicSecurityService dynamicSecurityService(UmsResourceService umsResourceService) {
        return () -> {
            Map<RequestMatcher, List<ConfigAttribute>> map = new ConcurrentHashMap<>();
            List<ResourceVO> list= umsResourceService.getAllResourceRole();
            for (ResourceVO resource : list) {
                // 通配符匹配器
                map.put(new AntPathRequestMatcher(resource.getUrl()),
                        // 所有角色信息
                        resource.getRoleList().stream()
                                .map(role-> new org.springframework.security.access.SecurityConfig(role.getName()))
                                .collect(Collectors.toList())
                );
            }
            return map;
        };
    }
}
