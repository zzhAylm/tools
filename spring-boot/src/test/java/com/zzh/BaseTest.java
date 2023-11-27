package com.zzh;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.zzh.dto.JsonDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/10/12 20:33
 */
@ActiveProfiles(value = "dev")
public class BaseTest {
    private static final Logger log = LogManager.getLogger(BaseTest.class);

    @Test
    public void test1() throws JsonProcessingException {
        JsonDto jsonDto = new JsonDto();
        jsonDto.setJson("zzh");
        jsonDto.setAge(18);
        jsonDto.setId(1L);
        jsonDto.setName("zzh");
        JsonDto.PersonInfo personInfo = new JsonDto.PersonInfo();
        personInfo.setFullName("zzzzzzzz");
        personInfo.setUserName("zzh");
        jsonDto.setPersonInfo(personInfo);
        System.out.println(JSONUtil.toJsonStr(jsonDto));
        Gson gson = new Gson();
        System.out.println(gson.toJson(jsonDto));

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(jsonDto));
    }

    @Test
    public void test2() throws JsonProcessingException {
        String json = "{\"id\":1,\"name\":\"zzh\",\"age\":18,\"json\":\"zzh\",\"userName\":\"zzh\",\"fullName\":\"zzzzzzzz\"}";
        JsonDto bean = JSONUtil.toBean(json, JsonDto.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonDto jsonDto = mapper.readValue(json, JsonDto.class);
        System.out.println(bean);
        System.out.println(jsonDto);
    }

    @Test
    public void test3() {
        TypeReference<String> typeReference = new TypeReference<String>() {
        };
        System.out.println(typeReference.getType().equals(String.class));

        String json = "fafasdfadsfadsfsz";

        System.out.println();
    }


    @Test
    public void test4() {

        String sql = "update user set age=#{age&name}, NAME=#{name} WHERE ID=#{id}";

//        Pattern pattern = Pattern.compile("#\\{([^}]+)&([^}]+)}");
        Pattern pattern = Pattern.compile("#\\{([^}]+)}");
        Matcher matcher = pattern.matcher(sql);
        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.group(1));
            // group[0] : 匹配的字符串
            // group[1] : 匹配字符串中，匹配正则第一个()中的内容
            // group[2] : 匹配字符串中，匹配正则第二个()中的内容
            // [^}]+ : 匹配的是一个或多个不包括 "}" 的字符，其中包含一个 ^ 符号，它表示否定。这意味着它匹配除了后面紧跟的字符 "}" 以外的任何字符。
//            System.out.println(matcher.group(2));
            //

            System.out.println(StrUtil.toCamelCase("zzh_ylm"));
        }


    }


    @Test
    public void test5() {

        String json = "{\"id\":1,\"name\":\"zzh\",\"age\":18,\"json\":\"zzh\",\"userName\":\"zzh\",\"fullName\":\"zzzzzzzz\"}";

        System.out.println(JSONUtil.isTypeJSON(json));


        String domainName = "";

        String publicHttp = StringUtils.isEmpty(domainName) ? "local" + "/" + "key" : domainName + "/" + "key";
        System.out.println(publicHttp);
    }


    //    范型
    @Test
    public void test6() {
//        Collections.sort();
    }


    @Test
    public void testStr() {

        String context = "[prod]更新汇率信息异常！CC汇率更新异常:调用通道-询价（公共）接口-失败，调用Currencycloud通道-询价-异常：java.lang.RuntimeException: com.fasterxml.jackson.core.JsonParseException: Unexpected character ('<' (code 60)): expected a valid value (JSON String, Number, Array, Object or token 'null', 'true' or 'false')\\n at [Source: (String)\\\"<html>\\r\\n<head><title>502 Bad Gateway</title></head>\\r\\n<body>\\r\\n<center><h1>502 Bad Gateway</h1></center>\\r\\n<hr><center>nginx</center>\\r\\n</body>\\r\\n</html>\\r\\n\\\"; line: 1, column: 2]";
        System.out.println(JSONUtil.isTypeJSONObject(context));
        System.out.println(JSONUtil.isTypeJSONArray(context));
        try {
            JSON parse = JSONUtil.parseArray(context);
            System.out.println(parse);
//            if (jsonObject.size() == 1 && jsonObject.containsKey("articles")) {
//                System.out.println(1111);
//            } else if (jsonObject.size() == 1 && jsonObject.containsKey("content")) {
//                System.out.println(22222);
//            } else {
//                System.out.println(33333);
//            }
        } catch (Exception e) {
            log.error("error", e);
            System.out.println("errrrrrrrr");
        } finally {
            System.out.println("4444444");
        }
        System.out.println("6666666");
    }

    @Test
    public void assertTest() {
        String str = "zzj";
        String str2 = "";
        Assert.hasLength(str2, "不能为空");
        Assert.notNull(str2, "str 不能为空");
        System.out.println("断言后内容");
    }
}
