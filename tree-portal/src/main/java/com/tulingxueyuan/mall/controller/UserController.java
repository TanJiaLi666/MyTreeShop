package com.tulingxueyuan.mall.controller;

import com.tulingxueyuan.mall.common.api.CommonResult;

import com.tulingxueyuan.mall.common.util.ComConstants;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        HashMap<String, Object> map = new HashMap<>();
        map.put(ComConstants.FLAG_MEMBER_USER, login);
        JwtTokenUtil.menberName.set(map);

        System.out.println(session.getId());
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


/*    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAdminInfo() {
        UmsMember UmsMember= (UmsMember) session.getAttribute(ComConstants.FLAG_CURRENT_USER);
        System.out.println(session.getId());
        Map<String, Object> data = new HashMap<>();
        data.put("username", UmsMember.getUsername());
        data.put("menus", roleService.getMenuList(UmsMember.getId()));
        data.put("icon", UmsMember.getIcon());
        List<UmsRole> roleList = adminService.getRoleList(UmsMember.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        return CommonResult.success(data);
    }*/

/*    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
        session.setAttribute(ComConstants.FLAG_CURRENT_USER,null);
        return CommonResult.success(null);
    }*/

/*    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsMember>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<UmsMember> adminList = adminService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }*/

/*    @ApiOperation("获取指定用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsMember> getItem(@PathVariable Long id) {
        UmsMember admin = adminService.getById(id);
        return CommonResult.success(admin);
    }*/

/*    @ApiOperation("修改指定用户信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody UmsMember admin) {
        boolean success = adminService.update(id, admin);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }*/

/*    @ApiOperation("修改指定用户密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@Validated @RequestBody UpdateAdminPasswordParam updatePasswordParam) {
        int status = adminService.updatePassword(updatePasswordParam);
        if (status > 0) {
            return CommonResult.success(status);
        } else if (status == -1) {
            return CommonResult.failed("提交参数不合法");
        } else if (status == -2) {
            return CommonResult.failed("找不到该用户");
        } else if (status == -3) {
            return CommonResult.failed("旧密码错误");
        } else {
            return CommonResult.failed();
        }
    }*/

/*    @ApiOperation("删除指定用户信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        boolean success = adminService.delete(id);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }*/

/*    @ApiOperation("修改帐号状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
        UmsMember UmsMember = new UmsMember();
        UmsMember.setStatus(status);
        boolean success = adminService.update(id,UmsMember);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }*/

  /*  @ApiOperation("给用户分配角色")
    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRole(@RequestParam("adminId") Long adminId,
                                   @RequestParam("roleIds") List<Long> roleIds) {
        int count = adminService.updateRole(adminId, roleIds);
        if (count >= 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }*/
/*
    @ApiOperation("获取指定用户的角色")
    @RequestMapping(value = "/role/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsRole>> getRoleList(@PathVariable Long adminId) {
        List<UmsRole> roleList = adminService.getRoleList(adminId);
        return CommonResult.success(roleList);
    }*/
}
