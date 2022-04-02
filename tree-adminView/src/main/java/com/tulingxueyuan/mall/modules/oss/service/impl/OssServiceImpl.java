package com.tulingxueyuan.mall.modules.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.tulingxueyuan.mall.config.OssConfig;
import com.tulingxueyuan.mall.modules.oss.dto.OssResultDTO;
import com.tulingxueyuan.mall.modules.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OssServiceImpl implements OssService {
    @Value("${aliyun.oss.accessKeyId}")
    String accessId;
    @Value("${aliyun.oss.dir.prefix}")
    String dir;
    @Value("${aliyun.oss.bucketName}")
    String bucketName;
    @Value("${aliyun.oss.endpoint}")
    String endpoint;

    @Autowired
    OssConfig ossConfig;

    @Override
    public OssResultDTO policy() {
        String host = "https://"+bucketName+"."+endpoint;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        String dir_date = dir + sdf.format(new Date());
        OSS ossClient = ossConfig.buildOssClient();
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir_date);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            OssResultDTO dto = new OssResultDTO();
            dto.setAccessId(accessId);
            dto.setDir(dir_date);
            dto.setExpire(String.valueOf(expireEndTime / 1000));
            dto.setHost(host);
            dto.setPolicy(encodedPolicy);
            dto.setSignature(postSignature);
            return dto;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ossClient.shutdown();
        }
        return null;
    }
}
