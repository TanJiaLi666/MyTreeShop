package com.tulingxueyuan.mall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends SecurityConfig{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
