package com.zzh.spring;

import org.springframework.http.MediaType;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/6/27 20:00
 */
public class Main {

//    @Bean
    public static void main(String[] args) {
//        Bean1 bean1 = new Bean1();
//        System.out.println(bean1);


        MediaType mediaType = MediaType.valueOf("application/json;charset=UTF-8");
        System.out.println(MediaType.APPLICATION_JSON.includes(mediaType));
    }
}
