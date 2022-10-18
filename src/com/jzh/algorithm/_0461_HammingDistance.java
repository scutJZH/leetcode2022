package com.jzh.algorithm;

// 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
//给你两个整数 x 和 y，计算并返回它们之间的汉明距离。

// 思路：异或
public class _0461_HammingDistance {
    public static void main(String[] args) {
        System.out.println(hammingDistance(1, 4));
    }

    public static int hammingDistance(int x, int y) {
        int z = x ^ y;
        String s = Integer.toBinaryString(z);
        int num = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                num++;
            }
        }
        return num;
    }
}
