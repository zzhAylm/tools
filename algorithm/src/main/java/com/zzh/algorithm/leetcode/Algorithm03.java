package com.zzh.algorithm.leetcode;

/**
 * @Description: 26. 删除有序数组中的重复项
 * @Author: zzh
 * @Crete 2024/2/18 12:00
 */
public class Algorithm03 {

    public static void main(String[] args) {

    }

    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int index = 1;
        int now = 0;
        while (index < nums.length) {
            if (nums[now] != nums[index]) {
                nums[++now]= nums[index];
            }
            index++;
        }
        return now+1;
    }
}
