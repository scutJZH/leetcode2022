package com.jzh.leetcode;

import java.util.*;

/**
 * 有 n 个网络节点，标记为1到 n。
 * 给你一个列表times，表示信号经过 有向 边的传递时间。times[i] = (ui, vi, wi)，其中ui是源节点，vi是目标节点， wi是一个信号从源节点传递到目标节点的时间。
 * 现在，从某个节点K发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回-1 。
 *
 * 思路：dijkstra算法，用result[i-1] 表示源节点到编号为i的节点的最短距离（-1代表无穷），confirmed[i-1]代表是否这个节点的距离已经确定，如果edges[i-1][j-1]>=0，则代表编号为i的节点到j的节点存在一条边
 */
public class _0743_NetworkDelayTime {

    public static void main(String[] args) {
        int[][] times = new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        System.out.println(networkDelayTime(times, 4, 2));
    }


    public static int networkDelayTime(int[][] times, int n, int k) {
        if (times == null || times.length == 0) {
            return -1;
        }

        // 保存每个节点出发的边，edges[i][j]=-1代表i没有直接到j的边
        int[][] edges = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                edges[i][j] = -1;
            }
        }
        for (int[] edge : times) {
            edges[edge[0] - 1][edge[1] - 1] = edge[2];
        }

        // -1代表无法到达
        int[] result = new int[n];
        Arrays.fill(result, -1);

        // 初始化
        result[k - 1] = 0;

        // 保存已经确定了的节点
        boolean[] confirmed = new boolean[n];

        int minIndex = getMinIndex(result, confirmed);
        while (minIndex >= 0) {
            for (int i = 0; i < n; i++) {
                if (confirmed[i] || edges[minIndex][i] < 0) {
                    continue;
                }

                int d = edges[minIndex][i];

                if (result[i] < 0) {
                    result[i] = d + result[minIndex];
                } else {
                    result[i] = Math.min(result[i], result[minIndex] + d);
                }
            }

            confirmed[minIndex] = true;
            minIndex = getMinIndex(result, confirmed);
        }

        int max = 0;
        for (int i : result) {
            if (i == -1) {
                return -1;
            }
            max = Math.max(i, max);
        }

        return max;
    }

    public static int getMinIndex(int[] result, boolean[] confirmed) {
        int index = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < result.length; i++) {
            if (result[i] >= 0 && !confirmed[i] && result[i] < min) {
                min = result[i];
                index = i;
            }
        }

        return index;
    }
}
