package com.jzh.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一个区间的集合intervals，其中 intervals[i] = [starti, endi]。返回 需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 思路：贪心算法，尽可能保留结束时间早的区间
 */
public class _0435_NonOverlappingIntervals {

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 2}, {1, 2},{1, 2}};
        System.out.println(eraseOverlapIntervals(intervals));
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return 0;
        }

        // 按结束时间从小到大排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));

        int end;
        int delCount = 0;
        int index= 0;

        while (index < intervals.length) {
            end = intervals[index++][1];
            // 剩下的区间的right>end，故left<end的都属于有重合的
            while (index < intervals.length && intervals[index][0] < end) {
                delCount++;
                index++;
            }
        }

        return delCount;
    }
}
