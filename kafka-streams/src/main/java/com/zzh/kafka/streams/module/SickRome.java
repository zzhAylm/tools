package com.zzh.kafka.streams.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/3 23:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SickRome implements Serializable {

    private String id;
    private String dockerName;
}
