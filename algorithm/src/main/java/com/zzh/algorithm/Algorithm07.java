package com.zzh.algorithm;

/**
 * @Description: 螺旋矩阵2
 * @Author: zzh
 * @Crete 2023/8/28 21:36
 */
public class Algorithm07 {

    public static void main(String[] args) {
        int[][] ints = generateMatrix(6);
        for (int i = 0; i < ints.length; i++) {
            System.out.println();
            for (int j = 0; j < ints[i].length; j++) {
                System.out.print(ints[i][j] + "\t");
            }
        }
    }

    public static int[][] generateMatrix(int n) {
        int[][] arr = new int[n][n];
        int index = 1;
        int count = n;
        for (int i = 0; count > 1; i++) {
            int top = i;
            int right = i;
            int bottom = n - 1 - i;
            int left = n - 1 - i;
            while (top < n - 1 - i) {
                arr[right][top] = index;
                index++;
                top++;
            }
            while (right < n - 1 - i) {
                arr[right][top] = index;
                right++;
                index++;
            }
            while (bottom > i) {
                arr[right][bottom] = index;
                index++;
                bottom--;
            }
            while (left > i) {
                arr[left][bottom] = index;
                index++;
                left--;
            }
            count = count - 2;
        }
        if (n % 2 != 0) {
            arr[n / 2][n / 2] = n * n;
        }
        return arr;
    }
}
