package com.zzh.websocket.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/14 16:09
 */
@ConfigurationProperties
@EnableConfigurationProperties(SpringBootProperties.class)
public class SpringBootConfig {
}
