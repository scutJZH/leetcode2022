package com.jzh.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组points，其中points[i] = [xstart, xend]表示水平直径在xstart和xend之间的气球（气球直径）。你不知道气球的确切 y 坐标。
 * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart≤ x ≤ xend，则该气球会被 引爆。可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
 * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小弓箭数。
 *
 * 思路：贪心算法：尽量先射右边界在前面的气球。
 */
public class _0452_MinNumberOfArrowsToBurstBalloons {

    public static void main(String[] args) {
        int[][] points = new int[][]{{10,16},{2,8},{1,6},{7,12}};
        System.out.println(findMinArrowShots(points));
    }

    public static int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        // 尽量射右边界在前面的气球
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));

        int count = 1;
        int index = 1;
        int end = points[0][1];

        while (index < points.length) {
            if (points[index][0] > end) {
                // 说明不能重合了，需用一支新的箭
                count++;
                end = points[index++][1];
            } else {
                index++;
            }
        }

        return count;
    }
}
