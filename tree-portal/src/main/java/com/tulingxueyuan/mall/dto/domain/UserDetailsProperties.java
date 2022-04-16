package com.tulingxueyuan.mall.dto.domain;

import com.tulingxueyuan.mall.modules.ums.model.UmsMember;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsProperties implements UserDetails {

    private UmsMember member;
    public UserDetailsProperties(UmsMember umsMember) {
        this.member = umsMember;
    }

    /**
     * 前台没有特殊权限，不需要配置额外的资源
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    public UmsMember getMember() {
        return member;
    }

    @Override
    public String getUsername() {
        return member.getUsername();
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
        return member.getStatus()==1;
    }
}
