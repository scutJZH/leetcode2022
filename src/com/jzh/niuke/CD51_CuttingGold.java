package com.jzh.niuke;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 给定一个正数数组arr，arr的累加和代表金条的总长度，arr的每个数代表金条要分成的长度。规定长度为k的金条分成两块，费用为k个铜板。返回把金条分出arr中的每个数字需要的最小代价。
 * 思路：贪心算法：每次拿最短的金条切成刚好是最短的两块，进行倒推，找到最低金额
 */
public class CD51_CuttingGold {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            long[] arr = new long[a];
            for (int i = 0; i < a; i++) {
                arr[i] = in.nextLong();
            }
            System.out.println(cuttingGold(arr));
        }

//        int[] arr = new int[]{10, 30, 20};
//        int[] arr = new int[]{3, 9, 5, 2, 4, 4};
//        System.out.println(cuttingGold(arr));
    }

    public static long cuttingGold(long[] arr) {
        PriorityQueue<Long> heap = new PriorityQueue<>(); // 小根堆，储存需要切后的金条的长度
        for (long i : arr) {
            heap.add(i);
        }

        long count = 0L;
        while (heap.size() >= 2) {
            long left = heap.poll();
            long right = heap.poll();

            long sum = left + right;
            count += sum;
            heap.add(sum);
        }

        return count;
    }
}
