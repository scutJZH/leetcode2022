package com.jzh.leetcode;

/**
 * 在本问题中，有根树指满足以下条件的 有向 图。该树只有一个根节点，所有其他节点都是该根节点的后继。该树除了根节点之外的每一个节点都有且只有一个父节点，而根节点没有父节点。
 * 输入一个有向图，该图由一个有着 n 个节点（节点值不重复，从 1 到 n）的树及一条附加的有向边构成。附加的边包含在 1 到 n 中的两个不同顶点间，这条附加的边不属于树中已存在的边。
 * 结果图是一个以边组成的二维数组edges 。 每个元素是一对 [ui, vi]，用以表示 有向 图中连接顶点 ui 和顶点 vi 的边，其中 ui 是 vi 的一个父节点。
 * 返回一条能删除的边，使得剩下的图是有 n 个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
 * <p>
 * 思路：看到给边且需要判断多余边，则直接考虑kruskal算法
 *  分析：
 *      因为要满足【只有一个根节点】，如果直接用并查集，会导致生成的结果中有多个根节点。故只能用kruskal算法来判断是否有环。
 *      因为会多一条边，则可能会有两种情况：
 *          1. 这条边指向根节点，则每个节点都会有父节点，则这个图中一定有环路。去掉形成环路的最后一个边即可。
 *          2. 这条边不指向根节点，则其中一个节点会有两个父节点（两条冲突边），此时图中可能有环路也可能没有环路。通过把第二条冲突边不加入到并查集的方式，如果还形成环路，则说明是第一条冲突边，否则是第二条冲突边
 */
public class _0685_RedundantConnectionII {

    public static void main(String[] args) {
        int[][] edges = new int[][]{{2, 1}, {3, 1}, {4, 2}, {1, 4}};
        int[] edge = findRedundantDirectedConnection(edges);
        System.out.println(edge[0] + "," + edge[1]);
    }

    public static int[] findRedundantDirectedConnection(int[][] edges) {
        if (edges == null || edges.length == 0) {
            return null;
        }

        // 节点从1开始编号，n条边应该对应n+1个节点，但因为多了一条节点，故节点编号为1 ~ n
        // 用于找环形节点
        UnionFind unionFind = new UnionFind(edges.length + 1);
        // 用于找出冲突边
        int[] parent = new int[edges.length + 1];
        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }

        int conflict = -1;
        int cycle = -1;
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            // 先找冲突边
            if (parent[edge[1]] != edge[1]) {
                // 说明已经有过父亲了，记录冲突边，不记录到并查集中
                conflict = i;
            } else {
                // 记录父节点
                parent[edge[1]] = edge[0];
                // 找到导致环产生的边
                if (unionFind.isSameSet(edge[0], edge[1])) {
                    cycle = i;
                } else {
                    unionFind.union(edge[0], edge[1]);
                }
            }
        }

        if (conflict < 0) {
            // 如果没有冲突的边，则说明每个节点都有一个父亲，则一定形成环，去掉最后一个形成环的边
            return edges[cycle];
        } else {
            if (cycle > 0) {
                // 如果有冲突的边，也造成环，由于冲突的边未记入并查集，故冲突的边是前一个边
                return new int[]{parent[edges[conflict][1]], edges[conflict][1]};
            } else {
                // 如果只有冲突边，但是没有环，说明这个冲突边就是多余的边
                return edges[conflict];
            }
        }
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

        public int find(int i) {
            int helpIndex = 0;

            while (i != parents[i]) {
                helper[helpIndex++] = i;
                i = parents[i];
            }

            while (helpIndex > 0) {
                parents[helper[--helpIndex]] = i;
            }

            return i;
        }

        public boolean isSameSet(int i, int j) {
            return find(i) == find(j);
        }

        public void union(int i, int j) {
            int iParent = find(i);
            int jParent = find(j);

            if (iParent == jParent) {
                return;
            }

            parents[jParent] = iParent;
        }
    }


}
