package com.zzh.algorithm.dobblepointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 回溯算法
 * @Author: zzh
 * @Crete 2024/3/7 20:41
 */
public class Algorithm09 {

    List<List<Integer>> result = new ArrayList<>();

    // 选排列
    public List<List<Integer>> permute(int[] nums) {
        resource(nums, new ArrayList<>());
        return result;
    }

    public void resource(int[] nums, List<Integer> res) {
        if (res.size() == nums.length) {
            result.add(new ArrayList<>(res));
            return;
        }
        for (int num : nums) {
            if (!res.contains(num)) {
                res.add(num);
                resource(nums, res);
                res.remove(res.size() - 1);
            }
        }
    }


    // 选排列
    public List<List<Integer>> permute1(int[] nums) {
        boolean[] used = new boolean[ nums.length];
        Arrays.fill(used, false);
        resource(nums, used, new LinkedList<>());
        return result;
    }

    public void resource(int[] nums, boolean[] used, LinkedList<Integer> res) {
        if (res.size() == nums.length) {
            result.add(new ArrayList<>(res));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            res.add(nums[i]);
            used[i] = true;
            resource(nums, used, res);
            res.removeLast();
            used[i] = false;
        }

    }


}
