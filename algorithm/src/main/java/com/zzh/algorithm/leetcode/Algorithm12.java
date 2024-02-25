package com.zzh.algorithm.leetcode;

import java.util.*;

/**
 * @Description: 380. O(1) 时间插入、删除和获取随机元素
 * @Author: zzh
 * @Crete 2024/2/19 10:42
 */
public class Algorithm12 {

    public static void main(String[] args) {

    }

    class RandomizedSet {

        Map<Integer, Integer> nums;
        List<Integer> list;
        Random random;

        public RandomizedSet() {
            nums = new HashMap<>();
            random = new Random();
            list = new ArrayList<>();
        }

        public boolean insert(int val) {
            if (nums.containsKey(val)) {
                return false;
            }
            int size = list.size();
            list.add(val);
            nums.put(val, size);
            return true;
        }

        public boolean remove(int val) {
            if (nums.containsKey(val)) {
                Integer index = nums.get(val);
                int lastIndex = list.size() - 1;
                Integer last = list.get(lastIndex);
                list.set(index, last);
                nums.put(last, index);
                list.remove(lastIndex);

                nums.remove(val);
                return true;
            }
            return false;
        }

        public int getRandom() {
            int index = random.nextInt(list.size());
            return list.get(index);
        }
    }
}
