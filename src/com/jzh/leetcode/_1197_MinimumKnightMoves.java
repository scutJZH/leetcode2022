package com.jzh.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 一个坐标可以从 -infinity延伸到+infinity的 无限大的棋盘上，你的 骑士驻扎在坐标为[0, 0]的方格里。
 * 骑士的走法和中国象棋中的马相似，走 “日” 字
 * <p>
 * 返回 骑士前去征服坐标为[x, y]的部落所需的最小移动次数 。本题确保答案是一定存在的。
 * <p>
 * 思路：
 * 由于棋盘具有对称性，所以可以把坐标都规划到第一象限
 * 由于是无限边界，用dfs会导致溢出，故使用bfs
 * 注意边界问题：虽然是无限坐标，但是理论上不会无限往更远处走，但是需要给一定的缓冲用于掉头，故可以在[-2, 2]和[desX, desY]组成的正方形棋盘上进行计算，desX=Abs(x)+2，desY=Abs(y)+2（2为缓冲掉头）
 */
public class _1197_MinimumKnightMoves {

    public static void main(String[] args) {
        System.out.println(minKnightMoves(2, 112));
    }

    public static int minKnightMoves(int x, int y) {
        // 由于棋盘具有对称性，故可以把任意x y 转化为绝对值
        int desX = Math.abs(x) + 2;
        int desY = Math.abs(y) + 2;

        int borderX = desX + 2;
        int borderY = desY + 2;
        // 设定边界为[-2, 2]和[desX+2, desY+2]的矩形，多给2格使其可以掉头
        // dp[x+2][y+2]为[x,y]到[desX][desY]的最小步数
        int[][] dp = new int[borderX + 1][borderY + 1];
        for (int i = 0; i < borderX + 1; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 8个方向
        int[][] directions = new int[][]{{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-2, 1}, {-2, -1}, {-1, -2}, {-1, 2}};

        int step = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{2, 2});

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] des = queue.poll();
                if (des[0] == desX && des[1] == desY) {
                    return step;
                }
                // 防止往回走
                if (dp[des[0]][des[1]] != -1) {
                    continue;
                }

                dp[des[0]][des[1]] = step;
                for (int[] dire : directions) {
                    int d1 = des[0] + dire[0];
                    int d2 = des[1] + dire[1];
                    if (0 <= d1 && d1 <= borderX && 0 <= d2 && d2 <= borderY) {
                        queue.add(new int[]{d1, d2});
                    }
                }
            }
            step++;
        }

        return dp[desX][desY];
    }
}
