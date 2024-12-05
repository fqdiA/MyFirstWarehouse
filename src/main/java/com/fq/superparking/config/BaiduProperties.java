package com.fq.superparking.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "baidu")
public class BaiduProperties {
    private String appId;
    private String apiKey;
    private String secretKey;
}
