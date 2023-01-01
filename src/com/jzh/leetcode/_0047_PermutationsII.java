package com.jzh.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 * <p>
 * 思路：递归，用index标记，在index之前已经排好序，交换index和之后位置的数字来尝试第index位放置的数字
 *  如何找到不重复：当第index位置填充数字时，通过check[nums[i] + 10]来标记这个位置是否填过nums[i]了，为什么要+10，因为-10 <= nums[i] <= 10，故check数组长度取21即可
 */
public class _0047_PermutationsII {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2, 3};
        List<List<Integer>> result = permuteUnique(nums);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        process(nums, 0, result);
        return result;
    }

    public static void process(int[] nums, int index, List<List<Integer>> result) {
        if (index == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (int n : nums) {
                list.add(n);
            }
            result.add(list);
            return;
        }

        boolean[] check = new boolean[21];
        for (int i = index; i < nums.length; i++) {
            // 剪枝
            if (check[nums[i] + 10]) {
                continue;
            }
            swap(nums, index, i);
            process(nums, index + 1, result);
            swap(nums, index, i);
            check[nums[i] + 10] = true;
        }
    }

    public static void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }

        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }
}
