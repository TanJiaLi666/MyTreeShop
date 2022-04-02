package com.tulingxueyuan.mall.modules.oss.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OssResultDTO {
    private String accessId;
    private String policy;
    private String signature;
    private String host ;
    private String expire ;
    private String dir;
}
