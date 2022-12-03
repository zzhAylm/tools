package com.zzh.kafka.streams.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/11/28 21:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionReward {

    private String customerId;
    private double totalPoints;
    private Integer rewardPoints;

    public static TransactionReward newBuilder(Transaction transaction) {
        return new TransactionReward(transaction.getCustomerId(), transaction.getPrice() * transaction.getQuantity(), (int) (transaction.getPrice() * transaction.getQuantity()));
    }
}
