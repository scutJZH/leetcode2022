package com.jzh.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个字符串s，通过将字符串s中的每个字母转变大小写，我们可以获得一个新的字符串。
 * 返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。
 * 例：s = "a1b2"，输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * 思路：递归，如果是字母则通过变大或变小来进行不同的选择（大小写字母可以用 ^= 32 来进行转换和恢复，A~Z是97~122 都是 xx1xxxxx a~z是65~90 都是xx0xxxxx）
 */
public class _0784_LetterCasePermutation {

    public static void main(String[] args) {
        List<String> list = letterCasePermutation("a1b2");
        System.out.println(list);
    }

    public static List<String> letterCasePermutation(String s) {
        if (s == null || s.length() == 0) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        process(s.toCharArray(), 0, result);
        return result;
    }

    public static void process(char[] chars, int index, List<String> result) {
        if (index == chars.length) {
            result.add(String.valueOf(chars));
            return;
        }

        // 是否为字母
        if (isLetter(chars[index])) {
            // 大写和小写两种情况
            process(chars, index + 1, result);
            chars[index] ^= 32;
            process(chars, index + 1, result);
        } else {
            process(chars, index + 1, result);
        }
    }

    public static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}
