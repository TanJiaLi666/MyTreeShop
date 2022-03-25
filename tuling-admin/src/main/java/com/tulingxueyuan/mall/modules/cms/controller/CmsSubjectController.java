package com.tulingxueyuan.mall.modules.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.cms.model.CmsSubject;
import com.tulingxueyuan.mall.modules.cms.service.CmsSubjectService;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendSubject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 专题表 前端控制器
 * </p>
 *
 * @author TanJiaLi
 * @since 2022-03-25
 */
@RestController
@RequestMapping("/subject")
public class CmsSubjectController {

    @Autowired
    CmsSubjectService subjectService;

    @ApiOperation("加载专题列表")
    @GetMapping("/listAll")
    public CommonResult<List<CmsSubject>> fetchListAll() {
        List<CmsSubject> page = subjectService.fetchListAll();
        return CommonResult.success(page,"成功加载列表");
    }
    @ApiOperation("加载专题列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<CmsSubject>> fetchList(CmsSubject subject) {
        Page<CmsSubject> page = subjectService.fetchList(subject);
        return CommonResult.success(CommonPage.restPage(page),"成功加载列表");
    }

}

