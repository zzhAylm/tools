package com.zzh.algorithm.leetcode;

/**
 * @Description: 189. 轮转数组
 * @Author: zzh
 * @Crete 2024/2/18 14:28
 */
public class Algorithm06 {

    public static void main(String[] args) {
        int[] arr = {1, 2};
        rotate(arr, 3);
    }

    public static void rotate(int[] nums, int k) {
        if (k <= 0 || nums.length <= 1) {
            return;
        }

        int length = nums.length;
        int[] tempNums = new int[k];
        for (int i = 0; i < k; i++) {
            tempNums[i % length] = nums[i % length];
        }
        for (int i = 0; i < k; i++) {
            int num = tempNums[i];
            int index = i + k;
            while (index < nums.length + k) {
                int temp = nums[index % nums.length];
                nums[index % nums.length] = num;
                num = temp;
                index += k;
            }
        }
    }


    public static void rotate2(int[] nums, int k) {
        if (k <= 0 || nums.length <= 1) {
            return;
        }
        int length = nums.length;
        k = k % length;
        int[] tempNums = new int[k];
        System.arraycopy(nums, 0, tempNums, 0, k);
        for (int i = 0; i < k; i++) {
            int num = tempNums[i];
            int index = i + k;
            while (index < nums.length + k) {
                int tempIndex = index % nums.length;
                int temp = nums[tempIndex];
                nums[tempIndex] = num;
                num = temp;
                index += k;
            }
        }
    }
}
