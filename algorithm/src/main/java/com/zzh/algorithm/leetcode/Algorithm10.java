package com.zzh.algorithm.leetcode;

/**
 * @Description: 45. 跳跃游戏 II
 * @Author: zzh
 * @Crete 2024/2/18 17:37
 */
public class Algorithm10 {


    public int jump(int[] nums) {
        int step = 0;
        int maxIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxIndex = Math.max(maxIndex, nums[i] + i);
            if (i == endIndex) {
                endIndex = maxIndex;
                step++;
            }
        }
        return step;
    }

}
