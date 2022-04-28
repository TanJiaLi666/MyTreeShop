package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.tulingxueyuan.mall.modules.home.service.UmsMemberService;
import com.tulingxueyuan.mall.modules.pms.model.PmsComment;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsCommentMapper;
import com.tulingxueyuan.mall.modules.pms.service.PmsCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.ums.model.UmsAdmin;
import com.tulingxueyuan.mall.modules.ums.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 商品评价表 服务实现类
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
@Service
public class PmsCommentServiceImpl extends ServiceImpl<PmsCommentMapper, PmsComment> implements PmsCommentService {
    @Autowired
    UmsAdminService adminService;

    @Override
    public Boolean sendComment(PmsComment comment) {
        comment.setShowStatus(0);
        comment.setCreateTime(new Date());
        comment.setSort(999);
        UmsAdmin admin = adminService.getAdmin();
        comment.setMemberIp(admin.getId().toString());
        comment.setMemberIcon(admin.getIcon());
        comment.setMemberNickName(admin.getNickName());
        return save(comment);
    }
}
