package com.jzh.leetcode;

/**
 * 给两个整数数组nums1和nums2，返回 两个数组中公共的 、长度最长的子数组的长度。
 *
 * 思路：设定dp[i][j]为以nums1[i]和nums2[j]结尾的字符串中，后缀公共子字符串的最大长度
 *  如果nums1[i] = nums[j]则是dp[i - 1][j - 1] + 1，否则为0
 */
public class _0718_MaximumLengthOfRepeatedSubarray {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,2,1}, nums2 = new int[]{3,2,1,4,7};
        System.out.println(findLength(nums1, nums2));
    }

    public static int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length][nums2.length];
        int ans = 0;
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] == nums2[0]) {
                dp[i][0] = 1;
                ans = 1;
            }
        }
        for (int i = 0; i < nums2.length; i++) {
            if (nums1[0] == nums2[i]) {
                dp[0][i] = 1;
                ans = 1;
            }
        }

        // 两个数组中以i和j结尾的子数组的最长公共后缀长度
        for (int i = 1; i < nums1.length; i++) {
            for (int j = 1; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }

        return ans;
    }
}


