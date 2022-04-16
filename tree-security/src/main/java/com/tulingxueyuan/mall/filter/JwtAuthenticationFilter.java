package com.tulingxueyuan.mall.filter;

import cn.hutool.core.util.StrUtil;
import com.tulingxueyuan.mall.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    JwtTokenUtil tokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头参数，不为空则为登录状态，注册状态
        String token = request.getHeader(tokenHeader);
        if (!StringUtils.isEmpty(token) && token.startsWith(tokenHead)) {
            token = token.substring(tokenHead.length());
            String memberName = tokenUtil.getUserNameFromToken(token);
            if (!StrUtil.isBlank(memberName)) {
                UserDetails member = userDetailsService.loadUserByUsername(memberName);
                if (member != null) {
                    //生成security框架专属token
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member,null,member.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }else {
            filterChain.doFilter(request,response);
        }
    }
}
