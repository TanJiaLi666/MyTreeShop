package com.tulingxueyuan.mall.config;

import com.tulingxueyuan.mall.common.util.JwtTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 权限验证拦截器
 */
@Configuration
public class GlobalWebAppConfigurer implements WebMvcConfigurer {
}
