package com.jzh.leetcode;

/**
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 * <p>
 *
 * 思路：
 * 解1：使用并查集(由于连接有对称性，故遍历一半即可)
 */
public class _0547_NumberOfProvinces {

    public static void main(String[] args) {
        int[][] isConnected = new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println(findCircleNum(isConnected));
    }

    public static int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }

        UnionFind unionFind = new UnionFind(isConnected.length);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i + 1; j < isConnected[i].length; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }

        return unionFind.size();
    }

    public static class UnionFind {
        // 第n个城市的父节点在parents[n]处
        private int[] parents;
        private int[] unionNumbers; // i作为最初父节点时的集合的元素数量

        private int[] helper; // 辅助数组，用于减少层高
        private int size;

        public UnionFind(int n) {
            // 将每个元素单独置为一个集合
            parents = new int[n];
            unionNumbers = new int[n];
            helper = new int[n];
            size = n;

            for (int i = 0; i < n; i++) {
                parents[i] = i;
                unionNumbers[i] = 1;
            }
        }

        // 找到最开始的父节点
        private int findParent(int i) {
            int index = 0;
            while (parents[i] != i) {
                i = parents[i];
                index++;
            }

            // 将路径上遇到的所有节点的父节点都改为i
            while (index > 0) {
                parents[helper[--index]] = i;
            }

            return i;
        }

        public void union(int i, int j) {
            int iParent = findParent(i);
            int jParent = findParent(j);
            // 本来就是同一个省份，就不必进行合并
            if (iParent == jParent) {
                return;
            }

            // 不是同一个省份时，小的合到大的
            int iSize = unionNumbers[iParent];
            int jSize = unionNumbers[jParent];

            if (i >= j) {
                parents[jParent] = iParent;
                unionNumbers[iParent] = iSize + jSize;
            } else {
                parents[iParent] = jParent;
                unionNumbers[jParent] = iSize + jSize;
            }

            size--;
        }

        public int size() {
            return size;
        }
    }
}
