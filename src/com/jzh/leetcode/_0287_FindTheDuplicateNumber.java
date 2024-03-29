package com.jzh.leetcode;

// 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
// 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
// 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。

// 思路：快慢指针
public class _0287_FindTheDuplicateNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{3,1,3,4,2};
        System.out.println(findDuplicate(nums));
    }

    public static int findDuplicate(int[] nums) {
        boolean[] temp = new boolean[nums.length];
        for (int n : nums) {
            if (temp[n]) {
                return n;
            }
            temp[n] = true;
        }
        return 0;
    }
}
