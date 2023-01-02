package com.jzh.niuke;

/**
 * 描述
 * 已知一个背包最多能容纳体积之和为v的物品
 * 现有 n个物品，第 i个物品的体积为 vi , 重量为 wi
 * 求当前背包最多能装多大重量的物品?
 * 数据范围： 1≤v≤10001≤v≤1000，1≤n≤1000，1≤vi≤1000，1≤wi≤1000
 *
 * 思路：
 *  解1：递归， 每次递归选择要当前产品或不要当前产品（只有满足剩余容量能装下该产品才能要当前产品）
 *  解2：根据递归演化的动态规划
 */
public class NC145_BagQuestion {

    public static void main(String[] args) {
        int[][] vw = new int[][]{{1, 3}, {10, 4}};
        System.out.println(knapsack1(10, 2, vw));
        System.out.println(knapsack2(10, 2, vw));
    }

    public static int knapsack1(int V, int n, int[][] vw) {
        // write code here
        if (vw.length != n || V < 0 || n == 0) {
            return 0;
        }
        // index 0 ~ N
        // rest 0 ~ V
        int[][] dp = new int[V + 1][vw.length + 1];
        for (int i = 0; i < V + 1; i++) {
            for (int j = 0; j < vw.length + 1; j++) {
                dp[i][j] = -1;
            }
        }

        return process(V, 0, vw, dp);
    }

    public static int process(int rest, int index, int[][] vw, int[][] dp) {
        if (dp[rest][index] != -1) {
            return dp[rest][index];
        }

        if (rest == 0 || index == vw.length) {
            dp[rest][index] = 0;
            return dp[rest][index];
        }

        // 要当前产品或不要当前产品
        int max = 0;
        int currentV = vw[index][0];
        if (currentV <= rest) {
            max = Math.max(vw[index][1] + process(rest - currentV, index + 1, vw, dp), process(rest, index + 1, vw, dp));
        } else {
            max = process(rest, index + 1, vw, dp);
        }

        dp[rest][index] = max;
        return dp[rest][index];
    }

    public static int knapsack2(int V, int n, int[][] vw) {
        if (vw.length != n || V < 0 || n == 0) {
            return 0;
        }
        // index 0 ~ N
        // rest 0 ~ V
        int[][] dp = new int[V + 1][vw.length + 1];

        for (int index = vw.length - 1; index >= 0; index--) {
            for (int restV = 1; restV < V + 1; restV++) {
                if (vw[index][0] <= restV) {
                    dp[restV][index] = Math.max(vw[index][1] + dp[restV - vw[index][0]][index + 1], dp[restV][index + 1]);
                } else {
                    dp[restV][index] = dp[restV][index + 1];
                }
            }
        }

        return dp[V][0];
    }
}
