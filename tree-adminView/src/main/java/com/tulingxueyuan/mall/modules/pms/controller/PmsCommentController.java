package com.tulingxueyuan.mall.modules.pms.controller;


import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsComment;
import com.tulingxueyuan.mall.modules.pms.service.PmsCommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品评价表 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-01-07
 */
@RestController
@RequestMapping("/comment")
public class PmsCommentController {
    @Autowired
    PmsCommentService commentService;

    @ApiOperation("管理员发布评论")
    @PostMapping("/send")
    public CommonResult<Boolean> sendComment(PmsComment comment){
        Boolean is_success = commentService.sendComment(comment);
        if(is_success){
            return CommonResult.success(true,"成功");
        }
        return CommonResult.failed("失败");
    }

}

