package com.zzh.kafka.streams.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/11/28 21:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionKey {

    private String customerId;
    private Date transactionDate;
}
