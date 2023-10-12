package com.zzh.domain;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/10/12 11:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {


    @NotNull(message = "年龄不能为空")
    private Integer age;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "地址不能为空")
    private String address;
}
