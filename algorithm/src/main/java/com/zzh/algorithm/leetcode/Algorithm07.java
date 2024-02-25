package com.zzh.algorithm.leetcode;

/**
 * @Description: 121. 买卖股票的最佳时机
 * @Author: zzh
 * @Crete 2024/2/18 16:25
 */
public class Algorithm07 {

    public static void main(String[] args) {

    }

    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int max = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }
}
