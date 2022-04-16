package com.tulingxueyuan.mall.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
@Getter
@Setter
@ConfigurationProperties(prefix = "secure.ignored")
public class SecurityIgnore {
    private List<String> urls;



}
