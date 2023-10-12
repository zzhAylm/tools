package com.zzh.dto;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/9/27 14:51
 */
@Data
@Builder
public class RequestDto<T> implements Serializable {

    @Builder.Default
    private String version = "1.0";

    private String sign;

    private String signType;

    private String sysId;

    @Builder.Default
    private String timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now());

    private T data;
}
