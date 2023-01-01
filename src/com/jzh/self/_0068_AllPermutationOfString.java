package com.jzh.self;

import java.util.*;

/**
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 * 思路：递归
 * 解1：不包含重复元素，使用set储存。将所有字符放到List中，挨个取
 * 解2：对解1进行优化，① 使用index表示index前已经排好序，通过交换之后字母和index的位置来进行不同的排序，然后继续排index + 1位.② 通过boolean[] check数组来表示第index位是否已经用过了char[i]，进行去重操作
 */
public class _0068_AllPermutationOfString {

    public static void main(String[] args) {
        String[] strs = permutation2("abc");
        for (String str : strs) {
            System.out.println(str);
        }
    }

    public static String[] permutation1(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        char[] chars = s.toCharArray();
        List<Character> charList = new ArrayList<>();
        for (char c : chars) {
            charList.add(c);
        }

        Set<String> result = new HashSet<>();
        process1(charList, "", result);
        return result.toArray(new String[0]);
    }

    public static void process1(List<Character> charList, String str, Set<String> result) {
        if (charList.size() == 0) {
            result.add(str);
            return;
        }

        for (int i = 0; i < charList.size(); i++) {
            char c = charList.remove(i);
            process1(charList, str + c, result);
            // 归位
            charList.add(i, c);
        }
    }

    public static String[] permutation2(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        List<String> result = new ArrayList<>();
        process2(s.toCharArray(), 0, result);
        return result.toArray(new String[0]);
    }

    public static void process2(char[] chars, int index, List<String> result) {
        if (index == chars.length - 1) {
            result.add(String.valueOf(chars));
            return;
        }

        boolean[] check = new boolean[256]; // ASCII码最多256,表示第i位已经使用过chars[i]了
        for (int i = index; i < chars.length; i++) {
            if (check[chars[i]]) {
                continue;
            }
            // 进行位置交换
            swap(chars, index, i);
            // 确定了index位的字母
            process2(chars, index + 1, result);
            // 换回来
            swap(chars, index, i);
            check[chars[i]] = true; // 表示第i位已经使用过chars[i]了
        }
    }

    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
