package com.zzh.algorithm.dobblepointer;

import java.util.*;

/**
 * @Description: 子集和组合问题
 * @Author: zzh
 * @Crete 2024/3/8 10:48
 */
public class Algorithm10 {

    public static void main(String[] args) {
        Algorithm10 Algorithm10 = new Algorithm10();
        int[] candidates = new int[]{1, 1, 2};
        System.out.println(Algorithm10.permuteUnique(candidates));

    }

    List<List<Integer>> result = new ArrayList<>();

    //    216. 组合总和 III
    public List<List<Integer>> combinationSum3(int k, int n) {
        boolean[] used = new boolean[10];
        Arrays.fill(used, false);
        backtrace(0, used, k, n, new LinkedList<>(), 0);
        return result;
    }

    // 回溯算法的核心： 遍历子集问题的回溯树
    public void backtrace(int count, boolean[] used, int k, int n, LinkedList<Integer> res, int sum) {
        if (count > k || (count == k && sum != n)) {
            return;
        }
        if (count == k) {
            result.add(new ArrayList<>(res));
            return;
        }
        int num = 0;
        if (!res.isEmpty()) {
            num = res.getLast();
        }
        for (int i = 1; i <= 9; i++) {
            if (!used[i] && i > num) {
                used[i] = true;
                res.add(i);
                backtrace(count + 1, used, k, n, res, sum + i);
                used[i] = false;
                res.removeLast();
            }
        }
    }


    List<List<Integer>> subSets = new ArrayList<>();
    LinkedList<Integer> res = new LinkedList<>();

    //78. 子集
    public List<List<Integer>> subsets(int[] nums) {
        backtrace(nums, 0);
        return subSets;
    }

    public void backtrace(int[] nums, int index) {
        subSets.add(new ArrayList<>(res));
        for (int i = index; i < nums.length; i++) {
            res.add(nums[i]);
            backtrace(nums, i + 1);
            res.removeLast();
        }
    }

    List<List<Integer>> combineResult = new ArrayList<>();
    LinkedList<Integer> combineRes = new LinkedList<>();

    //77. 组合
    public List<List<Integer>> combine(int n, int k) {

        backtrace(n, k, 1);
        return combineResult;
    }

    public void backtrace(int n, int k, int index) {
        if (combineRes.size() == k) {
            combineResult.add(new ArrayList<>(combineRes));
            return;
        }
        for (int i = index; i <= n; i++) {
            combineRes.add(i);
            backtrace(n, k, i + 1);
            combineRes.removeLast();
        }
    }


    //90. 子集 II
    List<List<Integer>> subResult = new ArrayList<>();
    LinkedList<Integer> subRes = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtraceSub(nums, 0);
        return subResult;
    }

    public void backtraceSub(int[] nums, int index) {
        subResult.add(new ArrayList<>(subRes));
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i - 1] == nums[i]) {
                continue;
            }
            subRes.add(nums[i]);
            backtraceSub(nums, i + 1);
            subRes.removeLast();
        }
    }


    //    组合总和 II
    List<List<Integer>> combinationResult = new ArrayList<>();
    LinkedList<Integer> combinationRes = new LinkedList<>();


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationBacktrace(candidates, target, 0);
        return combinationResult;
    }

    public void combinationBacktrace(int[] candidates, int target, int index) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            combinationResult.add(new ArrayList<>(combinationRes));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            combinationRes.add(candidates[i]);
            combinationBacktrace(candidates, target - candidates[i], i + 1);
            combinationRes.removeLast();
        }
    }


    List<List<Integer>> permuteResult = new ArrayList<>();
    LinkedList<Integer> permuteRes = new LinkedList<>();

    //     47. 全排列 II

    /**
     * // 新添加的剪枝逻辑，固定相同的元素在排列中的相对位置
     * if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
     * // 如果前面的相邻相等元素没有用过，则跳过
     * continue;
     * }
     * // 选择 nums[i]
     * 当出现重复元素时，比如输入 nums = [1,2,2',2'']，2' 只有在 2 已经被使用的情况下才会被选择，同理，2'' 只有在 2' 已经被使用的情况下才会被选择，这就保证了相同元素在排列中的相对位置保证固定。
     */
    public List<List<Integer>> permuteUnique(int[] nums) {

        boolean[] used = new boolean[nums.length];
        Arrays.fill(used, false);
        Arrays.sort(nums);
        backtracePermute(nums, used);
        return permuteResult;
    }

    public void backtracePermute(int[] nums, boolean[] used) {
        if (permuteRes.size() == nums.length) {
            permuteResult.add(new ArrayList<>(permuteRes));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            if (used[i]) {
                continue;
            }
            permuteRes.add(nums[i]);
            used[i] = true;
            backtracePermute(nums, used);
            used[i] = false;
            permuteRes.removeLast();
        }
    }


    List<List<Integer>> comSumResult = new ArrayList<>();
    LinkedList<Integer> comSumRes = new LinkedList<>();

    //    39. 组合总和
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtraceSum(candidates,target,0);
        return comSumResult;
    }

    public void backtraceSum(int[] candidates, int target, int index) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            comSumResult.add(new ArrayList<>(comSumRes));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            comSumRes.add(candidates[i]);
            backtraceSum(candidates,target-candidates[i],i);
            comSumRes.removeLast();
        }
    }
}
