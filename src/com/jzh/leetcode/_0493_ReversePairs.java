package com.jzh.leetcode;

// 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
//
// 你需要返回给定数组中的重要翻转对的数量。

// 思路：利用归并排序，每个元素都会和其它元素比较一次，且当是两个有序数组进行比较时，可以省去后序比较的时间
// 考虑 * 2 越界的问题
public class _0493_ReversePairs {
    public static void main(String[] args) {
        int[] arr = new int[]{2,4,3,5,1};
        int result = reversePairs(arr);
        System.out.println(result);
    }

    public static int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    public static int mergeSort(int[] nums, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        int leftResult = mergeSort(nums, l, mid);
        int rightResult = mergeSort(nums, mid + 1, r);
        return leftResult + rightResult + merge(nums, l, mid, r);
    }

    public static int merge(int[] nums, int l, int mid, int r) {
        int p1 = l;
        int p2 = mid +1;

        int result = 0;
        // 负数*2导致可能有 nums[p1] < nums[p2] 但是 nums[p1] > 2 * nums[p2]
        while (p1 <= mid && p2 <= r) {
            // 当nums[p1] > 2 * nums[p2]时，则p1-mid上都大于nums[p2]
            if (nums[p1] > 2 * (long)nums[p2]) {
                result += mid - p1 + 1;
                p2++;
            } else {
                p1++;
            }
        }
        // 合并数组
        p1 = l;
        p2 = mid + 1;
        int[] helper = new int[r - l + 1];
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
