package com.zzh;

import cn.hutool.json.JSONUtil;
import com.zzh.dto.RequestDto;
import org.junit.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/11/21 11:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

    @BeforeClass
    public static void beforeClassTest() {
        System.out.println("before class test");
    }

    @Before
    public void beforeTest() {
        System.out.println("before test");
    }

    @Test
    public void Test1() {
        System.out.println("test 1+1=2");
        Assert.assertEquals(2, 1 + 1);
    }

    @Test
    public void Test2() {
        System.out.println("test 2+2=4");
        Assert.assertEquals(4, 2 + 2);
    }

    @After
    public void afterTest() {
        System.out.println("after test");
    }

    @AfterClass
    public static void afterClassTest() {
        System.out.println("after class test");
    }


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    public void testMvc() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/hello?name={name}","mrbird"));
        mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}", 1));
        mockMvc.perform(MockMvcRequestBuilders.multipart("/fileupload").file("file", "文件内容".getBytes("utf-8")));
    }

    @Test
    public void test() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/test/test")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(JSONUtil.toJsonStr(RequestDto.builder().data("zzh").build())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
