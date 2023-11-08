package com.zzh.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/10/12 20:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonDto implements Serializable {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    private Integer age;
    //生成json忽律
    @JsonIgnore
    private String json;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT")
    private Date date = new Date();
    // json扁平
    @JsonUnwrapped
    @JsonIgnoreProperties(ignoreUnknown = true)
    /**
     * 当我们使用 Jackson 将 JSON 数据转换为 Java 对象时，如果 JSON 中包含了目标类中没有定义的属性，通常会抛出异常。
     * 通过在目标类上使用 @JsonIgnoreProperties(ignoreUnknown = true) 注解，可以告诉 Jackson 忽略这些未知的属性，避免异常的抛出。
     * **/
    private PersonInfo personInfo;

    @Data
    public static class PersonInfo implements Serializable{
        private String userName;
        private String fullName;
    }
}
