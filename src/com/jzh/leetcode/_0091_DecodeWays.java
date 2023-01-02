package com.jzh.leetcode;

import java.util.Arrays;

/**
 * 一条包含字母A-Z 的消息通过以下映射进行了编码 ：'A' -> "1",'B' -> "2",...,'Z' -> "26"
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：① "AAJF" ，将消息分组为 (1 1 10 6)；② "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * 题目数据保证答案肯定是一个 32 位 的整数。
 *
 * 思路：
 * 解1：递归，找边界条件：不能0开头，不能超过26.从左到右遍历字符串即可，选择该位要不要解码
 * 解2：动态规划
 */
public class _0091_DecodeWays {

    public static void main(String[] args) {
        System.out.println(numDecodings1("12"));
        System.out.println(numDecodings2("12"));
    }

    public static int numDecodings1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // index： 0 ~ s.length
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);

        return process1(s.toCharArray(), 0, dp);
    }

    public static int process1(char[] arr, int index, int[] dp) {
        if (dp[index] != -1) {
            return dp[index];
        }

        // 遍历完成，方法数+1
        if (index == arr.length) {
            dp[index] = 1;
        } else if (arr[index] == '0') {
            // 如果为0，则说明应该放到上面一个，所以这个结果不能要(0没有对应，0x也没有对应)
            dp[index] = 0;
        } else if (index == arr.length - 1 || (arr[index] - '0') * 10 + (arr[index + 1] - '0') > 26) {
            // 如果index是最后一位或这位和下一位相加大于26，则必须要该位
            dp[index] = process1(arr, index + 1, dp);
        } else {

            dp[index] = process1(arr, index + 1, dp) + process1(arr, index + 2, dp);
        }
        return dp[index];
    }

    public static int numDecodings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] arr = s.toCharArray();
        // index： 0 ~ s.length
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;


        // 递归时，dp[i] 需要依赖 dp[index + 1]和dp[index + 2]，故倒序
        for (int i = s.length() - 1; i >= 0; i--) {
            if (arr[i] == '0') {
                dp[i] = 0;
            } else if (i == arr.length - 1 || (arr[i] - '0') * 10 + (arr[i + 1] - '0') > 26) {
                dp[i] = dp[i + 1];
            } else {
                dp[i] = dp[i + 1] + dp[i + 2];
            }
        }

        return dp[0];
    }
}
