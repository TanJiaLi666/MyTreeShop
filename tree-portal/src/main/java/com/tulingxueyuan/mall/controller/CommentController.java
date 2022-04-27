package com.tulingxueyuan.mall.controller;

import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.dto.CommentDTO;
import com.tulingxueyuan.mall.modules.pms.model.PmsComment;
import com.tulingxueyuan.mall.modules.pms.model.PmsCommentReplay;
import com.tulingxueyuan.mall.modules.pms.service.PmsCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "评论Controller")
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    PmsCommentService commentService;

    @ApiOperation(value = "加载评论列表")
    @GetMapping("/list/{id}")
    public CommonResult fetchList(@PathVariable("id") Long id) {
        List<CommentDTO> list = commentService.fetchList(id);
        if (list != null) {
            return CommonResult.success(list, "加载成功！");
        }
        return CommonResult.failed("加载失败！");
    }
    @ApiOperation(value = "评价商品")
    @PostMapping("/opeMain")
    public CommonResult saveComment(PmsComment comment) {
        Boolean save = commentService.saveComment(comment);
        if (save) {
            return CommonResult.success(true, "成功！");
        }
        return CommonResult.failed("失败！");
    }
    @ApiOperation(value = "回复评论")
    @PostMapping("/opeSecondary")
    public CommonResult saveCommentReply(PmsCommentReplay commentReplay) {
        Boolean save = commentService.saveCommentReply(commentReplay);
        if (save) {
            return CommonResult.success(true, "成功！");
        }
        return CommonResult.failed("失败！");
    }
    @ApiOperation(value = "删除评论")
    @PostMapping("/delete/{id}")
    public CommonResult deleteCommentReply(@PathVariable("id") Long id) {
        Boolean delete = commentService.deleteCommentReply(id);
        if (delete) {
            return CommonResult.success(true, "成功！");
        }
        return CommonResult.failed("失败！");
    }
    @ApiOperation(value = "删除回复评论")
    @PostMapping("/deleteRe/{id}")
    public CommonResult deleteReCommentReply(@PathVariable("id") Long id) {
        Boolean delete = commentService.deleteReCommentReply(id);
        if (delete) {
            return CommonResult.success(true, "成功！");
        }
        return CommonResult.failed("失败！");
    }
}
