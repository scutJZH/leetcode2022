package com.jzh.leetcode;

import java.util.*;

/**
 * 给你一个字符串数组，请你将字母异位词组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词：是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 *
 * 思路：字母异位词中出现的字母均相同，只有顺序不同。
 *  解1：将字符串排序，查看map里是否存在这个键
 *  解2：将字符串进行词频统计，并拼成字符串的键，查看map是否存在这个键
 */
public class _0049_GroupAnagrams {
    public static void main(String[] args) {
        String[] strs = new String[]{"ddddddddddg","dgggggggggg"};
        System.out.println(groupAnagrams1(strs));
        System.out.println(groupAnagrams2(strs));
    }
    public static List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String newStr = String.valueOf(chars);
            List<String> list = map.computeIfAbsent(newStr, value -> new ArrayList<>());
            list.add(str);
        }

        return new ArrayList<>(map.values());
    }

    public static List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = getKey(str);
            List<String> list = map.computeIfAbsent(key, value -> new ArrayList<>());
            list.add(str);
        }

        return new ArrayList<>(map.values());
    }

    public static String getKey(String str) {
        char[] chars = str.toCharArray();
        int[] count = new int[26];

        for (char c : chars) {
            count[c - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count.length; i++){
            if (count[i] > 0) {
                sb.append(count[i]);
                sb.append((char)('a' + i));
            }
        }

        return sb.toString();
    }

}
