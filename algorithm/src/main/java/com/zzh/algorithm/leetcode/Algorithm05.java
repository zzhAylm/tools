package com.zzh.algorithm.leetcode;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/2/18 14:10
 */
public class Algorithm05 {


    public int majorityElement(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }
        int index = 0;
        Integer num = null;
        int count = 0;
        while (index < nums.length) {
            if (count == 0) {
                num = nums[index];
                count++;
            } else {
                if (nums[index] != num) {
                    count--;
                } else {
                    count++;
                }
            }
            index++;
        }
        return num;
    }

}
