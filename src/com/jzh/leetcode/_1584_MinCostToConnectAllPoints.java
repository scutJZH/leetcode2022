package com.jzh.leetcode;

import java.util.*;

/**
 * 给你一个points数组，表示 2D 平面上的一些点，其中points[i] = [xi, yi]。
 * 连接点[xi, yi] 和点[xj, yj]的费用为它们之间的 曼哈顿距离：|xi - xj| + |yi - yj|，其中|val|表示val的绝对值。
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有一条简单路径时，才认为所有点都已连接。
 * <p>
 * 思路：通过点构造所有边，使用Kruskal算法。Kruskal算法需要用到并查集来检查边是否会形成环形（判断两个点是否在同一个集合中）
 */
public class _1584_MinCostToConnectAllPoints {

    public static void main(String[] args) {
        int[][] points = new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        System.out.println(minCostConnectPoints(points));
    }

    public static int minCostConnectPoints(int[][] points) {
        if (points == null || points.length < 2) {
            return 0;
        }

        // 生成所有边并加入优先级队列
        PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                edges.add(new Edge(i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])));
            }
        }

        int sum = 0;
        UnionFind unionFind = new UnionFind(points.length);
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            if (!unionFind.isSameSet(edge.i, edge.j)) {
                sum += edge.value;
                unionFind.union(edge.i, edge.j);
            }
        }

        return sum;
    }

    public static class Edge {
        int i;
        int j;
        int value;

        public Edge(int i, int j, int value) {
            this.i = i;
            this.j = j;
            this.value = value;
        }
    }

    public static class UnionFind {
        private int[] parents;
        private int[] numbers;
        private int[] helper;

        public UnionFind(int n) {
            parents = new int[n];
            numbers = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                numbers[i] = 1;
            }
            helper = new int[n];
        }

        public int findParent(int i) {
            int helperIndex = 0;

            while (parents[i] != i) {
                helper[helperIndex++] = i;
                i = parents[i];
            }

            // 降层
            while (helperIndex > 0) {
                parents[helper[--helperIndex]] = i;
            }

            return i;
        }

        public boolean isSameSet(int i, int j) {
            return findParent(i) == findParent(j);
        }

        public void union(int i, int j) {
            int iParent = findParent(i);
            int jParent = findParent(j);

            if (iParent == jParent) {
                return;
            }

            // 小的合到大的上面
            int iNumber = numbers[i];
            int jNumber = numbers[j];

            if (iNumber >= jNumber) {
                parents[jParent] = iParent;
                numbers[iNumber] += jNumber;
            } else {
                parents[iParent] = jParent;
                numbers[jNumber] += iNumber;
            }
        }
    }
}
