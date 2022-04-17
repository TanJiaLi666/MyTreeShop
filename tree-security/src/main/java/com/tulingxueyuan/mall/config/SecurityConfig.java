package com.tulingxueyuan.mall.config;

import com.tulingxueyuan.mall.common.util.JwtTokenUtil;
import com.tulingxueyuan.mall.filter.JwtAuthenticationFilter;
import com.tulingxueyuan.mall.handler.RestFullAccessHandler;
import com.tulingxueyuan.mall.handler.RestFullAuthHandler;
import com.tulingxueyuan.mall.securityface.SecurityResourceRoleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;
import java.util.Map;


public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //非必须自动注入类
    @Autowired(required = false)
    SecurityResourceRoleInterface resourceRoleInterface;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //放行白名单
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        for (String url : getUrlSecurityList().getUrls()) {
            registry.antMatchers(url).permitAll();
        }
        //动态角色资源分配
        if (resourceRoleInterface != null) {
            Map<String, List<String>> resourceMap = resourceRoleInterface.getResource();
            for (String url : resourceMap.keySet()) {
                // todo Object数组转String数组
                List<String> res = resourceMap.get(url);
                registry.antMatchers(url).hasAnyAuthority(res.toArray(new String[res.size()]));
            }
        }
        //允许跨域请求 OPTION请求
        registry.antMatchers(HttpMethod.OPTIONS).permitAll();
        //其他请求需要身份验证
        registry
                .anyRequest()
                .authenticated()
                .and()
                    .cors()
                .and()
                    //关闭CSRF请求伪造
                    .csrf()
                    .disable()
                    //禁止session
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //自定义处理类
                .and()
                    .exceptionHandling()
                    //没权限
                    .accessDeniedHandler(getAccessHandler())
                    //没登录
                    .authenticationEntryPoint(getAuthHandler())
                .and()
                    //在security的配置中，添加过滤器，且在最前面，默认第一个是UsernamePasswordAuthenticationFilter.class，所以插入在它之前
                    .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    public SecurityIgnore getUrlSecurityList() {
        return new SecurityIgnore();
    }

    @Bean
    public PasswordEncoder passWordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }
    @Bean
    public RestFullAuthHandler getAuthHandler() {
        return new RestFullAuthHandler();
    }
    @Bean
    public RestFullAccessHandler getAccessHandler() {
        return new RestFullAccessHandler();
    }
}
