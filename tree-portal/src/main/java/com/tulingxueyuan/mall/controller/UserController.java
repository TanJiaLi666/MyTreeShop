package com.tulingxueyuan.mall.controller;

import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.common.util.JwtTokenUtil;
import com.tulingxueyuan.mall.modules.ums.model.UmsMember;
import com.tulingxueyuan.mall.modules.ums.model.dto.UserLoginDTO;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
@Api(tags = "UserController", description = "后台用户管理")
@RequestMapping("/user")
public class UserController {
    @Autowired
    HttpSession session;
    @Autowired
    UmsMemberService memberService;
    @Autowired
    JwtTokenUtil tokenUtil;



    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    @ResponseBody
    public CommonResult login(@Validated UserLoginDTO userLogin) {
        UmsMember login = memberService.login(userLogin.getUsername(), userLogin.getPassword());
        if (login == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        String token = tokenUtil.generateUserNameStr(login.getUsername(), login.getPassword(), login.getNickname());
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        tokenMap.put("tokenHeader", tokenHeader);
        return CommonResult.success(tokenMap, "登录成功");
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    @ResponseBody
    public CommonResult<UmsMember> register(@Validated @RequestBody UserLoginDTO UmsMemberParam) {
        UmsMember umsMember = memberService.register(UmsMemberParam);
        if (umsMember == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(umsMember, "注册成功");
    }


    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping(value = "/info")
    @ResponseBody
    public CommonResult getAdminInfo() {
        UmsMember member = memberService.getMemberId();
        Map<String, Object> data = new HashMap<>();
        data.put("username", member.getNickname());
        data.put("myHeader", member.getIcon());
        return CommonResult.success(data);
    }
}
