package com.jzh.leetcode;

import java.util.*;

/**
 * 给定一个候选人编号的集合candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的每个数字在每个组合中只能使用一次。
 * 注意：解集不能包含重复的组合。
 * 1 <=candidates.length <= 100
 * 1 <=candidates[i] <= 50
 * 1 <= target <= 30
 *
 * 思路：回溯法，
 *  优化：① 由于1 <=candidates[i] <= 50，则可以用51位的数组统计词频。②由于i从小到大遍历，比较rest和i的值可以进行剪枝
 */
public class _0040_CombinationSumII {

    public static void main(String[] args) {
        int[] nums = new int[]{10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        System.out.println(combinationSumII(nums, target));
    }

    public static List<List<Integer>> combinationSumII(int[] candidates, int target) {
        if (target == 0 || candidates == null || candidates.length == 0) {
            return Collections.emptyList();
        }

        int[] count = new int[51];
        for (int num : candidates) {
            count[num]++;
        }

        List<List<Integer>> result = new ArrayList<>();
        process(count, result, new LinkedList<>(), 0, target);
        return result;
    }

    public static void process(int[] count, List<List<Integer>> result, LinkedList<Integer> list, int index, int rest) {
        if (rest == 0) {
            result.add(new ArrayList<>(list));
            return;
        } else if (index == count.length) {
            return;
        } else if (rest < index) {
            // 剪枝
            return;
        }

        int maxNum = count[index];
        for (int i = 0; i <= maxNum && index * i <= rest; i++) {
            for (int j = 0; j < i; j++) {
                list.add(index);
            }
            process(count, result, list, index + 1, rest - index * i);
            for (int j = 0; j < i; j++) {
                list.removeLast();
            }
        }
    }
}
