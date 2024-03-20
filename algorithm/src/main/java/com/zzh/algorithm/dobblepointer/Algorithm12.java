package com.zzh.algorithm.dobblepointer;

/**
 * @Description: 二分法
 * @Author: zzh
 * @Crete 2024/3/11 20:35
 */
public class Algorithm12 {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 7, 7, 8, 8, 10};
        searchRange(arr, 8);

    }

    public static int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};

        int left = 0;
        int right = nums.length - 1;

        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                index = mid;
                break;
            }
        }
        if (index != -1) {
            left = index;
            right = index;
            while (left >= 0) {
                if (left - 1 >= 0 && nums[left - 1] == target) {
                    left--;
                } else {
                    break;
                }
            }
            while (right < nums.length) {
                if (right < nums.length - 1 && nums[right + 1] == target) {
                    right++;
                } else {
                    break;
                }
            }
            return new int[]{left, right};
        }

        return res;
    }

    // [1,10]：两个二分法的方式不同
    public int towSearch1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) { // [1,1] 进入循环有效所以是 <=
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;

    }

    //[1,10) : 两个二分法的方式不同
    public int towSearch2(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) { // [1,1] 进入循环有效所以是 <=
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                return mid;
            }
        }
        // 如果索引越界，说明数组中无目标元素，返回 -1
        if (left >= nums.length) {
            return -1;
        }
        return nums[left] == target ? nums[left] : -1;

    }
}
