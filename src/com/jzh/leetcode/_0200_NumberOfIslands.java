package com.jzh.leetcode;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 思路：
 *  解1：感染法，遍历节点时，如果是陆地，则使用DFS或BFS找到相邻的所有陆地，并将它们进行标记，全部标记成功后岛屿数量加1。如果碰到的节点是被标记过的或是水，则直接跳过。
 *  解2：使用并查集，遍历节点，如果节点是陆地，则将其加入并查集中（已存在就不加入），并检查它的右边和下边的节点看是否能够与当前节点进行合并。最终返回并查集中的集合数量。
 */

public class _0200_NumberOfIslands {

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1'}, {'1'}
        };

        System.out.println(numIslands2(grid));
    }

    public static int numIslands(char[][] grid) {
        int num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                num += dfs(grid, i, j);
            }
        }
        return num;
    }

    // 广度搜素，对连起来的陆地进行标记(2)
    public static int dfs(char[][] grid, int x, int y) {
        if (x >= grid.length || x < 0 || y >= grid[x].length || y < 0) {
            return 0;
        }
        // 海洋和已标记的陆地不再进行搜索
        if (grid[x][y] == '0' || grid[x][y] == '2') {
            return 0;
        }

        // 置为已标记
        grid[x][y] = '2';

        dfs(grid, x - 1, y);
        dfs(grid, x + 1, y);
        dfs(grid, x, y - 1);
        dfs(grid, x, y + 1);

        return 1;
    }

    public static int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        UnionFind unionFind = new UnionFind(grid);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    // 只需要找右边节点和下边节点
                    if (i + 1 < grid.length && grid[i + 1][j] == '1') {
                        unionFind.union(i, j, i + 1, j);
                    }
                    if (j + 1 < grid[i].length && grid[i][j + 1] == '1') {
                        unionFind.union(i, j, i, j + 1);
                    }
                }
            }
        }

        return unionFind.size;
    }

    public static class UnionFind {
        // 记录数组大小
        private int m;
        private int n;
        private int[] parents; // 记录直接父节点
        private int[] numbers; // 记录以x节点为祖先时的整个集合中的元素数量

        private int[] helper; // 辅助数组，用于减少层高
        private int size;

        public UnionFind(char[][] grid) {
            m = grid.length;
            n = grid[0].length;
            parents = new int[m * n];
            numbers = new int[m * n];
            helper = new int[m * n];
            for (int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        int index = i * n + j;
                        parents[index] = index;
                        numbers[index] = 1;
                        size++;
                    }
                }
            }
        }

        public int find(int i, int j) {
            int helpIndex = 0;

            int index = i * n + j;
            while (parents[index] != index) {
                helper[helpIndex++] = index;
                index = parents[index];
            }

            // 将所有路径上的节点，祖先节点均设置为index
            while (helpIndex > 0) {
                parents[helper[--helpIndex]] = index;
            }

            return index;
        }

        public void union(int i1, int j1, int i2, int j2) {
            int i1Parent = find(i1, j1);
            int i2Parent = find(i2, j2);

            // 同一个集合，不用合并
            if (i1Parent == i2Parent) {
                return;
            }

            // 小集合合并到大集合
            if (numbers[i1Parent] >= numbers[i2Parent]) {
                numbers[i1Parent] = numbers[i1Parent] + numbers[i2Parent];
                parents[i2Parent] = i1Parent;
            } else {
                numbers[i2Parent] = numbers[i1Parent] + numbers[i2Parent];
                parents[i1Parent] = i2Parent;
            }

            size--;
        }

        public int size() {
            return size;
        }
    }
}
