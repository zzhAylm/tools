package com.zzh.boot3.service;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.URLUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/29 10:02
 */
@Slf4j
@SpringBootTest
public class ServiceTest {

    @Test
    public void strTest() throws IOException {
        String str="https://oss-test.suixingpay.com/1/s3/test1/2024_01_29_09_58_ef88302bfe13448d9e306b2ad3eac3cb.png";


        InputStream inputStream = URLUtil.url(str).openStream();

        String encode = Base64.encode(inputStream);

        log.info("encode={}", encode);


    }


    @Test
    public void str(){
        String str="https://oss-test.suixingpay.com/1/s3/test1/2024_01_29_09_58_ef88302bfe13448d9e306b2ad3eac3cb.png";
        int index = str.indexOf("?");
        String substring = str.substring(index);
        System.out.println(substring);

    }


    @Test
    public void ossStr(){
        String ossUrl="https://oss-ali-prod.cogolinks.com/20231130222151001_347_77_ad2728319a3431_10080002069.png";
        String[] split = ossUrl.split("\\?", ossUrl.lastIndexOf("?"));
        ossUrl = split[0];
        String suffix = "";
        if (split.length > 1) {
            suffix = "?" + split[1];

        }

        System.out.println(ossUrl);
        System.out.println(suffix);
        System.out.println(ossUrl+suffix);
    }
}
