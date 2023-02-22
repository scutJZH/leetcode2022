package com.jzh.leetcode;

import javax.swing.text.html.StyleSheet;

/**
 * 给定两个字符串text1 和text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *     例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 * 思路：先递归，返回chars1[0 ~ i]范围和chars2[0 ~ j]范围内最长子序列 -> 发现有重复计算：记忆化搜索 -> 有表结构：动态规划
 */
public class _1143_LongestCommonSubsequence {

    public static void main(String[] args) {
        String text1 = "abcde", text2 = "ace";
        System.out.println(longestCommonSubsequence1(text1, text2));
        System.out.println(longestCommonSubsequence2(text1, text2));
    }

    public static int longestCommonSubsequence1(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        return process1(text1.toCharArray(), text2.toCharArray(), text1.length() - 1, text2.length() - 1);
    }

    // 返回chars1[0 ~ i]范围和chars2[0 ~ j]范围内最长子序列
    public static int process1(char[] chars1, char[] chars2, int i, int j) {
        if (i == 0 && j == 0) {
            return chars1[i] == chars2[j] ? 1 : 0;
        }
        if (i == 0) {
            return chars1[i] == chars2[j] ? 1 : process1(chars1, chars2, i, j - 1);
        }
        if (j == 0) {
            return chars1[i] == chars2[j] ? 1 : process1(chars1, chars2, i - 1, j);
        }
        int p1 = process1(chars1, chars2, i, j - 1);
        int p2 = process1(chars1, chars2, i - 1, j);
        int p3 = chars1[i] == chars2[j] ? 1 + process1(chars1, chars2, i - 1, j - 1) : process1(chars1, chars2, i - 1, j - 1);
        return Math.max(p1, Math.max(p2, p3));
    }

    public static int longestCommonSubsequence2(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();


        int[][] dp = new int[chars1.length][chars2.length];

        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;
        // i == 0时初始化
        for (int j = 1; j < chars2.length; j++) {
            dp[0][j] = chars1[0] == chars2[j] ? 1 : dp[0][j - 1];
        }
        // j==0时初始化
        for (int i = 1; i < chars1.length; i++) {
            dp[i][0] = chars1[i] == chars2[0] ? 1 :dp[i - 1][0];
        }
        for (int i = 1; i < chars1.length; i++) {
            for (int j = 1; j < chars2.length; j++) {
                if (chars1[i] == chars2[j]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[chars1.length - 1][chars2.length - 1];
    }
}
