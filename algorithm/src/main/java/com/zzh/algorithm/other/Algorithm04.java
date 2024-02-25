package com.zzh.algorithm.other;

/**
 * @Description: 二分法
 * @Author: zzh
 * @Crete 2023/8/25 09:58
 */
public class Algorithm04 {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        System.out.println(dichotomy(arr, 10));
    }


    public static boolean dichotomy(int[] arr, int target) {
        if (arr.length == 0 || target < arr[0] || target > arr[arr.length - 1]) {
            return false;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

}
