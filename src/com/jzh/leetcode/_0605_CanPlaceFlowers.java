package com.jzh.leetcode;

/**
 * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * 给你一个整数数组flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数n ，能否在不打破种植规则的情况下种入n朵花？能则返回 true ，不能则返回 false。
 *
 * 思路：贪心算法，从左到右先尽量种植，假设在i位置种花，则有：当i位置没种花，且i-1位置、i+1位置也没种花才可以种花
 */
public class _0605_CanPlaceFlowers {

    public static void main(String[] args) {
        int[] flowerbed = new int[]{1,0,0,0,1};
        System.out.println(canPlaceFlowers(flowerbed, 2));
    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length == 0) {
            return false;
        }
        if (n < 1) {
            return true;
        }

        int count = 0;
        // 从左到右先尽量种植
        for (int i = 0; i < flowerbed.length; i++) {
            // 当i位置没种花，且i-1位置、i+1位置也没种花才可以种花
            if (flowerbed[i] == 0) {
                if ((i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                    count++;
                    flowerbed[i] = 1;
                }
            }
        }

        return count >= n;
    }
}
