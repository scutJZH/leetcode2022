package com.jzh.leetcode;

/**
 * 给你一个整数数组 prices ，其中prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候最多只能持有一股股票。你也可以先购买，然后在同一天出售。
 * 返回 你能获得的 最大 利润。
 *
 * 思路：
 *  解1：贪心算法，假设每天都买，当天或者第二天卖掉，则有：
 *      如果第二天价格更高，则第二天卖掉，否则当天卖掉
 */
public class _0122_BestTimeToBuyAndSellStockII {

    public static void main(String[] args) {
        int[] prices = new int[]{1,2,3,4,5};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            // 第i-1天能赚就买，然后第二天卖掉。否则就当天卖掉，收入是0
            profit += Math.max(0, prices[i] - prices[i - 1]);
        }

        return profit;
    }


}
