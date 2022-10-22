package com.jzh.leetcode;

import com.jzh.util.Utils;

// 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
// 请注意 ，必须在不复制数组的情况下原地对数组进行操作。

// 思路：遇到非0直接往前填，之后全部置为0
public class _0283_MoveZeroes {

    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        moveZeroes(nums);
        Utils.showNums(nums);
    }

    public static void moveZeroes(int[] nums) {
       int n = 0;
       for (int num : nums) {
           if (num != 0) {
               nums[n] = num;
               n++;
           }
       }

       for (int i = n; i < nums.length; i++) {
           nums[i] = 0;
       }
    }

}
