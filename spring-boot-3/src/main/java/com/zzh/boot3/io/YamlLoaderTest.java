package com.zzh.boot3.io;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/31 15:33
 */
@Slf4j
@SpringBootTest
public class YamlLoaderTest {


    @Test
    public void loader() throws IOException {
        YamlPropertySourceLoader yamlPropertySourceLoader=new YamlPropertySourceLoader();
        List<PropertySource<?>> load = yamlPropertySourceLoader.load("boot3Properties", new ClassPathResource("META-INF/config/boot3.yml"));
        log.info("boot3 load={}", JSONUtil.toJsonStr(load));
    }
}
