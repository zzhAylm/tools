package com.zzh.controller;

import cn.hutool.json.JSONUtil;
import com.zzh.dto.RequestDto;
import com.zzh.dto.ResponseDto;
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


    @PostMapping
    public ResponseDto<String> post(@RequestBody RequestDto<String> requestDto) {
        log.info("request is : {}", JSONUtil.toJsonStr(requestDto));
        return ResponseDto.success("success");
    }
}
