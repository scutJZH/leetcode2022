package com.jzh.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。
 * 您想要拼写出给定的字符串 target，方法是从收集的贴纸中切割单个字母并重新排列它们。如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。
 * 返回你需要拼出 target的最小贴纸数量。如果任务不可能，则返回 -1 。
 * 注意：在所有的测试用例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选择的，并且 target被选择为两个随机单词的连接。
 *
 * 思路：递归+记忆化搜索（由于样本是字符串，无法转换为表格，故无法使用动态规划）
 *  如果可以使target的字符数量减少，则结果为 1 + process(stickers, rest, dp)，遍历所有striker选取min，如果用完所有striker，min还是MAX，则说明没有。
 *  优化：
 *      ① 由于会经常使用到reduce查看rest，可以使用词频的方式生成rest。因为target经常会使用到length，故target不使用词频会更快。
 *      ② 由于结果与贴纸的顺序无关，故可以将 target第一个存在的字符，在stickerWord中存在时，才使用这个sticker，减少递归次数
 */
public class _0691_StickersToSpellWord {

    public static void main(String[] args) {
        String[] stickers = new String[]{"a","b"};
        String target = "ab";
        System.out.println(minStickers(stickers, target));
    }

    public static int minStickers(String[] stickers, String target) {
        if (target == null || target.length() == 0) {
            return 0;
        }
        if (stickers == null || stickers.length == 0) {
            return -1;
        }

        int[][] stickerWords = new int[stickers.length][26]; // 统计词频
        for (int i = 0; i < stickers.length; i++) {
            char[] stickerChars = stickers[i].toCharArray();
            int[] words = new int[26];
            for (char stickerChar : stickerChars) {
                words[stickerChar - 'a']++;
            }
            stickerWords[i] = words;
        }

        return process(stickerWords, target, new HashMap<>());
    }

    public static int process(int[][] stickerWords, String target, Map<String, Integer> dp) {
        if (dp.containsKey(target)) {
            return dp.get(target);
        }

        if (target.length() == 0) {
            return 0;
        }

        char[] targetChar = target.toCharArray();
        int[] targetCount = new int[26];
        for (char c : targetChar) {
            targetCount[c - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        for (int[] stickerWord : stickerWords) {
            // 剪枝：由于结果与贴纸的顺序无关，故可以将 target第一个存在的字符，在stickerWord中存在时，才使用这个sticker，减少递归次数
            if (stickerWord[targetChar[0] - 'a'] > 0) {
                String rest = reduce(targetCount, stickerWord);
                // 说明sticker是有效的
                if (target.length() > rest.length()) {
                    int p = process(stickerWords, rest, dp);
                    if (p != -1) {
                        min = Math.min(min, 1 + p);
                    }
                }
            }
        }

        int result = min == Integer.MAX_VALUE ? -1 : min;
        dp.put(target, result);
        return result;
    }

    public static String reduce(int[] targetWord, int[] stickerWord) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            int diff = Math.max(0, targetWord[i] - stickerWord[i]);
            for (int j = 0; j < diff; j++) {
                sb.append((char)('a' + i));
            }
        }
        return sb.toString();
    }
}
