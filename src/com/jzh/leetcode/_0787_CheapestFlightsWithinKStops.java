package com.jzh.leetcode;

import java.util.Arrays;

/**
 * 有 n 个城市通过一些航班连接。给你一个数组flights ，其中flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。
 *
 * 思路：通过bellman-ford算法来找不超过K个边的最短路径
 */
public class _0787_CheapestFlightsWithinKStops {

    public static void main(String[] args) {
        int[][] flights = new int[][]{{0, 1, 1}, {0, 2, 5}, {1, 2, 1}, {2, 3, 1}};
        System.out.println(findCheapestPrice(4, flights, 0, 3, 1));
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];
        int INF = 0x3f3f3f3f;
        Arrays.fill(dist, INF);

        dist[src] = 0;

        // k个中转站则是k+1个边
        for (int i = 0; i < k + 1; i++) {
            int[] clone = dist.clone(); // 一次迭代只能更新一次，如果不clone，则可能在更新了A点的基础上，通过A点去更新了B点，则更新了两次
            for (int[] flight : flights) {
                if (dist[flight[0]] + flight[2] < clone[flight[1]]) {
                    dist[flight[1]] = Math.min(dist[flight[1]], clone[flight[0]] + flight[2]);
                }
            }
        }

        return dist[dst] > INF / 2 ? -1 : dist[dst];
    }

}
