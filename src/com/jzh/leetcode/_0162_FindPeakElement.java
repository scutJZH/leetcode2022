package com.jzh.leetcode;

// 峰值元素是指其值严格大于左右相邻值的元素。
//给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
//你可以假设 nums[-1] = nums[n] = -∞ 。
//你必须实现时间复杂度为 O(log n) 的算法来解决此问题。

// 思路：二分法，由于只需要找到任意一个，且复杂度为O(log n)，则可以使用二分法
// 先检查端点，若左边 ↑， 右边 ↓，则峰值肯定在中间某处位置
// 若在mid处为 ↑，则峰值肯定在(mid, R)，若mid处为 ↓，则峰值肯定在(L, mid)
public class _0162_FindPeakElement {
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,1,3,5,6,4};
        System.out.println(findPeakElement(nums));
    }

    public static int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        // 检查端点
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }

        // 二分法找转折点
        int l = 1;
        int r = nums.length - 2;
        int mid = 0;
        while (l < r) {
            mid = (l + r) / 2;
            if (nums[mid] < nums[mid + 1]) {
                l = mid + 1;
            } else if (nums[mid] < nums[mid - 1]) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return l;
    }
}
