package com.jzh.algorithm;


// 整数数组 nums 按升序排列，数组中的值互不相同 。
//
// 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
// 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
// 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题

// 思路：O(log n)为二分法，分别讨论target[L]、target[middle]、target[R]的大小关系
// nums[L] < nums[middle] < nums[R] : L - R 均未旋转
// nums[L] > nums[middle] : 旋转部分在 L - middle
// nums[middle] > nums[R] : 旋转部分在 middle - R
public class _0033_SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target) {
        return search(nums, target, 0, nums.length - 1);
    }

    public static int search(int[] nums, int target, int l, int r) {
        if (l == r) {
            return nums[l] == target ? l : -1;
        }

        int middle = (l + r) >> 1;
        if (target == nums[middle]) {
            return middle;
        }

        if (target == nums[l]) {
            return l;
        }

        if (target == nums[r]) {
            return r;
        }

        // 旋转点在右边时：左边有序，当nums[L] < target < nums[middle]时，才找左边，其余找右边即可
        if (nums[middle] > nums[r]) {
            if (nums[l] < target && target < nums[middle]) {
                return search(nums, target, l, middle);
            } else {
                return search(nums, target, middle + 1, r);
            }
            // 同理，旋转点在左边时，右边有序，则当nums[middle] < target < nums[R]时才找右边，其余找左边即可
        } else {
            if (nums[middle] < target && target < nums[r]) {
                return search(nums, target, middle + 1, r);
            } else {
                return search(nums, target, l, middle);            }
        }
    }
}
