package com.jzh.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个大小为 m x n 的二进制网格 grid 。网格表示一个地图，其中，0 表示水，1 表示陆地。最初，grid 中的所有单元格都是水单元格（即，所有单元格都是 0）。
 * 可以通过执行 addLand 操作，将某个位置的水转换成陆地。给你一个数组 positions ，其中 positions[i] = [ri, ci] 是要执行第 i 次操作的位置 (ri, ci) 。
 * 返回一个整数数组 answer ，其中 answer[i] 是将单元格 (ri, ci) 转换为陆地后，地图中岛屿的数量。
 * 岛屿 的定义是被「水」包围的「陆地」，通过水平方向或者垂直方向上相邻的陆地连接而成。你可以假设地图网格的四边均被无边无际的「水」所包围。
 *
 * 思路：使用并查集，逐个加入陆地，并与它的上下左右联结，记录下并查集中集合的size。
 */
public class _0305_NumberOfIslandsII {

    public static void main(String[] args) {
        int m = 3, n = 3;
        int[][] positions = {{0,0},{0,1},{1,2},{2,1}};
        System.out.println(numIslands2(m, n, positions));
    }

    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind unionFind = new UnionFind(m, n);

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < positions.length; i++) {
            int x = positions[i][0];
            int y = positions[i][1];
            unionFind.add(x, y);
            if (x - 1 >= 0) {
                unionFind.union(x, y, x - 1, y);
            }
            if (x + 1 < m) {
                unionFind.union(x, y, x + 1, y);
            }
            if (y - 1 >= 0) {
                unionFind.union(x, y, x, y - 1);
            }
            if (y + 1 < n) {
                unionFind.union(x, y, x, y + 1);
            }
            result.add(unionFind.size);
        }

        return result;
    }

    public static class UnionFind {
        private int m, n;
        private int[] parents;
        private int[] numbers;

        private int[] helper;
        private int size;

        public UnionFind(int m, int n) {
            this.m = m;
            this.n = n;
            parents = new int[m * n];
            numbers = new int[m * n];
            helper = new int[m * n];
            size = 0;
        }

        public void add(int i, int j) {
            int index = i * n + j;
            // 说明已经加过
            if (numbers[index] != 0) {
                return;
            }

            parents[index] = index;
            numbers[index] = 1;
            size++;
        }

        public int find(int i, int j) {
            int index = i * n + j;

            // 看是否存在
            if (numbers[index] == 0) {
                return -1;
            }

            int helperIndex = 0;

            while (parents[index] != index) {
                helper[helperIndex++] = index;
                index = parents[index];
            }

            // 减少层数
            while (helperIndex > 0) {
                parents[helper[--helperIndex]] = index;
            }

            return index;
        }

        public void union(int i1, int j1, int i2, int j2) {
            int i1Parent = find(i1, j1);
            int i2Parent = find(i2, j2);

            if (i1Parent == -1 || i2Parent == -1 || i1Parent == i2Parent) {
                return;
            }

            if (numbers[i1Parent] >= numbers[i2Parent]) {
                numbers[i1Parent] += numbers[i2Parent];
                parents[i2Parent] = i1Parent;
            } else {
                numbers[i2Parent] += numbers[i1Parent];
                parents[i1Parent] = i2Parent;
            }

            size--;
        }
    }

}
