package com.jzh.leetcode;

/**
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * 你需要按照以下要求，给这些孩子分发糖果：
 *     每个孩子至少分配到 1 个糖果。
 *     相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 *
 * 思路：
 * 将规则相邻两个孩子评分更高的孩子会获得更多的糖果分解为：
 *  ① 左规则： 当 ratingsB>ratingsA时，B 的糖比 A 的糖数量多。
 *  ② 右规则： 当 ratingsA>ratingsB时，A 的糖比 B 的糖数量多。
 *
 *  先从左至右遍历学生成绩 ratings，按照以下规则给糖，并记录在 left 中：
 *     先给所有学生 1 颗糖；
 *     若 ratingsi>ratingsi−1，则第 i 名学生糖比第 i−1 名学生多 1 个。
 *     若 ratingsi<=ratingsi−1，则第 i 名学生糖数量不变。（交由从右向左遍历时处理。）
 *     经过此规则分配后，可以保证所有学生糖数量 满足左规则 。
 * 同理，在此规则下从右至左遍历学生成绩并记录在 right 中，可以保证所有学生糖数量 满足右规则 。两个数组取max可得到答案
 *
 * 为什么取max可以保证相邻两点数量多少不会颠倒呢：取任意相邻两点A、B
 * 如果A大于B。从左往右遍历时，有leftB=1，A为X（X >= 1）。从右往左遍历时，有rightB=M（M>=1）,rightA=M+1。
 * 取Max则有 A=Max(X, M+1)，B=M，则一定有A>B
 */
public class _0135_Candy {

    public static void main(String[] args) {
        int[] ratings = new int[]{1, 2, 2};
        System.out.println(candy(ratings));
    }

    public static int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        int defaultCandy = 1;

        int[] leftHelper = new int[ratings.length];
        int[] rightHelper = new int[ratings.length];

        // 从左往右遍历，使其满足左规则
        leftHelper[0] = defaultCandy;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                leftHelper[i] = leftHelper[i - 1] + 1;
            } else {
                leftHelper[i] = defaultCandy;
            }
        }

        // 从右往左遍历，使其满足右规则
        rightHelper[rightHelper.length - 1] = defaultCandy;
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                rightHelper[i] = rightHelper[i + 1] + 1;
            } else {
                rightHelper[i] = defaultCandy;
            }
        }

        // 计算总糖数
        int count = 0;
        for (int i = 0; i < ratings.length; i++) {
            count += Math.max(leftHelper[i], rightHelper[i]);
        }
        return count;
    }
}
