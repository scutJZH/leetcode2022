package com.jzh.leetcode;

/**
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *
 * 思路：动态规划，dp[i][j][k]代表遍历到第i个，当前还剩j个0和k个1。
 *  优化思路：空间压缩，dp使用二维数组自我更新
 */
public class _0474_OnesAndZeroes {

    public static void main(String[] args) {
        String[] strs = new String[]{"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;
        System.out.println(findMaxForm(strs, m, n));
        System.out.println(findMaxForm2(strs, m, n));
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        if ((m == 0 && n == 0) || strs == null || strs.length == 0) {
            return 0;
        }

        // 统计每个str中m和n的个数
        int[][] nums = new int[strs.length][2];
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            for (char c : chars) {
                nums[i][c - '0']++;
            }
        }

        int[][][] dp = new int[nums.length + 1][m + 1][n + 1];
        // 默认dp[i][0][0]和dp[nums.length][j][k]都为0
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = 0; j < m + 1; j++) {
                for (int k = 0; k < n + 1; k++) {
                    if (j > 0 || k > 0) {
                        dp[i][j][k] = dp[i + 1][j][k];
                        if (nums[i][0] <= j && nums[i][1] <= k) {
                            dp[i][j][k] = Math.max(1 + dp[i + 1][j - nums[i][0]][k - nums[i][1]], dp[i][j][k]);
                        }
                    }
                }
            }
        }

        return dp[0][m][n];
    }

    public static int process(int[][] nums, int index, int m, int n) {
        if ((m == 0 && n == 0) || index == nums.length) {
            return 0;
        }
        if (nums[index][0] <= m && nums[index][1] <= n) {
            return Math.max(1 + process(nums, index + 1, m - nums[index][0], n - nums[index][1]), process(nums, index + 1, m, n));
        } else {
            return process(nums, index + 1, m, n);
        }
    }

    /**
     * 空间压缩
     */
    public static int findMaxForm2(String[] strs, int m, int n) {
        if ((m == 0 && n == 0) || strs == null || strs.length == 0) {
            return 0;
        }

        // 统计每个str中m和n的个数
        int[][] nums = new int[strs.length][2];
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            for (char c : chars) {
                nums[i][c - '0']++;
            }
        }

        // int[][][] dp = new int[nums.length + 1][m + 1][n + 1];
        // 可以看出，dp[i][j][k] 只依赖dp[i + 1][j][k]（正上方）和1 + dp[i + 1][j - zero]][k - one]（斜上方）的格子
        // 故可以使用二维数组优化空间，进行自我更新
        int[][] dp = new int[m + 1][n + 1];
        for (int i = nums.length - 1; i >= 0; i--) {
            int zero = nums[i][0];
            int one = nums[i][1];
            // 由dp[i + 1][j - zero]][k - one]可知，当j<zero || k < one时，只关心正上方，故不用更新
            // 由于dp[i][j][k]依赖dp[i + 1][j - zero]][k - one]，故应从大到小自我更新
            for (int j = m; j >= zero; j--) {
                for (int k = n; k >= one; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zero][k - one] + 1);
                }
            }
        }

        return dp[m][n];
    }
}
