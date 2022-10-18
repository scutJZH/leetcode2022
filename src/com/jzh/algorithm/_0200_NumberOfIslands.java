package com.jzh.algorithm;

// 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
// 此外，你可以假设该网格的四条边均被水包围。
public class _0200_NumberOfIslands {

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        System.out.println(numIslands(grid));
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
}
