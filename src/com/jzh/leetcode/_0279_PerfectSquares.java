package com.jzh.leetcode;

/**
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * 1 <= n <= 10^4
 *
 * 思路：动态规划，max不会超过根号n
 *
 */
public class _0279_PerfectSquares {

    public static void main(String[] args) {
        int n = 13;
        System.out.println(numSquares1(n));
        System.out.println(numSquares2(n));
    }


    public static int numSquares1(int n) {
        return process1(n);
    }

    public static int process1(int rest) {
        if (rest == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;

        for (int i = 1; i * i <= rest; i++) {
            min = Math.min(min, process1(rest - i * i) + 1);
        }

        return min;
    }

    /**
     * 动态规划
     */
    public static int numSquares2(int n) {
        int[] dp = new int[n + 1];

        for (int rest = 1; rest < dp.length; rest++) {
            dp[rest] = Integer.MAX_VALUE;
            for (int i = 1; i * i <= rest; i++) {
                dp[rest] = Math.min(dp[rest], dp[rest - i * i] + 1);
            }
        }

        return dp[n];
    }


}
