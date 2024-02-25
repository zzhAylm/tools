package com.zzh.algorithm.other;

/**
 * @Description: 长度最小的子数组
 * @Author: zzh
 * @Crete 2023/8/28 20:50
 */
public class Algorithm06 {

    public static void main(String[] args) {


        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(minSubArrayLen(15, arr));

    }

    public static int minSubArrayLen(int target, int[] nums) {
        if (target < 0 || nums == null || nums.length == 0) {
            return 0;
        }
        int result = nums.length + 1;
        int left = 0;
        int right = 0;
        int sum = nums[left];
        int length = nums.length;
        while (right < length && left <= right) {
            if (sum >= target) {
                result = Math.min(result, right - left + 1);
                sum = sum - nums[left];
                left++;
            } else {
                right++;
                if (right < length) {
                    sum = sum + nums[right];
                }
            }
        }
        return result == nums.length + 1 ? 0 : result;
    }

    public static int minSubArrayLen1(int target, int[] nums) {
        if (target < 0 || nums == null || nums.length == 0) {
            return 0;
        }
        int result = nums.length + 1;
        int left = 0;
        int right = 0;
        int sum = 0;
        int length = nums.length;
        while (right < length && left <= right) {
            sum += nums[right];
            while (sum >= target && left <= right) {
                result = Math.min(result, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return result == nums.length + 1 ? 0 : result;
    }
}
