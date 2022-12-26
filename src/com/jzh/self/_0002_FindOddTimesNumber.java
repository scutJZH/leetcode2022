package com.jzh.self;

/**
 * 一个数组中一种数出现了奇数次，其它数都出现了偶数次，请找出这个数
 * 思路：使用异或。利用任何数和自己异或都为0，任何数和0异或都为本身的特性，以及异或交换律
 */
public class _0002_FindOddTimesNumber {

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,2,2,2,2,3,3,3,3,4,4,5,6,6};
        System.out.println(findOddTimesNumber(arr));
    }

    public static int findOddTimesNumber(int[] arr) {
        int n = 0;
        for (int i : arr) {
            n ^= i;
        }
        return n;
    }
}
