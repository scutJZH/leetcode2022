package com.jzh.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个长度为n的整数数组nums，请你判断在 最多 改变1 个元素的情况下，该数组能否变成一个非递减数列。
 * 我们是这样定义一个非递减数列的：对于数组中任意的i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 *
 * 思路：从左到右进行遍历
 *  1.最多可以调整一个节点，并且调整时尽可能的让max小。故当遇到nums[i] < num[i-1]时，通过再和nums[i-2]比较，确定是调整num[i-1]还是nums[i]
 */
public class _0665_NonDecreasingArray {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 4, 1, 2};
        System.out.println(checkPossibility(nums));
    }

    public static boolean checkPossibility(int[] nums) {
        if (nums == null) {
            return false;
        }

        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            // 遇到右边小于左边时则需要进行调整
            if(nums[i] < nums[i - 1]) {
                if (i == 1 || nums[i] >= nums[i - 2]) {
                    // 尽量使max更小，方便后续更容易满足非递减条件
                    nums[i - 1] = nums[i];
                } else {
                    nums[i] = nums[i - 1];
                }
                count++;
            }
        }
        return count < 2;
    }
}
