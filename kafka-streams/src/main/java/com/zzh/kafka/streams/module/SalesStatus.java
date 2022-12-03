package com.zzh.kafka.streams.module;

import lombok.*;

import java.io.Serializable;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/2 00:38
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SalesStatus implements Serializable {

    private Integer count;
    private Double totalAmount;
    private String department;
    private Double averageAmount;
}
