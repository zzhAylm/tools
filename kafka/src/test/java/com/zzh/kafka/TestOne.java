package com.zzh.kafka;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/8 23:24
 */
@Slf4j
@SpringBootTest(classes = KafkaApplication.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class TestOne {

    private final Logger logger = LoggerFactory.getLogger(TestOne.class);

    @Test
    public void test() {
        logger.info("testOne is running!");

    }

    @Test
    public  void test1() {

        String reStr = "cp -rf ./config.txt ./{}/conf/";
        File file = new File("/Users/zzh/Company/projects/tools/kafka/src/main/resources/file.txt");
        StringBuilder stringBuffer = new StringBuilder();
        try (FileInputStream fileInputStream = new FileInputStream(file);) {
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(buff)) > 0) {
                stringBuffer.append(new String(buff, 0, len));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String str = stringBuffer.toString();


        String[] split = str.split("\\s+");

        StringBuffer outPut = new StringBuffer();

        Arrays.stream(split).forEach(s -> {
            outPut.append(reStr.replace("{}", s)).append("\n");
        });
        System.out.println(outPut);
    }

}
