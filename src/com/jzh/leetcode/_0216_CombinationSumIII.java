package com.jzh.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 找出所有相加之和为n 的k个数的组合，且满足下列条件：
 *     只使用数字1到9
 *     每个数字最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 *
 * 思路：回溯法，加或不加
 */
public class _0216_CombinationSumIII {

    public static void main(String[] args) {
        int k = 3, n = 7;
        System.out.println(combinationSum3(k, n));
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        if (k == 0 || n == 0) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        process(1, k, n, new ArrayList<>(), result);
        return result;
    }

    public static void process(int index, int restK, int restN, List<Integer> current, List<List<Integer>> result) {
        if (restN == 0 && restK == 0) {
            result.add(new ArrayList<>(current));
            return;
        } else if (restN == 0 || index == 10 || restK == 0 || index > restN) {
            return;
        }

        // 不加
        process(index + 1, restK, restN, current, result);
        current.add(index);
        process(index + 1, restK - 1, restN - index, current, result);
        current.remove(current.size() - 1);
    }


}
