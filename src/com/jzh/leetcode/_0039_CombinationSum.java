package com.jzh.leetcode;

import java.util.*;

/**
 * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，找出candidates中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为target 的不同组合数少于 150 个。
 *
 * 思路：回溯法，不断尝试
 */
public class _0039_CombinationSum {

    public static void main(String[] args) {
        int[] candidates = new int[]{2,3,6,7};
        int target = 7;
//        List<List<Integer>> reuslt = combinationSum(candidates, target);
        System.out.println(combinationSum(candidates, target));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (target == 0 || candidates == null || candidates.length == 0) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        process(candidates, result, new LinkedList<>(), 0, target);
        return result;
    }

    public static void process(int[] candidates, List<List<Integer>> result, LinkedList<Integer> list, int index, int rest) {
        if (rest == 0) {
            result.add(new ArrayList<>(list));
            return;
        } else if (index == candidates.length) {
            return;
        }

        for (int j = 0; candidates[index] * j <= rest; j++) {
            for (int k = 0; k < j; k++) {
                list.add(candidates[index]);
            }
            process(candidates, result, list, index + 1, rest - candidates[index] * j);
            for (int k = 0; k < j; k++) {
                list.removeLast();
            }
        }
    }

}
