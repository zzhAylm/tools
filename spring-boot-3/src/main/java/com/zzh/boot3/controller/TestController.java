package com.zzh.boot3.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/14 17:41
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {


    //    内容协商，同一个接口更具请求头不同返回不同的值
    @GetMapping("/test")
    public void test() {
        log.info("test ={}", this);
    }


    //  在任意位置可以获取到Request和Response ：
    // mvc 使用 RequestContextFilter ，将request和response放在了 RequestContextHolder里面,我们可以在任意位置获取到request和response
    @GetMapping("/request")
    public void request() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(requestAttributes, "requestAttributes is empty!");
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        log.info("test ={}", this);
    }

    //ErrorMvcAutoConfiguration 错误处理机制

    @GetMapping("/error")
    public ResponseEntity<String> error() {

        log.info("test ={}", this);
        return ResponseEntity.ok("body");
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/auth")
    public ResponseEntity<String> auth() {
        log.info("test ={}", this);
        return ResponseEntity.ok("body");
    }


    @RequestMapping("/account")
    public ModelAndView index(ModelAndView m) {
        List<Account> list = new ArrayList<>();
        list.add(new Account("KangKang", "康康", "e10adc3949ba59abbe56e", "超级管理员", "17777777777"));
        list.add(new Account("Mike", "麦克", "e10adc3949ba59abbe56e", "管理员", "13444444444"));
        list.add(new Account("Jane", "简", "e10adc3949ba59abbe56e", "运维人员", "18666666666"));
        list.add(new Account("Maria", "玛利亚", "e10adc3949ba59abbe56e", "清算人员", "19999999999"));
        m.addObject("accountList", list);
        m.setViewName("account");
        m.setStatus(HttpStatusCode.valueOf(200));
        return m;
    }


    @Data
    @AllArgsConstructor
    public static class Account {
        private String name;
        private String account;
        private String password;
        private String role;
        private String mobile;
    }


}
