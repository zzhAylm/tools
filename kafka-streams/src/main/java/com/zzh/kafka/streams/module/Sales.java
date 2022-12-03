package com.zzh.kafka.streams.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/11/30 23:52
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sales implements Serializable {
    private String userName;
    private String department;
    private double salesAmount;
    private double totalSalesAmount;
}
