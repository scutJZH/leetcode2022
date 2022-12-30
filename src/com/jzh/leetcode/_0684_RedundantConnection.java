package com.jzh.leetcode;

/**
 * 树可以看成是一个连通且 无环的无向图。
 * 给定往一棵n 个节点 (节点值1～n) 的树中添加一条边后的图。添加的边的两个顶点包含在 1 到 n中间，且这条附加的边不属于树中已存在的边。图的信息记录于长度为 n 的二维数组 edges，edges[i] = [ai, bi]表示图中在 ai 和 bi 之间存在一条边。
 * 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组edges中最后出现的边。
 * <p>
 * 思路：kruskal算法（由于只返回一个，故找到后可以直接return）
 */
public class _0684_RedundantConnection {

    public static void main(String[] args) {
        int[][] edges = new int[][]{{1, 2}, {1, 3}, {2, 3}};
        int[] edge = findRedundantConnection(edges);
        for (int i : edge) {
            System.out.print(i + ",");
        }
    }

    public static int[] findRedundantConnection(int[][] edges) {
        if (edges == null || edges.length == 0) {
            return null;
        }

        // 假设有n个节点，如果是最小生成树，应该是n-1个边
        // 此时多了一条边，则n条边对应了n个节点。由于是从1开始，故需初始化并查集大小为n+1
        UnionFind unionFind = new UnionFind(edges.length + 1);
        for (int[] edge : edges) {
            // 如果在同一个集合中，说明已经有路径到达这两个点了
            if (unionFind.isSameSet(edge[0], edge[1])) {
                return new int[]{edge[0], edge[1]};
            } else {
                unionFind.union(edge[0], edge[1]);
            }
        }

        return null;
    }

    public static class UnionFind {
        private int[] parents;
        private int[] helper;

        public UnionFind(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }

            helper = new int[n];
        }

        public int findParent(int i) {
            int helpIndex = 0;
            while (i != parents[i]) {
                helper[helpIndex++] = i;
                i = parents[i];
            }

            // 降低层数
            while (helpIndex > 0) {
                parents[helper[--helpIndex]] = i;
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

            parents[jParent] = iParent;
        }
    }
}
