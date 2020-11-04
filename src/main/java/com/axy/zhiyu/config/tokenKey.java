package com.axy.zhiyu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "axy.config")
@Data
public class tokenKey {
    private String salt;
    private String tokenKey;
}
