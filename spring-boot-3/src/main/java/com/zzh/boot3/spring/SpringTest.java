package com.zzh.boot3.spring;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/2/1 21:56
 */
@Slf4j
@SpringBootTest
public class SpringTest {

    @Test
    public void processor(){
//        BeanPostProcessor
//        InstantiationAwareBeanPostProcessor
//        BeanFactoryPostProcessor
//        BeanDefinitionRegistryPostProcessor
    }


    @Test
    public void send() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("static/合同的pdf.pdf");
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("base64", Base64.getEncoder().encodeToString(classPathResource.getStream().readAllBytes()));
        paramMap.put("fileName","zzh01");
        paramMap.put("bizSys","bestsign");
        paramMap.put("posX","200");
        paramMap.put("posY","300");
        paramMap.put("posPage","18");
        paramMap.put("signType","Single");
        String post = HttpUtil.post("http://localhost:9999/tsign-qs/self-sign", paramMap);

        log.info("result={}", post);
    }

    @Test
    public void send2() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("static/test2.pdf");
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("base64", Base64.getEncoder().encodeToString(classPathResource.getStream().readAllBytes()));
        paramMap.put("fileName","zzh02");
        paramMap.put("bizSys","bestsign");
        paramMap.put("posX","122.04");
        paramMap.put("posY","288.22626");
        paramMap.put("posPage","2");
        paramMap.put("signType","Single");
        String post = HttpUtil.post("http://localhost:9999/tsign-qs/self-sign", paramMap);

        log.info("result={}", post);
    }
}
