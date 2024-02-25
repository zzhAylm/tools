package com.zzh.algorithm.leetcode;

/**
 * @Description: 134. 加油站
 * @Author: zzh
 * @Crete 2024/2/19 14:58
 */
public class Algorithm14 {

    public static void main(String[] args) {

        int[] gas = {67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66};
        int[] cost = {27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        System.out.println(canCompleteCircuit(gas, cost));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int length = gas.length;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = gas[i] - cost[i];
        }
        int index = 0;
        while (index < length) {
            if (arr[index] >= 0) {
                int temp = 0;
                for (int i = index; i < 2 * length; i++) {
                    temp += arr[i % length];
                    if (temp < 0) {
                        break;
                    }
                }
                if (temp >= 0) {
                    return index;
                }
            }


            index++;

        }
        return -1;
    }


    public static int canCompleteCircuit2(int[] gas, int[] cost) {

        int idx = 0;
        int n = gas.length;
        int sum = 0;
        int min = 0;
        for (int i = 0; i < n; i++) {
            sum += gas[i] - cost[i];
            if (sum < min) {
                min = sum;
                idx = i + 1;
            }
        }
        return sum < 0 ? -1 : idx;
    }
}
