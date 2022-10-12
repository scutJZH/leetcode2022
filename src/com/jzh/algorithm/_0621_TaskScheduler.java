package com.jzh.algorithm;

// 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。//
// 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
//
// 你需要计算完成所有任务所需要的最短时间

import java.lang.reflect.Array;
import java.util.Arrays;

// 思路： https://leetcode.cn/problems/task-scheduler/solution/tong-zi-by-popopop/
public class _0621_TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        int[] arr = new int[26];
        // 统计每个任务的数量
        for (char c : tasks) {
            arr[c - 'A']++;
        }
        // 找到最多的任务数量
        Arrays.sort(arr);
        int maxNum = arr[25];

        // 找到最后一次执行的并行任务数量
        int num = 1;
        for (int i = 24; i >= 0; i--) {
            if (arr[i] == maxNum) {
                num++;
            } else {
                break;
            }
        }

        return Math.max(tasks.length, (maxNum - 1) * (n + 1) + num);
    }
}