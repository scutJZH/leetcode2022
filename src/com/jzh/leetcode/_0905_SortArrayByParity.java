package com.jzh.leetcode;

// 给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
// 思路：
// 解：左右指针
public class _0905_SortArrayByParity {
    public static void main(String[] args) {
        int[] nums = new int[]{3,1,2,4};
        int[] sorted = sortArrayByParity(nums);
        for (int i : sorted) {
            System.out.println(i + " ");
        }
    }

    public static int[] sortArrayByParity(int[] nums) {
        int p1 = 0;
        int p2 = nums.length - 1;
        for (int i = 0; i <= p2;) {
            if (nums[i] % 2 == 0) {
                swap(nums, i++, p1++);
            } else {
                swap(nums, i, p2--);
            }
        }
        return nums;
    }

    public static void swap(int[]nums, int i, int j) {
        if (i != j) {
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }
    }
}
