package com.tulingxueyuan.mall.config;

import com.tulingxueyuan.mall.common.util.JwtTokenUtil;
import com.tulingxueyuan.mall.interceptor.AuthInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 权限验证拦截器
 */
@Configuration
public class GlobalWebAppConfigurer implements WebMvcConfigurer {

    /**
     * 该拦截器主要是为了权限验证
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**");
    }
    /**用户验证拦截器*/
    @Bean
    @ConfigurationProperties(prefix = "secure.ignored")
    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }
}
