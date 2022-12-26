package com.jzh.self;

/**
 * 一个数组中有一种数出现了K次，其它数都出现了M次，1 < K < M，找到出现了K次的数n要求：时间复杂度O(N)，空间复杂度O(1)
 *
 * 思路：
 *     构造一个32位数组a，a[i]表示所有数的第i位之和
 *     假设 共有x个数出现了M次，且这x个数第i位都为1
 *     若n在第i位为1，则a[i] = x * M + K
 *     若n在第i为不为1，则a[i] = x * M
 *     因为K < M，故当a[i] % M不为0时，则n在i位为1，否则为0。
 */
public class _0005_FindKTimesNumber {

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,1,2,2,2,3,3,3,4,4,4,5,5,6,6,6,7,7,7};
        System.out.println(findKTimesNumber(arr,2, 3));
    }

    public static int findKTimesNumber(int[] arr, int k, int m) {
        int[] helper = new int[32];

        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                // 数的每一位都&i，得到该位为1还是0
                helper[i] += (num >> i) & 1;
            }
        }

        int n = 0;
        for (int i = 0; i < 32; i++) {
            // helper[i] % M不为0时，则n在i位为1，否则为0。
            if (helper[i] % m > 0) {
                n |= 1 << i;
            }
        }

        return n;
    }
}
