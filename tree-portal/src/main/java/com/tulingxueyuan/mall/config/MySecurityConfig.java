package com.tulingxueyuan.mall.config;

import com.tulingxueyuan.mall.modules.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends SecurityConfig{
    @Autowired
    UmsMemberService memberService;

    @Bean
    public UserDetailsService userDetailsService() {
        return s -> memberService.loadMemberByUsername(s);
    }
}
