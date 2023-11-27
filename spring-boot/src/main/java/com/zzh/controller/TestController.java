package com.zzh.controller;

import cn.hutool.json.JSONUtil;
import com.zzh.annotation.Logger;
import com.zzh.dto.RequestDto;
import com.zzh.dto.ResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/9/27 14:49
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {


    @RequestMapping("/test")
    public ResponseDto<String> post(@RequestBody RequestDto<String> requestDto) {
        log.info("request is : {}", JSONUtil.toJsonStr(requestDto));
        return ResponseDto.success("success");
    }


    @RequestMapping("/refresh")
    public ResponseDto<String> refresh(HttpServletResponse response) {
        response.setHeader("Refresh", "5;URL=http://localhost:8080/test/refresh/target");
        return ResponseDto.success("refresh");
    }

    @RequestMapping("/refresh/target")
    public ResponseDto<String> refreshTarget(HttpServletResponse response) {
        response.setHeader("Refresh", "5;URL=http://localhost:8080/test/refresh");
        return ResponseDto.success("refresh target");
    }

    @Logger(value = "do logger")
    @GetMapping("/aop")
    public ResponseDto<String> testAop(String name,String addr){
        System.out.println("logger aop test....");
        return ResponseDto.success("name"+name+",addr"+addr);
    }
}
