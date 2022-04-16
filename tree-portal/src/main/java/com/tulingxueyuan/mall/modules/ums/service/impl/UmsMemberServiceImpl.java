package com.tulingxueyuan.mall.modules.ums.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.common.exception.ApiException;
import com.tulingxueyuan.mall.common.exception.Asserts;
import com.tulingxueyuan.mall.dto.domain.UserDetailsProperties;
import com.tulingxueyuan.mall.modules.ums.mapper.UmsMemberLoginLogMapper;
import com.tulingxueyuan.mall.modules.ums.mapper.UmsMemberMapper;
import com.tulingxueyuan.mall.modules.ums.model.UmsMember;
import com.tulingxueyuan.mall.modules.ums.model.UmsMemberLoginLog;
import com.tulingxueyuan.mall.modules.ums.model.dto.UserLoginDTO;
import com.tulingxueyuan.mall.modules.ums.service.UmsAdminCacheService;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-04-07
 */
@Service
@Slf4j
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {

    @Autowired
    private UmsMemberLoginLogMapper loginLogMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UmsMember login(String username, String password) {
        //密码需要客户端加密后传递
        UmsMember umsMember=null;
        try {
            UserDetails details = userDetailsService.loadUserByUsername(username);
            umsMember = ((UserDetailsProperties) details).getMember();
            log.debug(umsMember.getUsername()+umsMember.getPassword());
            if(!passwordEncoder.matches(password, umsMember.getPassword())){
                Asserts.fail("密码不正确");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            if (!details.isEnabled()) {
                Asserts.fail("账号禁用");
            }
            insertLoginLog(username);
        } catch (Exception e) {
            Asserts.fail("登录异常:"+e.getMessage());
        }
        return umsMember;
    }


    @Override
    public UmsMember loadUserByUsername(String username){
        //获取用户信息
        UmsMember admin = getAdminByUsername(username);
        if (admin != null) {
            // 查询用户访问资源，暂留， 后续改动
            // List<UmsResource> resourceList = getResourceList(admin.getId());
            return admin;
        }
        throw new ApiException("用户不存在");
    }
   @Override
    public UmsMember getAdminByUsername(String username) {
        UmsMember admin = adminCacheService.getAdmin(username);
        if(admin!=null) return  admin;
        QueryWrapper<UmsMember> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsMember::getUsername,username);
        List<UmsMember> adminList = list(wrapper);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            adminCacheService.setAdmin(admin);
            return admin;
        }
        return null;
    }

    @Override
    public UmsMember register(UserLoginDTO umsMemberParam) {
        UmsMember umsAdmin = new UmsMember();
        BeanUtils.copyProperties(umsMemberParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        QueryWrapper<UmsMember> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsMember::getUsername,umsAdmin.getUsername());
        List<UmsMember> umsAdminList = list(wrapper);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        //String encodePassword = BCrypt.hashpw(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        baseMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public UmsMember getMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsProperties properties = (UserDetailsProperties)authentication.getPrincipal();
        return properties.getMember();
    }

    @Override
    public UserDetails loadMemberByUsername(String name) {
        UmsMember member = getAdminByUsername(name);
        if (member != null) {
            return new UserDetailsProperties(member);
        }
        throw new ApiException("用户名密码错误!");
    }


    private void insertLoginLog(String username) {
        UmsMember admin = getAdminByUsername(username);
        if(admin==null) return;
        UmsMemberLoginLog loginLog = new UmsMemberLoginLog();
        loginLog.setMemberId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);
    }
}
