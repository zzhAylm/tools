package com.zzh.algorithm.other;

import java.util.Arrays;

/**
 * @Description: 有序数组的的平方
 * @Author: zzh
 * @Crete 2023/8/28 12:03
 */
public class Algorithm05 {


    public static void main(String[] args) {
//
//        DateTime parse = DateUtil.parse("2023-08-28 12:30:30", "yyyy-MM-dd HH:mm:ss");
//
//        System.out.println(parse.toTimeStr());
//
//        DateTime parse1 = DateUtil.parse("2023-08-27 12:30:30");
//        DateTime endTime = DateUtil.parseTimeToday(parse1.toTimeStr());
//        System.out.println(endTime.toString());
//
//        DateTime parse2 = DateUtil.parse("2023-08-28T10:59:30.418Z", "yyyy-MM-dd HH:mm:ss");
//
//        System.out.println(parse2.toString());

        int[] arr = {-7, -5, -3, -2, -2, -1, -1, 0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 4, 5, 6, 6, 7, 8, 9};

        int[] ints = sortArr(arr);
        System.out.println(Arrays.toString(ints));

    }

    public static int[] sortArr(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int length = nums.length;
        int[] result = new int[length];
        int index = 0;
        while (index < length && nums[index] < 0) {
            index++;
        }
        int left = index - 1;
        int right = index;
        index = 0;
        while (left >= 0 && right < length) {
            int leftNum = nums[left] * nums[left];
            int rightNum = nums[right] * nums[right];
            if (leftNum >= rightNum) {
                result[index] = rightNum;
                right++;
            } else {
                result[index] = leftNum;
                left--;
            }
            index++;
        }
        while (left >= 0) {
            result[index] = nums[left] * nums[left];
            left--;
            index++;
        }
        while (right < length) {
            result[index] = nums[right] * nums[right];
            right++;
            index++;
        }
        return result;

    }


}
