package com.jzh.leetcode;

/**
 * 给你一个整数数组 nums 。玩家 1 和玩家 2 基于这个数组设计了一个游戏。
 * 玩家 1 和玩家 2 轮流进行自己的回合，玩家 1 先手。开始时，两个玩家的初始分值都是 0 。每一回合，玩家从数组的任意一端取一个数字（即，nums[0] 或 nums[nums.length - 1]），取到的数字将会从数组中移除（数组长度减 1 ）。玩家选中的数字将会加到他的得分上。当数组中没有剩余数字可取时，游戏结束。
 * 如果玩家 1 能成为赢家，返回 true 。如果两个玩家得分相等，同样认为玩家 1 是游戏的赢家，也返回 true 。你可以假设每个玩家的玩法都会使他的分数最大化。
 *
 * 思路：玩牌时分别会从[L...R]范围内先手拿和后手拿
 * 解1：
 *  先手拿：需要满足少了一张牌后的后手拿+当前牌最大，即Max(n[L] + second(L+1, R), n[R] + second(L, R-1))
 *  后手拿：如果对手拿了n[L]，则我应该在(L+1, R)的范围内先手拿，如果对手拿了n[R]，则我应该在(L, R-1)先手拿，显然，对手会让我的牌尽可能的小，即Min(first(L+1, R), first(L, R-1))
 *
 * 解2：
 *  由递归衍生出的动态规划
 */
public class _0486_PredictTheWinner {
    public static void main(String[] args) {
        int[] nums = new int[]{2,4,55,6,8};
        System.out.println(PredictTheWinner(nums));
        System.out.println(PredictTheWinner2(nums));
    }
    public static boolean PredictTheWinner(int[] nums) {
        int[][] firstDp = new int[nums.length][nums.length];
        int[][] secondDp = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                firstDp[i][j] = -1;
                secondDp[i][j] = -1;
            }
        }

        int first = firstGet(nums, 0, nums.length - 1, firstDp);
        int second = secondGet(nums, 0, nums.length - 1, secondDp);
        return first >= second;
    }

    // 先手拿的分数
    public static int firstGet(int[]nums, int l, int r, int[][] dp) {
        if (dp[l][r] != -1) {
            return dp[l][r];
        }

        if (l == r) {
            dp[l][r] = nums[l];
            return dp[l][r];
        }

        dp[l][r] = Math.max(secondGet(nums, l + 1, r, dp) + nums[l], secondGet(nums, l, r - 1, dp) + nums[r]);
        return dp[l][r];
    }

    // 后手拿的分数
    public static int secondGet(int[] nums, int l, int r, int[][] dp) {
        if (dp[l][r] != -1) {
            return dp[l][r];
        }

        // 只剩一张牌时，后手肯定没牌拿
        if (l == r) {
            dp[l][r] = 0;
            return 0;
        }

        int f1 = firstGet(nums, l + 1, r, dp); // 对手已经拿走l位置的牌，我只能从[l+1...r]位置先手拿
        int f2 = firstGet(nums, l, r - 1, dp); // 对手已经拿走r位置的牌，我只能从[l...r-1]位置先手拿
        // 由于博弈，对手肯定想让我的牌尽可能的小，所以我只能从更小的拿
        dp[l][r] = Math.min(f1, f2);
        return dp[l][r];
    }

    public static boolean PredictTheWinner2(int[] nums) {
        int[][] firstDp = new int[nums.length][nums.length];
        int[][] secondDp = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 仅剩最后一张牌
            firstDp[i][i] = nums[i];
            secondDp[i][i] = 0;
        }

        // 观察表结构
        for (int i = 1; i < nums.length; i++) {
            int l = 0;
            int r = i;

            while (r < nums.length) {
                firstDp[l][r] = Math.max(nums[l] + secondDp[l + 1][r], nums[r] + secondDp[l][r - 1]);
                secondDp[l][r] = Math.min(firstDp[l + 1][r], firstDp[l][r - 1]);
                l++;
                r++;
            }

        }

        return firstDp[0][nums.length - 1] >= secondDp[0][nums.length - 1];
    }
}
