package com.zzh.kafka.streams.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/11/28 21:44
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionPattern {
    private String zipCode;
    private String item;
    private Date date;
    private Double amount;
}
