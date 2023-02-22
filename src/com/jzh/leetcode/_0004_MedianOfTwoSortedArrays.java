package com.jzh.leetcode;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * 思路：
 *  看到log(m+n)则想到二分，这道题其实可以转化为寻找第k小的数，每次可以在两个数组中比较前k/2个数，如果a1[start + k/2]< a2[start + k/2]，则a1[start + k/2]和它之前的数肯定比第k个数小，则可以去掉这些数，重新在新的数组上找k-k/2小的数
 *  可通过递归的方式，不断找a2[i~end] a2[j~end]上第k小的数，注意数组越界问题和第k小与下标对应的问题
 */

// todo
public class _0004_MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        if ((l1 + l2) % 2 == 0) {
            int left = getKMinNumber(nums1, nums2, 0, 0, (l1 + l2) / 2);
            int right = getKMinNumber(nums1, nums2, 0, 0, (l1 + l2) / 2 + 1);
            return (left + right) * 0.5d;
        } else {
            return getKMinNumber(nums1, nums2, 0, 0, (l1 + l2 + 1) / 2);
        }
    }

    /**
     * 返回num1[start1, end1]和nums2[start2, end2]中第k小的数
     */
    public static int getKMinNumber(int[] nums1, int[] nums2, int start1, int start2, int k) {
        if (start1 == nums1.length) {
            return nums2[start2 + k - 1];
        }
        if (start2 == nums2.length) {
            return nums1[start1 + k - 1];
        }

        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int m1 = Math.min(k / 2, nums1.length - start1);
        int m2 = Math.min(k / 2, nums2.length - start2);

        if (nums1[start1 + m1 - 1] < nums2[start2 + m2 - 1]) {
            return getKMinNumber(nums1, nums2, start1 + m1, start2, k - m1);
        } else {
            return getKMinNumber(nums1, nums2, start1, start2 + m2, k - m2);
        }
    }

}
