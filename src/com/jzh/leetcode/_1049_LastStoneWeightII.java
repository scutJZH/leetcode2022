package com.jzh.leetcode;

/**
 * 有一堆石头，用整数数组stones 表示。其中stones[i] 表示第 i 块石头的重量。
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为x 和y，且x <= y。那么粉碎的可能结果如下：
 * 如果x == y，那么两块石头都会被完全粉碎；
 * 如果x != y，那么重量为x的石头将会完全粉碎，而重量为y的石头新重量为y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 * <p>
 * 思路：可以将题目转化为，将数组分为差值最小的两个数组
 * 假设更小的数组的质量为weight，则另一个数组的质量为sum-x
 * 差值为sum - 2*weight
 * 可以将该问题看做01背包问题，是否将stones[i]放入较小的堆
 *  优化：dp[i][j] 只依赖 dp[i + 1][j]和dp[i + 1][j + stones[i]]，所以可以使用一维数组自我更新，因为j 依赖 j 和 j + stones[i]，故从小到大自我更新
 */
public class _1049_LastStoneWeightII {

    public static void main(String[] args) {
        int[] stones = new int[]{31,26,33,21,40};
        System.out.println(lastStoneWeightII(stones));
        System.out.println(lastStoneWeightII2(stones));
    }

    public static int lastStoneWeightII(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }


        int maxWeight = sum / 2; // 较小堆的最大质量
        int[][] dp = new int [stones.length + 1][maxWeight + 1];
        for (int i = 0; i < maxWeight + 1; i++) {
            dp[stones.length][i] = sum - 2 * i;
        }
        for (int i = stones.length - 1; i >= 0; i--) {
            for (int j = 0; j < maxWeight + 1; j++) {
                dp[i][j] = dp[i + 1][j];
                int weight = j + stones[i];
                if (weight <= maxWeight) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][weight]);
                }
            }
        }

        return dp[0][0];
    }

    /**
     * 思路
     */
    public static int process(int[] stones, int sum, int weight, int index) {
        if (index == stones.length) {
            return sum - 2 * weight;
        }

        if ((weight + stones[index]) * 2 <= sum) {
            return Math.min(process(stones, sum, weight + stones[index], index + 1), process(stones, sum, weight, index + 1));
        } else {
            return process(stones, sum, weight, index + 1);
        }
    }

    /**
     * 数组压缩
     */
    public static int lastStoneWeightII2(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }


        int maxWeight = sum / 2; // 较小堆的最大质量
        int[] dp = new int[maxWeight + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = sum - 2 * i;
        }

        for (int i = stones.length - 1; i >= 0; i--) {
            for (int j = 0; j < dp.length; j++) {
                int weight = j + stones[i];
                if (weight <= maxWeight) {
                    dp[j] = Math.min(dp[weight], dp[j]);
                }
            }
        }

        return dp[0];
    }
}
