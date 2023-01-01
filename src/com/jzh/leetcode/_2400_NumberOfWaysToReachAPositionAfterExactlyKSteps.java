package com.jzh.leetcode;

import java.util.Arrays;

/**
 * 给你两个 正 整数 startPos 和 endPos 。最初，你站在 无限 数轴上位置 startPos 处。在一步移动中，你可以向左或者向右移动一个位置。
 * 给你一个正整数 k ，返回从 startPos 出发、恰好 移动 k 步并到达 endPos 的 不同 方法数目。由于答案可能会很大，返回对 10^9 + 7 取余 的结果。
 * 如果所执行移动的顺序不完全相同，则认为两种方法不同。
 * 注意：数轴包含负整数。
 * 1 <= startPos, endPos, k <= 1000
 *
 * 思路：
 *  解1：由于是在无限数轴上移动，没有边界，用dp[i][j]表示从i点到end还需要j步的话，数组过大，会超过时间限制，故通过计算向左多少步，向右多少步才能到达目标点，可以将题目转化为在K步里，向左走L步怎么插空进去
 *  解2：将递归转化为动态规划
 */
public class _2400_NumberOfWaysToReachAPositionAfterExactlyKSteps {

    public static void main(String[] args) {
        System.out.println(numberOfWays1(1, 2, 3));
        System.out.println(numberOfWays2(1, 2, 3));
    }

    public static int numberOfWays1(int startPos, int endPos, int k) {
        if (k < Math.abs(endPos - startPos) || (k + startPos - endPos) % 2 == 1 || (k - startPos + endPos) % 2 == 1) {
            return 0;
        }

        int l = (k + startPos - endPos) / 2; // 向左走L步
        int r = (k - startPos + endPos) / 2; // 向右走R步

        // 则问题变为了插空问题，K步中，L步的位置
        int[][] dp = new int[l + 1][k + 1];
        for (int i = 0; i < l + 1; i++) {
            for (int j = 0; j < k + 1; j++) {
                dp[i][j] = -1;
            }
        }
        int ans = process(l, k, dp);
        return ans;
    }

    public static int process(int restL, int restK, int[][] dp) {
        if (dp[restL][restK] != -1) {
            return dp[restL][restK];
        } else if (restL == 0 || restL == restK) {
            dp[restL][restK] = 1;
            return dp[restL][restK];
        } else if (restL > restK) {
            dp[restL][restK] = 0;
            return 0;
        }

        // 可填L也可不填
        dp[restL][restK] = (process(restL - 1, restK - 1, dp) + process(restL, restK - 1, dp)) % 1000000007;
        return dp[restL][restK];
    }

    public static int numberOfWays2(int startPos, int endPos, int k) {
        if (k < Math.abs(endPos - startPos) || (k + startPos - endPos) % 2 == 1 || (k - startPos + endPos) % 2 == 1) {
            return 0;
        }

        int l = (k + startPos - endPos) / 2; // 向左走L步
        // 则问题变为了插空问题，K步中，L步的位置
        int[][] dp = new int[l + 1][k + 1];
        // 当l步为0时或两个相等时，初始化为1， 当l>k时初始化为0
        for (int i = 0; i < l + 1; i++) {
            // 令 j=i，则消除了l>k的情况
            for (int j = i; j < k + 1; j++) {
                if (i == 0 || j == i) {
                    dp[i][j] = 1;
                }
            }
        }

        for (int i = 1; i < l + 1; i++) {
            for (int j = i; j < k + 1; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i][j - 1]) % 1000000007;
            }
        }
        return dp[l][k];
    }

}
