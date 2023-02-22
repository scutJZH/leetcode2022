package com.jzh.leetcode;

/**
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 *     1 <= s.length <= 1000
 *     s 仅由小写英文字母组成
 *
 * 思路：
 *  解1：最长回文子序列就是字符串与其逆序的最长公共子序列
 *  解2：由解1转换为动态规划
 *  解3：从L...R上找最长回文子序列
 *  解4：由解3转化为动态规划
 */
public class _0516_LongestPalindromicSubsequence {

    public static void main(String[] args) {
        String s = "bbbab";
        System.out.println(longestPalindromeSubseq1(s));
        System.out.println(longestPalindromeSubseq2(s));
        System.out.println(longestPalindromeSubseq3(s));
        System.out.println(longestPalindromeSubseq4(s));
    }

    /**
     * 最长回文子序列就是字符串与其逆序的最长公共子序列
     */
    public static int longestPalindromeSubseq1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }


        char[] chars1 = s.toCharArray();
        char[] chars2 = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            chars2[s.length() - 1 - i] = chars1[i];
        }

        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                dp[i][j] = -1;
            }
        }

        return process1(chars1, chars2, s.length() - 1, s.length() - 1, dp);
    }

    /**
     * 返回chars1[0 ~ i]和chars2[0 ~ j]上的最长公共序列
     * i: 0 ~ chars1.length - 1
     * j: 0 ~ chars2.length - 1
     */
    public static int process1(char[] chars1, char[] chars2, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (i == 0 && j == 0) {
            dp[i][j] = chars1[i] == chars2[j] ? 1 : 0;
        } else if (i == 0) {
            dp[i][j] = chars1[i] == chars2[j] ? 1 : process1(chars1, chars2, i, j - 1, dp);
        } else if (j == 0) {
            dp[i][j] = chars1[i] == chars2[j] ? 1 : process1(chars1, chars2, i - 1, j, dp);
        } else {
            dp[i][j] = chars1[i] == chars2[j] ? 1 + process1(chars1, chars2, i - 1, j - 1, dp) : Math.max(process1(chars1, chars2, i, j - 1, dp), process1(chars1, chars2, i - 1, j, dp));
        }

        return dp[i][j];
    }

    public static int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }


        char[] chars1 = s.toCharArray();
        char[] chars2 = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            chars2[s.length() - 1 - i] = chars1[i];
        }

        int[][] dp = new int[s.length()][s.length()];
        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;
        for (int j = 1; j < s.length(); j++) {
            dp[0][j] = chars1[0] == chars2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < s.length(); i++) {
            dp[i][0] = chars1[i] == chars2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < s.length(); i++) {
            for (int j = 1; j < s.length(); j++) {
                dp[i][j] = chars1[i] == chars2[j] ? 1 + dp[i - 1][j - 1] : Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }

        return dp[s.length() - 1][s.length() - 1];
    }

    /**
     * 从L - R上找最长回文子序列
     */
    public static int longestPalindromeSubseq3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                dp[i][j] = -1;
            }
        }

        return process3(s.toCharArray(), 0, s.length() - 1, dp);
    }

    /**
     * 从L - R上找最长回文子序列
     */
    public static int process3(char[] chars, int l, int r, int[][] dp) {
        if (dp[l][r] != -1) {
            return dp[l][r];
        }

        if (l == r) {
            dp[l][r] = 1;
        } else if (l == r - 1) {
            dp[l][r] = chars[l] == chars[r] ? 2 : 1;
        } else {
            dp[l][r] = chars[l] == chars[r] ? 2 + process3(chars, l + 1, r - 1, dp) : Math.max(process3(chars, l + 1, r, dp), process3(chars, l, r - 1, dp));
        }

        return dp[l][r];
    }

    public static int longestPalindromeSubseq4(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();

        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        for (int i = 1; i < s.length(); i++) {
            dp[i - 1][i] = chars[i - 1] == chars[i] ? 2 : 1;
        }

        for (int i = chars.length - 3; i >= 0; i--) {
            for(int j = i + 2; j < chars.length; j++) {
                dp[i][j] = chars[i] == chars[j] ? 2 + dp[i + 1][j - 1] : Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }

        return dp[0][s.length() - 1];
    }
}
