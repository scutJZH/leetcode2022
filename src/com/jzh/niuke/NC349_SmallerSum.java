package com.jzh.niuke;

import java.util.Arrays;
import java.util.List;

// 数组小和的定义如下：
// 在一个数组中，一个数左边比它小于或者等于的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和
// 求数组的小和

// 思路：利用归并排序，每个元素都会和其它元素进行比较，可以在比较时，顺便对某些特征进行统计或计算。
// 并且在统计或计算时，由于两边已经有序，所以在merge时，可以更简便的进行统计，减少重复遍历导致统计和计算更加复杂
public class NC349_SmallerSum {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{41, -4, 57, 11, -7, -10, 6, 66, 18, 21, 35, 65, 18, 68, 59, 0, 27, -13, 55, 83, 12, 0, -11, 21, 44, -32, 56, -2, 1, 32, 52, 55, -9, 7, 51, 8, 67, 49, -15, -15, -12, -22, 80, -46, -4, -32, 90, 0, 47, 32, 46, 9, -25, 27, 77, 14, -1, 66, 53, 39, 38, 35, 36, 14, -12, 36, 39, 61, 85, 50, 4, 27, -34, -22, 31, 44, 1, 37, 61, 22, 52, 52, -37, 70, 1, 15, -19, 21, 80, 69, 29, 48, -19, 48, -27, 9, 25, 62, 3, 12, 29, 29, 66, 57, -2, 62, -5, 38, -27, 79, -29, 27, 37, 74, -4, -31, 57, -19, -1, 38, 60, 38, 22, 47, 32, 58, 0, 18, 31, 45, 77, -36, -7, 19, 35, 2, 29, 59, 81, 7, -2, -7, 37, 45, 64, 10, 73, 33, 15, 25, 2, 45, 4, -34, 7, 27, -3, 53, 50, -10, 20, 64, -25, 7, 42, -10, 28, 61, 8, 44, 30, 44, -6, -18, 72, -8, 63, 0, -17, 67, 10, 68, 4, -43, 59, -7, 9, 57, -4, 97, 71, 24, 71, 9, 17, 31, 50, -6, 45, 50, 59, 44, 33, 16, -13, 33, -19, -3, 78, 74, 70, 23, 17, 44, 35, 56, 19, 20, 20, 13, 49, 61, -3, 17, 42, 9, 49, 26, 50, 15, 52, 82, 64, 72, 66, 19, -41, 60, 62, 78, 71, 41, 6, 47, 36, 24, -32, 70};
        List<Integer> list = Arrays.asList(arr);
        System.out.println(calArray(list));
    }

    public static long calArray (List<Integer> nums) {
        Integer[] arr = nums.toArray(new Integer[0]);
        return mergeSort(arr, 0, arr.length - 1);
    }

    public static long mergeSort(Integer[] nums, int l, int r) {
        if (l == r) {
            return 0;
        }

        int mid = l + ((r - l) >> 1);
        long leftSum = mergeSort(nums, l, mid);
        long rightSum = mergeSort(nums, mid + 1, r);
        long mergeSum = merge(nums, l, mid, r);
        return leftSum + rightSum + mergeSum;
    }

    public static long merge(Integer[] nums, int l, int mid, int r) {
        int p1 = l;
        int p2 = mid + 1;
        int[] helper = new int[r - l + 1];
        long sum = 0;

        int i = 0;
        while (p1 <= mid && p2 <= r) {
            if (nums[p1] <= nums[p2]) {
                // 当左边比右边某个数小时，则他比右边所有数都小（右边有序），省去了和右边所有数比较的必要（利用归并排序特点）
                sum += (long)nums[p1] * (r - p2 + 1);
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

        for (i = 0; i < helper.length; i++) {
            nums[l++] = helper[i];
        }

        return sum;
    }
}
