package com.tulingxueyuan.mall.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.ums.model.UmsMember;
import com.tulingxueyuan.mall.modules.ums.model.dto.UserLoginDTO;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-04-07
 */
public interface UmsMemberService extends IService<UmsMember> {

    UmsMember login(String username, String password);
    UmsMember loadUserByUsername(String username);
    UmsMember getAdminByUsername(String username);

    UmsMember register(UserLoginDTO umsMemberParam);
    UmsMember getMemberId();

    UserDetails loadMemberByUsername(String name);
}
