package com.jzh.leetcode;

// 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
// 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
// 必须在不使用库的sort函数的情况下解决这个问题。

// 思路：由于不能使用辅助数组，所以用快排切分思路，三个指针 l、p、r，[0, l) 表示小于target，[l, r) 表示等于target、(r, nums.length - 1] 表示大于target
// 注意：swap函数入参i == j的情况
public class _0075_SortColors {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 2};
        sortColors(nums);
        System.out.println(111);
    }

    public static void sortColors(int[] nums) {
        partition(nums, 0, nums.length - 1, 1);
    }

    public static void partition(int[] nums, int l, int r, int target) {
        if (l == r) {
            return;
        }
        int p = l;
        int p1 = l; // [l, p1) 上有 nums[i] < target
        int p2 = r; // (p2, r] 上有 nums[i] > target
        while (p <= p2) {
            if (nums[p] < target) {
                swap(nums, p1++, p++);
            } else if (nums[p] == target) {
                p++;
            } else {
                swap(nums, p, p2--);
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
