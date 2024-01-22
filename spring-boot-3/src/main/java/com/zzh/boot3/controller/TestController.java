package com.zzh.boot3.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
}
