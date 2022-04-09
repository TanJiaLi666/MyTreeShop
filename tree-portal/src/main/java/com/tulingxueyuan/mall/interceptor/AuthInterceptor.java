package com.tulingxueyuan.mall.interceptor;

import com.tulingxueyuan.mall.common.api.ResultCode;
import com.tulingxueyuan.mall.common.exception.ApiException;
import com.tulingxueyuan.mall.common.util.ComConstants;
import com.tulingxueyuan.mall.common.util.JwtTokenUtil;
import com.tulingxueyuan.mall.modules.ums.model.UmsMember;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * 作用： 验证 用户是否登录、菜单资源权限
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private List<String> urls;

    @Autowired
    private UmsMemberService memberService;
    @Autowired
    JwtTokenUtil tokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        //判断是否白名单
        PathMatcher matcher = new AntPathMatcher();
        for (String ignoredUrl : urls) {
            if(matcher.match(ignoredUrl,requestURI)){
                return  true;
            }
        }
        //获取请求头参数，不为空则为登录状态，注册状态
        String token = request.getHeader(tokenHeader);
        if (StringUtils.isEmpty(token) || !token.startsWith(tokenHead)) {
            throw new ApiException(ResultCode.UNAUTHORIZED);
        }
        token = token.substring(tokenHead.length());
        String memberName = tokenUtil.getUserNameFromToken(token);
        if (StringUtils.isEmpty(memberName)) {
            throw new ApiException(ResultCode.UNAUTHORIZED);
        }
        UmsMember member = memberService.getAdminByUsername(memberName);
        if (!StringUtils.isEmpty(member)) {
            HashMap<String, Object> map = new HashMap<>();
            map.put(ComConstants.FLAG_MEMBER_USER, member);
            JwtTokenUtil.menberName.set(map);
            return true;
        }else throw new ApiException(ResultCode.UNAUTHORIZED);
    }


    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
