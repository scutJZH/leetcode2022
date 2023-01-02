package com.jzh.leetcode;

import java.util.Arrays;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回-1 。
 * 你可以认为每种硬币的数量是无限的。
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 *
 * 思路：
 *  解1：递归，设当前是否使用coins[index]处的硬币（硬币只能使用index - coins.length - 1)
 *      优化：coins从小到大排序
 *  解2：根据递归衍生出的动态规划
 */
public class _0322_CoinChange {

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        System.out.println(coinChange1(coins, 11));
        System.out.println(coinChange2(coins, 11));
    }

    public static int coinChange1(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (coins == null || coins.length == 0) {
            return -1;
        }

        Arrays.sort(coins);

        // index：0~coins.length
        // restAmount: 0~amount
        int dp[][] = new int[coins.length + 1][amount + 1];
        for (int i = 0; i < coins.length + 1; i++) {
            for (int j = 0; j < amount + 1; j++) {
                dp[i][j] = -2;
            }
        }

        return process1(coins, 0, amount, dp);
    }

    /**
     * 假设硬币只能顺序使用
     */
    public static int process1(int[] coins, int index, int restAmount, int[][] dp) {
        if (dp[index][restAmount] != -2) {
            return dp[index][restAmount];
        }

        if (restAmount == 0) {
            return 0;
        }

        if (index == coins.length) {
            return -1;
        }

        // 是否使用coins[index]处的硬币
        if (coins[index] <= restAmount) {
            // 使用
            int p1 = process1(coins, index, restAmount - coins[index], dp);
            // 不使用
            int p2 = process1(coins, index + 1, restAmount, dp);
            int ans;
            if (p1 == -1 && p2 == -1) {
                ans = -1;
            } else if (p1 == -1) {
                ans = p2;
            } else if (p2 == -1) {
                ans = p1 + 1;
            } else {
                ans = Math.min(p1 + 1, p2);
            }
            dp[index][restAmount] = ans;
        } else {
            dp[index][restAmount] = -1;
        }
        return dp[index][restAmount];
    }

    public static int coinChange2(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (coins == null || coins.length == 0) {
            return -1;
        }

        Arrays.sort(coins);

        // index：0~coins.length
        // restAmount: 0~amount
        int dp[][] = new int[coins.length + 1][amount + 1];

        for (int i = 0; i < amount + 1; i++) {
            dp[coins.length][i] = -1;
        }

        // restAmount =  restAmount - coins[index] -> restAmount从左到右遍历
        // index = index + 1 -> 从右往左遍历
        for (int index = coins.length - 1; index >= 0; index--) {
            // restAmount == 0 -> return 0
            for (int restAmount = 1; restAmount < amount + 1; restAmount++) {
                if (coins[index] <= restAmount) {
                    int p1 = dp[index][restAmount - coins[index]];
                    int p2 = dp[index + 1][restAmount];
                    if (p1 == -1 && p2 == -1) {
                        dp[index][restAmount] = -1;
                    } else if (p1 == -1) {
                        dp[index][restAmount] = p2;
                    } else if (p2 == -1) {
                        dp[index][restAmount] = p1 + 1;
                    } else {
                        dp[index][restAmount] = Math.min(p1 + 1, p2);
                    }
                } else {
                    dp[index][restAmount] = dp[index + 1][restAmount];
                }
            }
        }

        return dp[0][amount];
    }
}
