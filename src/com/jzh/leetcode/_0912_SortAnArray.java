package com.jzh.leetcode;

// 给你一个整数数组 nums，请你将该数组升序排列。

import java.util.Arrays;
import java.util.Random;

// 解1：普通随机快排
// 解2：三向切分随机快排
public class _0912_SortAnArray {

    public static void main(String[] args) {
        int[] nums = new int[1000];
        int[] nums2 = new int[1000];
        int[] nums3 = new int[1000];
        for (int k = 0; k < 10000; k++) {
            for (int i = 0; i < 1000; i++) {
                int n = new Random().nextInt(1500);
                nums[i] = n;
                nums2[i] = n;
                nums3[i] = n;
            }
            sortArray1(nums);
            sortArray2(nums3);
            Arrays.sort(nums2);
            for (int i = 0; i < 1000; i++) {
                if (nums[i] != nums2[i] || nums[i] != nums3[i]) {
                    System.out.println("出错了！");
                    return;
                }
            }
        }
        System.out.println("成功！");
    }

    public static int[] sortArray1(int[] nums) {
        quicklySort1(nums, 0, nums.length - 1);
        return nums;
    }

    public static void quicklySort1(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        // 随机
        swap(nums, l + new Random().nextInt(r - l + 1), l);
        int p = partition1(nums, l, r);
        quicklySort1(nums, l, p - 1);
        quicklySort1(nums, p + 1, r);
    }

    // 取l作为target
    public static int partition1(int[] nums, int l, int r) {
        int p = l + 1;
        int target = nums[l];
        for (int i = l + 1; i <= r; i++) {
            if (nums[i] <= target) {
                swap(nums, p++, i);
            }
        }
        // 将target摆放到正确位置
        swap(nums, l, p - 1);
        return p - 1;
    }

    public static int[] sortArray2(int[] nums) {
        quicklySort2(nums, 0, nums.length - 1);
        return nums;
    }

    public static void quicklySort2(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        // 随机
        swap(nums, l + new Random().nextInt(r - l + 1), l);
        int[] p = partition2(nums, l, r);
        quicklySort2(nums, l, p[0] - 1);
        quicklySort2(nums, p[1] + 1, r);
    }

    // 取l作为target
    public static int[] partition2(int[] nums, int l, int r) {
        // [l, p1) [p1, p2] (p2, r]
        int p1 = l + 1;
        int p2 = r;
        int target = nums[l];
        for (int i = l + 1; i <= p2;) {
            if (nums[i] < target) {
                swap(nums, p1++, i++);
            } else if (nums[i] == target) {
                i++;
            } else {
                swap(nums, p2--, i);
            }
        }
        // 将target摆放到正确位置
        swap(nums, l, p1 - 1);
        return new int[]{p1 - 1, p2};
    }


    public static void swap(int[]nums, int i, int j) {
        if (i == j) {
            return;
        }
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
