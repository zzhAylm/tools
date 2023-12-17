package com.zzh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@Import({User.class})
@Import(MyImportSelector.class)
public class WebConfig {
}
