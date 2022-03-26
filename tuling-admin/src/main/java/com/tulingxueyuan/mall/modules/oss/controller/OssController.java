package com.tulingxueyuan.mall.modules.oss.controller;

import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.oss.dto.OssResultDTO;
import com.tulingxueyuan.mall.modules.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aliyun/oss")
public class OssController {
    @Autowired
    OssService ossService;
    @CrossOrigin
    @GetMapping("/policy")
    public CommonResult<OssResultDTO> policy() {
        OssResultDTO policy = ossService.policy();
        return CommonResult.success(policy, "上传成功");
    }
}
