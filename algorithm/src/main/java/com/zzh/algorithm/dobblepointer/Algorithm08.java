package com.zzh.algorithm.dobblepointer;

import java.util.Arrays;

/**
 * @Description: 动态规划
 * @Author: zzh
 * @Crete 2024/3/7 15:33
 */
public class Algorithm08 {

    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int n0 = 0;
        int n1 = 1;
        for (int i = 2; i <= n; i++) {
            int temp = n0 + n1;
            n0 = n1;
            n1 = temp;
        }
        return n1;
    }

    //  322. 零钱兑换
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        return dp(coins, amount, dp);
    }


    public int dp(int[] coins, int amount, int[] dp) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (dp[amount] != Integer.MAX_VALUE) {
            return dp[amount];
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int count = dp(coins, amount - coin, dp);
            if (count == -1) {
                continue;
            }
            res = Math.min(res, count + 1);
        }
        dp[amount] = res == Integer.MAX_VALUE ? -1 : res;
        return dp[amount];
    }

    //  322. 零钱兑换
    public int coinChange1(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int res = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                int count = dp[i - coin];
                if (count == -1) {
                    continue;
                }
                res = Math.min(res, count + 1);
            }
            dp[i] = res == Integer.MAX_VALUE ? -1 : res;
        }
        return dp[amount];
    }

    int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 数组大小为 amount + 1，初始值也为 amount + 1
        Arrays.fill(dp, amount + 1);

        // base case
        dp[0] = 0;
        // 外层 for 循环在遍历所有状态的所有取值
        for (int i = 0; i < dp.length; i++) {
            // 内层 for 循环在求所有选择的最小值
            for (int coin : coins) {
                // 子问题无解，跳过
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

    //LCR 103. 零钱兑换

    public static int coinChange3(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            int res = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                int count = dp[i - coin];
                if (count == -1) {
                    continue;
                }
                res = Math.min(res, count + 1);
            }
            dp[i] = res == Integer.MAX_VALUE ? -1 : res;
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        System.out.println(coinChange3(coins, 11));

    }
}
