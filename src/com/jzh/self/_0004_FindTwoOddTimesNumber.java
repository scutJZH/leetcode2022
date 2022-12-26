package com.jzh.self;

/**
 * 在一个数组中，有两个数出现了奇数次，其余的数出现了偶数次，找出这两个数
 * <p>
 * 思路：
 * 先对所有的数进行异或处理，能得到a^b
 * 由于a和b不相同，则必然a^b有一位为1
 * 对原数组进行分类，找出该位置为1的数，和该位置不为1的数(因为a^b该位为1说明a与b该位一个为1，一个为0。可以通过x&(~x+1)操作找到最后一位为1)
 * 对某一边进行异或处理，必然能得到 a （可以边分类边异或）
 * 将a^(a^b)，得到b
 */
public class _0004_FindTwoOddTimesNumber {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 6, 6, 7, 7, 7};
        findTwoOddTimesNumber(arr);
    }

    public static void findTwoOddTimesNumber(int[] arr) {
        int x = 0;
        for (int i : arr) {
            x ^= i;
        }
        // 此时得到 x = a ^ b

        // 通过x&(~x+1)操作提取出x的二进制最后一位的1
        x = x & (~x + 1);

        // 通过对对应位是否为1进行分类，然后对某一边进行异或处理
        int t = 0;
        for (int i : arr) {
            if ((i & x) != 0) {
                t ^= i;
            }
        }

        System.out.println(t + ", " + (t ^ x));
    }
}
