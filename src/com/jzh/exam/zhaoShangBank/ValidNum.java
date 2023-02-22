package com.jzh.exam.zhaoShangBank;

public class ValidNum {

    public static void main(String[] args) {
        System.out.println(validNum(1));
    }

    public static int validNum (int N) {
        if (N < 1) {
            return 0;
        }
        // write code here
        boolean[] used = new boolean[N];
        return process(used, 0, N);
    }

    public static int process(boolean[] used, int index, int N) {
        if (index == N) {
            return 1;
        } else if (index == 0) {
            int p1 = process(used, index + 1, N);
            used[index] = true;
            int p2 = process(used, index + 1, N);
            used[index] = false;
            return p1 + p2;
        }

        if (used[index - 1]) {
            return process(used, index + 1, N);
        } else {
            // 不使用
            int p1 = process(used, index + 1, N);
            // 使用
            used[index] = true;
            int p2 = process(used, index + 1, N);
            used[index] = false;
            return p1 + p2;
        }
    }
}
