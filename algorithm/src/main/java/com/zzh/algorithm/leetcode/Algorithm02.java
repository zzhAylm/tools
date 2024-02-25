package com.zzh.algorithm.leetcode;

/**
 * @Description: 27. 移除元素
 * @Author: zzh
 * @Crete 2024/2/18 10:55
 */
public class Algorithm02 {

    public static void main(String[] args) {

    }

    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return nums.length;
        }
        int num = 0;
        int index = 0;
        int length = nums.length;
        int end = length - 1;
        while (index < length) {
            if (nums[index] == val) {
                while (end > index && nums[end] == val) {
                    end--;
                }
                if (end <= index) {
                    return num;
                }
                int temp = nums[end];
                nums[end] = nums[index];
                nums[index] = temp;
            }
            num++;
            index++;
        }
        return num;
    }
}
