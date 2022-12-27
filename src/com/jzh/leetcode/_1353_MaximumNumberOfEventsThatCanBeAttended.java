package com.jzh.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个数组events，其中events[i] = [startDayi, endDayi]，表示会议i开始于startDayi，结束于endDayi。
 * 你可以在满足startDayi<= d <= endDayi中的任意一天d参加会议i。注意，一天只能参加一个会议。
 * 请你返回你可以参加的最大会议数目。
 *
 * 思路：贪心算法，开始时间早的会议先参加，相同开始时间的会议，尽可能参加结束时间早的（虽然都是1天，但时间结束的早的会议选择更小）
 * 故使用小顶堆来储存结束时间，当第n天时，将开始时间=n的会议放入到堆中，先从堆排除结束时间<n的会议，再弹出一个最早结束的会议来参加
 */
public class _1353_MaximumNumberOfEventsThatCanBeAttended {
    public static void main(String[] args) {
        int[][] events = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 2}};
        System.out.println(maxEvents(events));
    }

    public static int maxEvents(int[][] events) {
        if (events == null || events.length == 0) {
            return 0;
        }

        // 按开始时间排序
        Arrays.sort(events, Comparator.comparingInt(e -> e[0]));

        // 小顶堆，用来储存结束时间
        PriorityQueue<Integer> endDay = new PriorityQueue<>();

        // 天数开始
        int count = 0;
        int currentDay = 1;
        int i = 0; // 储存遍历数组的index
        while (i < events.length || !endDay.isEmpty()) {
            // 将currentDay开始的会议加入到堆中
            while (i < events.length && events[i][0] <= currentDay) {
                endDay.add(events[i][1]);
                i++;
            }

            // 将过期的会议剔除
            while (!endDay.isEmpty() && endDay.peek() < currentDay) {
                endDay.poll();
            }

            // 选择一个最早完的会议参加
            if (!endDay.isEmpty()) {
                count++;
                endDay.poll();
            }

            currentDay++;
        }

        return count;
    }
}
