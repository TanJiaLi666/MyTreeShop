package com.tulingxueyuan.mall.modules.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendProduct;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendSubject;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeRecommendSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页推荐专题表 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-21
 */
@RestController
@RequestMapping("/home/recommendSubject")
public class SmsHomeRecommendSubjectController {

    @Autowired
    SmsHomeRecommendSubjectService recommendSubjectService;

    @ApiOperation("加载推荐列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsHomeRecommendSubject>> fetchList(SmsHomeRecommendSubject recommendSubject) {
        Page<SmsHomeRecommendSubject> page = recommendSubjectService.fetchList(recommendSubject);
        return CommonResult.success(CommonPage.restPage(page),"成功加载列表");
    }
    @ApiOperation("修改专题是否推荐")
    @PostMapping("/update/recommendStatus")
    public CommonResult<Boolean> updateRecommendStatus(@RequestParam("recommendStatus") Integer recommendStatus,
                                                       @RequestParam("ids") List<Long> ids) {
        boolean updateStatus = recommendSubjectService.updateRecommendStatus(ids, recommendStatus);
        if (updateStatus) {
            return CommonResult.success(true,"成功") ;
        }
        return CommonResult.failed("失败");
    }
    @ApiOperation("修改专题排序")
    @PostMapping("/update/sort/{id}")
    public CommonResult<Boolean> updateHomeSubjectSort(@PathVariable("id") Long id,
                                                      @RequestParam("sort") Integer sort) {
        boolean updateStatus = recommendSubjectService.updateHomeSubjectSort(id, sort);
        if (updateStatus) {
            return CommonResult.success(true,"成功") ;
        }
        return CommonResult.failed("失败");
    }
    @ApiOperation("专题管理的删除")
    @PostMapping("/delete")
    public CommonResult<Boolean> deleteHomeSubject(@RequestParam("ids") List<Long> ids){
        Boolean isSuccess = recommendSubjectService.deleteHomeSubject(ids);
        if (isSuccess){
            return CommonResult.success(true,"成功删除");
        }
        return CommonResult.failed("删除失败");
    }

    @ApiOperation("添加专题操作")
    @PostMapping("/create")
    public CommonResult<Boolean> createHomeSubject(@RequestBody List<SmsHomeRecommendSubject> recommendSubjects) {
        boolean save = recommendSubjectService.createHomeSubject(recommendSubjects);
        return CommonResult.success(save) ;
    }
}

