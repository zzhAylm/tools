package com.zzh.algorithm.leetcode;

/**
 * @Description: 238. 除自身以外数组的乘积
 * @Author: zzh
 * @Crete 2024/2/19 11:35
 */
public class Algorithm13 {


    public int[] productExceptSelf(int[] nums) {

        int length = nums.length;
        int[] prefixArr = new int[length];
        int[] rearArr = new int[length];
        int[] arr = new int[length];

        prefixArr[0] = nums[0];
        for (int i = 1; i < length; i++) {
            prefixArr[i] = prefixArr[i - 1] * nums[i];
        }

        rearArr[length - 1] = nums[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            rearArr[i] = rearArr[i + 1] * nums[i];
        }

        for (int i = 0; i < length; i++) {
            if (i == 0) {
                arr[i] = rearArr[i + 1];
            } else if (i == length - 1) {
                arr[i] = prefixArr[i - 1];
            } else {
                arr[i] = prefixArr[i - 1] * rearArr[i + 1];
            }
        }
        return arr;

    }
}
