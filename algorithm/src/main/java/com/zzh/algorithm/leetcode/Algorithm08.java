package com.zzh.algorithm.leetcode;

/**
 * @Description: 122. 买卖股票的最佳时机 II
 * @Author: zzh
 * @Crete 2024/2/18 16:45
 */
public class Algorithm08 {

    public int maxProfit(int[] prices) {
        if (prices.length<=1){
            return 0;
        }
        int max=0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i]>prices[i-1]){
                max+=prices[i]-prices[i-1];
            }
        }
        return max;
    }
}
