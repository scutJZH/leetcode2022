package com.jzh.leetcode;

import java.util.*;

// 给你一个字符串数组，请你将字母异位词组合在一起。可以按任意顺序返回结果列表。
// 字母异位词：是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。

// 思路：由于字母异位词中出现的字母均相同，只有顺序不同，故可以将单词中的字母进行排序，再进行分组
public class _0049_GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String newStr = Arrays.toString(chars);
            List<String> list = map.computeIfAbsent(newStr, value -> new ArrayList<>());
            list.add(str);
        }

        return new ArrayList<>(map.values());
    }
}
