package com.jzh.leetcode;

// 给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
//区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。

// 思路：通过前缀和将子数组和的问题转换为某个具体元素值大小的问题(注意越界)
// 解法1：归并排序，通过比较两个有序数组的大小 将前缀和数组 lower <= nums[r] - nums[l] <= upper的问题 转化为 nums[r] - upper <= nums[l] <= nums[r] - lower的问题，并利用有序性，减少比较次数
public class _0327_CountOfRangeSum {
    public static void main(String[] args) {
        int[] nums = new int[]{2147483647,-2147483648,-1,0};
        System.out.println(countRangeSum(nums, -1, 0));
    }

    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums.length == 0) {
            return 0;
        }
        // 构造前缀和
        long[] sums = new long[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = nums[i] + sums[i - 1];
        }
        return mergeSort(sums, 0, sums.length - 1, lower, upper);
    }

    public static int mergeSort(long[]nums, int l, int r, int lower, int upper) {
        if (l == r) {
            return nums[l] >= lower && nums[l] <= upper ? 1 : 0;
        }

        int mid = l + ((r - l) >> 1);
        int leftResult = mergeSort(nums, l, mid, lower, upper);
        int rightResult = mergeSort(nums, mid + 1, r, lower, upper);
        int mergeResult = merge(nums, l, mid, r, lower, upper);
        return leftResult + rightResult + mergeResult;
    }

    public static int merge(long[]nums, int l, int mid, int r, int lower, int upper) {
        int result = 0;
        // 利用两边有序，找到满足nums[r] - upper <= nums[l] <= nums[r] - lower的个数
        int l1 = l; // 左窗口
        int l2 = l; // 右窗口
        int p2 = mid + 1;
        while (p2 <= r) {
            long min = nums[p2] - upper;
            long max = nums[p2] - lower;
            while (l1 <= mid && nums[l1] < min){
                l1++;
            }
            while (l2 <= mid && nums[l2] <= max) {
                l2++;
            }
            result += l2 - l1;
            p2++;
        }

        // 合并
        int p1 = l;
        p2 = mid + 1;
        long[] helper = new long[r - l + 1];
        int i = 0;

        while (p1 <= mid && p2 <= r) {
            if (nums[p1] <= nums[p2]) {
                helper[i++] = nums[p1++];
            } else {
                helper[i++] = nums[p2++];
            }
        }

        while (p1 <= mid) {
            helper[i++] = nums[p1++];
        }

        while (p2 <= r) {
            helper[i++] = nums[p2++];
        }

        i = 0;
        while (l <= r) {
            nums[l++] = helper[i++];
        }

        return result;
    }
}
