package com.zzh.algorithm.other;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Description: 滑动窗口最大值
 * @Author: zzh
 * @Crete 2023/9/4 20:56
 */
public class Algorithm13 {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 3, 1, 2, 0, 5};

        int[] ints = maxSlidingWindow(arr, 3);
        Arrays.stream(ints).forEach(System.out::println);
    }


    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 1) {
            return nums;
        }
        int[] result = new int[nums.length - k + 1];
        int index = 0;
        StackArray stackArray = new StackArray();
        for (int i = 0; i < nums.length; i++) {
            if (i >= k) {
                stackArray.pop(nums[i - k]);
                stackArray.push(nums[i]);
                result[index++] = stackArray.getMax();
            } else {
                stackArray.push(nums[i]);
                if (k - 1 == i) {
                    result[index++] = stackArray.getMax();
                }
            }
        }
        return result;
    }

    public static class StackArray {

        private final LinkedList<Integer> queue = new LinkedList<>();

        public int getMax() {
            if (!queue.isEmpty()) {
                return queue.peek();
            }
            return Integer.MAX_VALUE;
        }

        public void pop(int num) {
            int max = getMax();
            if (num == max) {
                queue.poll();
            }
        }

        public void push(int num) {
            while (!queue.isEmpty() && queue.peekFirst() < num) {
                queue.pollFirst();
            }
            while (!queue.isEmpty() && queue.peekLast() < num) {
                queue.pollLast();
            }
            queue.add(num);
        }


    }


}
