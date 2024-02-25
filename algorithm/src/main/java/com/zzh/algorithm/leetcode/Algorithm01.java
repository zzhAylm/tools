package com.zzh.algorithm.leetcode;

/**
 * @Description: 88. 合并两个有序数组
 * @Author: zzh
 * @Crete 2024/2/18 10:55
 */
public class Algorithm01 {

    public static void main(String[] args) {

    }

    /**
     * 最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int i = 0;
        int j = 0;
        int index = 0;

        int[] temp = new int[m + n];

        while (i < m && j < n) {
            int num1 = nums1[i];
            int num2 = nums2[j];
            if (num1 <= num2) {
                temp[index++] = num1;
                i++;
            } else {
                temp[index++] = num2;
                j++;
            }
        }
        while (i < m) {
            temp[index++] = nums1[i++];
        }
        while (j < n) {
            temp[index++] = nums2[j++];
        }
        System.arraycopy(temp, 0, nums1, 0, m + n);
    }
}
