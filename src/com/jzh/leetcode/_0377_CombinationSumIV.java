package com.jzh.leetcode;

import java.util.Arrays;

/**
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 题目数据保证答案符合 32 位整数范围。
 * 请注意，顺序不同的序列被视作不同的组合。
 *
 * 思路：递归 -> 动态规划
 *  优化：对nums排序，用于剪枝：i < nums.length && nums[i] <= rest
 */
public class _0377_CombinationSumIV {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        int target = 4;
        System.out.println(combinationSum4(nums, target));
    }

    public static int combinationSum4(int[] nums, int target) {
        if (target == 0) {
            return 1;
        } else if (nums == null || nums.length == 0) {
            return 0;
        }

        // 从小到大排序，用于剪枝
        Arrays.sort(nums);

        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int rest = 1; rest < dp.length; rest++) {
            for (int i = 0; i < nums.length && nums[i] <= rest; i++) {
                dp[rest] += dp[rest - nums[i]];
            }
        }

        return dp[target];
    }

    public static int process(int[] nums, int rest) {
        if (rest == 0) {
            return 1;
        }

        int ways = 0;
        for (int i = 0; i < nums.length && nums[i] <= rest; i++) {
            ways += process(nums, rest - nums[i]);
        }

        return ways;
    }
}
