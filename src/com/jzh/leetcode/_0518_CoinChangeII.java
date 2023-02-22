package com.jzh.leetcode;

/**
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。
 * <p>
 * 思路：动态规划，dp[i][j]表示用到第i张时还剩j金额
 */
public class _0518_CoinChangeII {

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        int amount = 5;
        System.out.println(change(amount, coins));
        System.out.println(change2(amount, coins));
    }

    public static int change(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        if (coins == null || coins.length == 0) {
            return 0;
        }

        // dp[i][j]表示用到第i张时还剩j金额
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < amount + 1; j++) {
            dp[coins.length][j] = 0;
        }

        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = 1; j < amount + 1; j++) {
                int count = 0;
                for (int k = 0; k * coins[i] <= j; k++) {
                    count += dp[i + 1][j - k * coins[i]];
                }
                dp[i][j] = count;
            }
        }

        return dp[0][amount];
    }

    /**
     * 数据压缩
     */
    public static int change2(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        if (coins == null || coins.length == 0) {
            return 0;
        }

        // dp[i][j]表示用到第i张时还剩j金额
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < amount + 1; j++) {
            dp[coins.length][j] = 0;
        }

        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = 1; j < amount + 1; j++) {
                // 通过表格观察可发现规律
                if (j >= coins[i]) {
                    dp[i][j] += dp[i][j - coins[i]];
                }
                dp[i][j] += dp[i + 1][j];
            }
        }

        return dp[0][amount];
    }


}
