package com.tulingxueyuan.mall.handler;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.common.api.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class RestFullAuthHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        //返回相应值
        JSON json = JSONUtil.parse(CommonResult.failed(ResultCode.UNAUTHORIZED));
        response.getWriter().print(json);
        //关闭writer
        response.getWriter().flush();
    }

}
