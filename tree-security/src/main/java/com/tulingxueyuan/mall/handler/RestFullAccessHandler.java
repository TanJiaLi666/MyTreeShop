package com.tulingxueyuan.mall.handler;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.common.api.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class RestFullAccessHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        //返回相应值
        JSON json = JSONUtil.parse(CommonResult.failed(ResultCode.FORBIDDEN));
        response.getWriter().print(json);
        //关闭writer
        response.getWriter().flush();
    }
    public RestFullAccessHandler getAccessHandler() {
        return new RestFullAccessHandler();
    }
}
