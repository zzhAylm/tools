package com.zzh.algorithm.leetcode;

/**
 * @Description: 274. H 指数
 * @Author: zzh
 * @Crete 2024/2/19 09:49
 */
public class Algorithm11 {

    public static void main(String[] args) {

    }

    public static int hIndex(int[] citations) {
        if (citations.length == 0) {
            return 0;
        }

        int length = citations.length;
        int[] arr = new int[length + 2];


        for (int i = 0; i < citations.length; i++) {
            if (citations[i] > length) {
                arr[length + 1]++;
            } else {
                arr[citations[i]]++;
            }
        }
        int count = 0;
        for (int i = length + 1; i >= 0; i--) {
            count += arr[i];
            if (count >= i) {
                return i;
            }

        }
        return 0;
    }

}
