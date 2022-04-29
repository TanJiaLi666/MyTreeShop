package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsComment;
import com.tulingxueyuan.mall.modules.pms.model.PmsCommentReplay;
import com.tulingxueyuan.mall.modules.pms.service.PmsCommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @ApiOperation("加载评论")
    @GetMapping("/list/{id}")
    public CommonResult<CommonPage> fetchList(@PathVariable("id")Long id, PmsComment comment){
        comment.setProductId(id);
        Page<PmsComment> list = commentService.fetchList(comment);
        return CommonResult.success(CommonPage.restPage(list));
    }
    @ApiOperation("更新状态")
    @PostMapping("/update/status")
    public CommonResult<Boolean> updateStatus(@RequestParam List<Long> ids,@RequestParam Integer showStatus){
        Boolean update = commentService.updateStatus(ids, showStatus);
        return CommonResult.success(update);
    }
    @ApiOperation("删除评论")
    @PostMapping("/update/delete")
    public CommonResult<Boolean> deleteReply(@RequestParam List<Long> ids){
        Boolean delete = commentService.deleteReply(ids);
        return CommonResult.success(delete);
    }
    @ApiOperation("修改排序")
    @PostMapping("/update/sort")
    public CommonResult<Boolean> updateSort(@RequestParam Long id,
                                            @RequestParam Integer sort){
        Boolean updateSort = commentService.updateSort(id, sort);
        return CommonResult.success(updateSort);
    }
    @ApiOperation("管理员发布评论")
    @GetMapping("/get/list/{id}")
    public CommonResult<Page<PmsCommentReplay>> getCommentReply(@PathVariable("id") Long id,
                                                                PmsCommentReplay commentReplay){
        Page<PmsCommentReplay> list = commentService.getCommentReply(id, commentReplay);
        return CommonResult.success(list,"成功");
    }
    @ApiOperation("删除评论")
    @PostMapping("/update/delete/in")
    public CommonResult<Boolean> deleteReplyIn(@RequestParam List<Long> ids){
        Boolean delete = commentService.deleteReplyIn(ids);
        return CommonResult.success(delete);
    }
    @ApiOperation("管理员回复评论")
    @PostMapping("/send/in")
    public CommonResult<Boolean> sendCommentReply(PmsCommentReplay comment){
        Boolean is_success = commentService.sendCommentReply(comment);
        if(is_success){
            return CommonResult.success(true,"成功");
        }
        return CommonResult.failed("失败");
    }
}

