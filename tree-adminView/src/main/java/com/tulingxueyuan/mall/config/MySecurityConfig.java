package com.tulingxueyuan.mall.config;

import com.tulingxueyuan.mall.modules.ums.dto.domain.ResourceVO;
import com.tulingxueyuan.mall.modules.ums.service.UmsAdminService;
import com.tulingxueyuan.mall.securityface.SecurityResourceRoleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends SecurityConfig{
    @Autowired
    UmsAdminService adminService;
    @Bean
    public UserDetailsService userDetailsService() {
        return name -> adminService.loadMemberByUsername(name);
    }
    @Bean
    public SecurityResourceRoleInterface getResource() {
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
}
