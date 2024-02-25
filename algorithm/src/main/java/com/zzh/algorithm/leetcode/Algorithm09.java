package com.zzh.algorithm.leetcode;

/**
 * @Description: 55. 跳跃游戏
 * @Author: zzh
 * @Crete 2024/2/18 17:06
 */
public class Algorithm09 {

    public static void main(String[] args) {
        int[] arr = {0, 2, 3};
        canJump(arr);
    }

    public static boolean canJump(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }
        boolean[] arr = new boolean[nums.length];
        arr[0] = true;
        for (int i = 1; i < nums.length; i++) {
            int dept = 1;
            int index = i - 1;
            while (index >= 0 && arr[index] && !arr[i]) {
                arr[i] = (nums[index] - dept) >= 0;
                index--;
                dept++;
            }
        }
        return arr[nums.length - 1];
    }

    public static boolean canJump2(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (index >= i) {
                index = Math.max(index, i + nums[i]);
                if (index >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
