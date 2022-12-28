package com.jzh.leetcode;

import java.util.*;

/**
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * 返回一个表示每个字符串片段的长度的列表。
 *
 * 思路：得到每个字母的下标区间(最左, 最右)，再通过遍历找最右边界
 */
public class _0763_PartitionLabels {

    public static void main(String[] args) {
        String s = "ab";
        System.out.println(partitionLabels(s));
    }

    public static List<Integer> partitionLabels(String s) {
        if (s == null || s.length() == 0) {
            return Collections.emptyList();
        }

        // 优化1：先转化为char数组，减少string.charAt调用
        char[] chars = s.toCharArray();

        // 优化2：用数组储存映射，而不是用map
        int[] indexArr = new int[26];
        for (int i = 0; i < chars.length; i++) {
            indexArr[chars[i] - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();
        int start = 0;
        int end = 0;

        for (int i = 0; i < chars.length; i++) {
            // 延长右边界
            end = Math.max(end, indexArr[chars[i] - 'a']);
            // 如果延长后的右边界还是和i相等，则说明该区间里包含了所有有关的字符了，其它地方没有了
            if (i == end) {
                result.add(end - start + 1);
                start = i + 1;
            }
        }

        return result;
    }
}
