package com.zzh.websocket.resources;

import cn.hutool.core.io.resource.ClassPathResource;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.*;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/13 17:53
 */

@SpringJUnitWebConfig
public class ResourcesTest {


    // 访问网络资源
    @Test
    public void urlResourcesTest() throws URISyntaxException, IOException {
        UrlResource urlResource = new UrlResource("https://osscdn.suixingpay.com/0/2/32MDJhYjIyYWZFb3NQVTJheDIwMjNfMDlfMTNfMTJfMTJfOGIwNGMwMWFmZGMxNGM4N2IxMDQzZjBhNDI2MmUzNTYyYUtzZXMtYnVja2V0MmFiMQ.HEIC");
        System.out.println(urlResource.getURL());
        System.out.println(urlResource.getFilename());
        System.out.println(urlResource.getDescription());
        InputStream inputStream = urlResource.getInputStream();
//        IoUtil.copy(inputStream, new FileOutputStream("./"+urlResource.getFilename()));

        UrlResource urlResource1 = new UrlResource("file:build.gradle");
        System.out.println(urlResource1.getURL());
        System.out.println(urlResource1.getFilename());
        System.out.println(urlResource1.getDescription());
    }


    // 访问类路径下的资源
    @Test
    public void classPathResourcesTest() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("static/client.html");
        System.out.println(classPathResource.getAbsolutePath());
        System.out.println(classPathResource.getPath());
        System.out.println(classPathResource.getName());

        BufferedReader reader = classPathResource.getReader(Charset.defaultCharset());
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }


    //访问系统资源
    @Test
    public void FileSystemResourcesTest() throws IOException {
        FileSystemResource fileSystemResource = new FileSystemResource("/Users/zzh/Company/projects/tools/build.gradle");
        System.out.println(fileSystemResource.getFilename());
        System.out.println(fileSystemResource.getDescription());
        System.out.println(fileSystemResource.getContentAsString(Charset.defaultCharset()));


        FileSystemResource fileSystemResource1 = new FileSystemResource("build.gradle");
        System.out.println(fileSystemResource1.getFilename());
        System.out.println(fileSystemResource1.getDescription());
        System.out.println(fileSystemResource1.getContentAsString(Charset.defaultCharset()));
    }



    @Test
    public void servletContextResourcesTest(){


    }


    @Test
    public void resourceLoader(){

        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext();
        Resource resource = classPathXmlApplicationContext.getResource("static/client.html");
        System.out.println(resource.getFilename());
        FileSystemXmlApplicationContext fileSystemXmlApplicationContext=new FileSystemXmlApplicationContext();
        Resource resource1 = fileSystemXmlApplicationContext.getResource("static/client.html");
        System.out.println(resource1.getFilename());

        Resource resource2 = classPathXmlApplicationContext.getResource("classpath:static/client.html");
        Resource resource3 = classPathXmlApplicationContext.getResource("file:build.gradle");
        Resource resource4 = classPathXmlApplicationContext.getResource("https://www.baidu.com");

        ClassPathXmlApplicationContext classPathXmlApplicationContext1 = new ClassPathXmlApplicationContext("classpath:test*.xml");
    }
}
