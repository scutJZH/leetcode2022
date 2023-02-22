package com.jzh.exam.zhaoShangBank;

/**
 * 统计L到R范围内的数字，x一共出现了几次。（如果x=2，则在22中算出现了2次）
 */
public class XCount {

    public static void main(String[] args) {
        System.out.println(countNum(2, 22, 2));
    }

    public static int countNum(int L, int R, int x) {
        int count = 0;
        for (int i = L; i <= R; i++) {
            int t = i;
            while (t > 0) {
                if (t % 10 == x) {
                    count++;
                }
                t = t / 10;
            }
        }

        return count;
    }
}
