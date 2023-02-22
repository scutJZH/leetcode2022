package com.jzh.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，返回 所需会议室的最小数量 。
 *
 * 思路：
 * 解1：先对数组按开始时间排序，然后找到一个容器，存放结束时间，每加入一个会议时，找到重叠会议一共有多少个（弹出小于等于新加入会议开始时间的结束时间）（根据此种场景，可以使用堆）
 * 解2：直接将开始时间和结束时间一起排序，依次遍历，如果为开始时间，则+1，如果为结束时间，则-1（结束时间要排在开始时间前，避免交点算错）
 * 注意：少用stream流，效率较低
 */
public class _0253_MeetingRoomsII {
    public static void main(String[] args) {
        int[][] intervals = new int[][] {{0, 30}, {5, 10}, {15, 20}};
        int max = minMeetingRooms2(intervals);
        System.out.println(max);
    }

    public static int minMeetingRooms1(int[][] intervals) {
        // 按开始时间排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int[] ints : intervals) {
            int start = ints[0];
            int end = ints[1];

            heap.add(end);
            while (!heap.isEmpty() && heap.peek() <= start) {
                heap.poll();
            }
            max = Math.max(max, heap.size());
        }
        return max;
    }

    public static int minMeetingRooms2(int[][] intervals) {
        // a[i][0]表示时间（包括开始时间和结束时间），a[i][1]表示+1或是-1，即开始还是结束
        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1]; // 结束时间排在开始时间前（结束：-1， 开始 +1）
            } else {
                return o1[0] - o2[0];
            }
        });

        for (int[] i : intervals) {
            heap.add(new int[]{i[0], 1}); // 开始时间
            heap.add(new int[]{i[1], -1}); // 结束时间
        }

        int max = 0;
        int count = 0;
        while (!heap.isEmpty()) {
            count += heap.poll()[1];
            max = Math.max(max, count);
        }

        return max;
    }
}
