package com.jzh.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。 由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
 * 给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。
 * 最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
 * 总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。
 * <p>
 * 答案保证在 32 位有符号整数范围内。
 * <p>
 * 思路：贪心算法，将启动资金按从小到大排序，每次将启动资金小于本金的全部项目出列，放到利润的大根堆中，然后选取最大的利润的项目
 */
public class _0502_IPO {

    public static void main(String[] args) {
        int[] profits = new int[]{1, 2, 3};
        int[] capital = new int[]{0, 1, 1};

        System.out.println(findMaximizedCapital(2, 0, profits, capital));
    }

    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // 将资本和capital组合到一起并按启动资金从小到大排序
        PriorityQueue<int[]> profitAndCapitals = new PriorityQueue<>(Comparator.comparingInt(o -> o[0])); // 小根堆
        PriorityQueue<Integer> capitals = new PriorityQueue<>((o1, o2) -> o2 - o1); // 纯利大根堆

        for (int i = 0; i < profits.length; i++) {
            // 优化点：低于w启动资金的直接加入纯利润大根堆
            if (capital[i] <= w) {
                capitals.add(profits[i]);
            } else {
                profitAndCapitals.add(new int[]{capital[i], profits[i]});
            }
        }

        int currentMoney = w;
        for (int i = 0; i < k; i++) {
            while (!profitAndCapitals.isEmpty() && profitAndCapitals.peek()[0] <= currentMoney) {
                capitals.add(profitAndCapitals.poll()[1]);
            }

            if (capitals.isEmpty()) {
                // 启动资金不足以启动任何项目，直接退出循环
                return currentMoney;
            }

            currentMoney += capitals.poll();
        }

        return currentMoney;
    }
}
