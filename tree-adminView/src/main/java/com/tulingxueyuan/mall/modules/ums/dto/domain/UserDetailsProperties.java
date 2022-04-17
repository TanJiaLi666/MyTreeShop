package com.tulingxueyuan.mall.modules.ums.dto.domain;

import com.tulingxueyuan.mall.modules.ums.model.UmsAdmin;
import com.tulingxueyuan.mall.modules.ums.model.UmsRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsProperties implements UserDetails {


    private UmsAdmin admin;
    private List<UmsRole> roleList;

    public UserDetailsProperties(UmsAdmin admin, List<UmsRole> roleList) {
        this.admin = admin;
        this.roleList = roleList;
    }

    /**
     * 前台没有特殊权限，不需要配置额外的资源
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = roleList.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    public UmsAdmin getMember() {
        return admin;
    }

    @Override
    public String getUsername() {
        return admin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * 用户是否启用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return admin.getStatus()==1;
    }
}
