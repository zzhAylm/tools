package com.zzh.algorithm.leetcode;

/**
 * @Description: 80. 删除有序数组中的重复项 II
 * @Author: zzh
 * @Crete 2024/2/18 13:58
 */
public class Algorithm04 {


    public static void main(String[] args) {

    }

    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int index = 2;
        int end = 1;
        while (index < nums.length) {
            if ((nums[end - 1] != nums[end]) || (nums[end - 1] == nums[end] && nums[index] != nums[end])) {
                nums[++end] = nums[index];
            }
            index++;
        }
        return end + 1;
    }

}
