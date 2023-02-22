package com.jzh.leetcode;

import java.util.*;

// 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
// 返回所有可能的结果。答案可以按 任意顺序 返回。

// 思路：回溯法
// 注意：
// 我们在每次进行搜索时，如果遇到连续相同的括号我们只需要搜索一次即可
// 比如当前遇到的字符串为 "(((())"，去掉前四个左括号中的任意一个，生成的字符串是一样的，均为 "((())"，
// 因此我们在尝试搜索时，只需去掉一个左括号进行下一轮搜索，不需要将前四个左括号都尝试一遍。
public class _0301_RemoveInvalidParentheses {
    public static void main(String[] args) {
        List<String> list = removeInvalidParentheses("n");
        System.out.println(list.toString());
    }

    public static List<String> removeInvalidParentheses(String s) {
        // 找到s中左右括号分别需要删除几个
        int leftNum = 0;
        int rightNum = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftNum++;
            } else if (c == ')') {
                if (leftNum > 0) {
                    leftNum--;
                } else {
                    rightNum++;
                }
            }
        }
        // 回溯法
        Map<String, String> map = new HashMap<>();
        deleteParentheses(s.toCharArray(), 0, leftNum, rightNum, map);
        return new ArrayList<>(map.keySet());
    }

    public static void deleteParentheses(char[] chars, int start, int leftNum, int rightNum, Map<String, String> map) {
        // 左右删除数量都为0时则返回
        if (leftNum == 0 && rightNum == 0) {
            // 检查是否有效
            if (isValid(chars)) {
                map.put(String.valueOf(chars), "");
            }
            return;
        }
        // 检查剩余字符中左右括号数量是否大于leftNum+rightNum
        if (chars.length - start < leftNum + rightNum) {
            return;
        }

        for (int i = start; i < chars.length; i++) {
            // 注意：相同时则不再执行
            if (i != start && chars[i] == chars[i - 1]) {
                continue;
            }
            if (chars[i] == '(' && leftNum > 0) {
                char[] newChars = deleteIndexAndGenerateNewChars(chars, i);
                deleteParentheses(newChars, i, leftNum - 1, rightNum, map);
            } else if (chars[i] == ')' && rightNum > 0) {
                char[] newChars = deleteIndexAndGenerateNewChars(chars, i);
                deleteParentheses(newChars, i, leftNum, rightNum - 1, map);
            }
        }
    }

    public static char[] deleteIndexAndGenerateNewChars(char[] chars, int index) {
        char[] newChars = new char[chars.length - 1];
        for (int i = 0, j = 0; i < chars.length; i++) {
            if (i != index) {
                newChars[j++] = chars[i];
            }
        }
        return newChars;
    }

    public static boolean isValid(char[] chars) {
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if (c == '(') {
                stack.add(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
