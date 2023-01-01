package com.jzh.self;

/**
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE"是"ABCDE"的一个子序列，而"AEC"不是）
 * 题目数据保证答案符合 32 位带符号整数范围。
 * <p>
 * 思路：
 * 解1：递归：两个参数i和j分别表示读到s的第i个字符和读到t的第j个字符，如果s[i]!=t[j] 则比较s[i+1]和t[j]；如果s[i]==t[j]，则可以比较s[i+1]和t[j]也可以比较s[i+1]和t[j+1]
 * 解2：动态规划：由递归可知，当j=t.length时，dp[i][j]=1，当j!=t.length+1&&i=s.length时，dp[i][j]=0，又有s[i]!=t[j]时，dp[i][j] = dp[i+1][j]; s[i]==t[j]时，dp[i][j]=dp[i+1][j]+dp[i+1][j+1]
 */
public class _0070_NumberOfSubsequences {
    public static void main(String[] args) {
        String s = "rabbbit";
        String t = "rabbit";
        System.out.println(numDistinct2(s, t));
    }

    public static int numDistinct(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return 0;
        }

        // 缓存表
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i < s.length() + 1; i++) {
            for (int j = 0; j < t.length() + 1; j++) {
                dp[i][j] = -1;
            }
        }

        return process(s.toCharArray(), t.toCharArray(), 0, 0, dp);
    }

    public static int process(char[] s, char[] t, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        // t匹配完
        if (j == t.length) {
            dp[i][j] = 1;
            return 1;
        }
        // s走完了还没有匹配完t
        if (i == s.length) {
            dp[i][j] = 0;
            return 0;
        }

        // 当s的第i为和t的第j位不相同时，跳过，找i+1位
        int ans = 0;
        if (s[i] != t[j]) {
            ans = process(s, t, i + 1, j, dp);
        } else {
            // 可以选择要i位，也可以选择不要i位
            ans = process(s, t, i + 1, j + 1, dp) + process(s, t, i + 1, j, dp);
        }
        dp[i][j] = ans;
        return ans;
    }

    public static int numDistinct2(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return 0;
        }
        // 初始化dp
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][t.length()] = 1;
        }
        for (int i = 0; i < t.length(); i++) {
            dp[s.length()][i] = 0;
        }

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        // 由图表可知，可按倒序来填表
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = t.length() - 1; j >= 0; j--) {
                if (sChars[i] != tChars[j]) {
                    dp[i][j] = dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                }
            }
        }

        return dp[0][0];
    }
}
