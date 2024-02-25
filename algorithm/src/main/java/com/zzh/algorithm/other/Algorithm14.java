package com.zzh.algorithm.other;

import java.util.*;

/**
 * @Description: 前 K 个高频元素
 * @Author: zzh
 * @Crete 2023/9/5 21:32
 */
public class Algorithm14 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] ints = topKFrequent(nums, 2);

        Arrays.stream(ints).forEach(System.out::println);

    }


    public static int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 1) {
            return nums;
        }
        int[] result = new int[k];

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        map.entrySet().forEach(entry -> {
            priorityQueue.add(entry);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        });
        for (int i = k - 1; i >= 0; i--) {
            result[i] = Objects.requireNonNull(priorityQueue.poll()).getKey();
        }
        return result;
    }
}
