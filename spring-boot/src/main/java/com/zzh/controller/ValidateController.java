package com.zzh.controller;

import cn.hutool.json.JSONUtil;
import com.zzh.domain.User;
import com.zzh.dto.RequestDto;
import com.zzh.dto.ResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/10/12 11:37
 */
@Slf4j
@RestController
@RequestMapping("/validate")
@Validated
public class ValidateController {


    @PostMapping("test1")
    public ResponseDto<User> validate(@Valid  @RequestBody RequestDto<User> requestDto) {
        log.info("user ={}", JSONUtil.toJsonStr(requestDto));
        return ResponseDto.build(200, "success", requestDto.getData());
    }

    @PostMapping("test2")
    public ResponseDto<Object> validate(@NotEmpty String name, @NotEmpty Integer age) {
        log.info("name ={},age={}", name, age);
        return ResponseDto.build(200, "success", name + ":" + age);
    }

    @PostMapping("test3")
    public ResponseDto<Object> validate(@RequestBody @Valid User user) {
        log.info("user ={}", JSONUtil.toJsonStr(user));
        return ResponseDto.build(200, "success", user);
    }

}
