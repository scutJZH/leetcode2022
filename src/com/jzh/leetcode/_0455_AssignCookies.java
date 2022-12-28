package com.jzh.leetcode;

import java.util.Arrays;

/**
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 * 对每个孩子 i，都有一个胃口值g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j]。如果 s[j]>= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。
 * 你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 * 思路：贪心算法，用尽量小尺寸的饼干先满足胃口小的孩子，再满足胃口大的孩子。
 */
public class _0455_AssignCookies {

    public static void main(String[] args) {
        int[] g = new int[]{1, 2};
        int[] s = new int[]{1, 2, 3};
        System.out.println(findContentChildren(g, s));
    }

    public static int findContentChildren(int[] g, int[] s) {
        if (g == null || g.length == 0 || s == null || s.length == 0) {
            return 0;
        }

        // 先满足胃口小的孩子，再满足胃口大的孩子。用尽量小尺寸的饼干满足
        Arrays.sort(g);
        Arrays.sort(s);

        int count = 0;
        int i = 0; // 遍历饼干的index
        for (int eatSize : g) {
            // 丢掉不满足的尺寸
            while (i < s.length && s[i] < eatSize) {
                i++;
            }
            // 如果还有饼干，则进行投喂
            if (i < s.length) {
                i++; // 喂过的饼干不能重复喂
                count++;
            }
        }

        return count;
    }
}
