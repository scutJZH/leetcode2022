package com.jzh.niuke;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一个长度为n的字符串数组strs，请找到一种拼接顺序，使得数组中所有的字符串拼接起来组成的字符串是所有拼接方案中字典序最小的，并返回这个拼接后的字符串。
 * 思路：贪心算法
 * 比较器对a.b和b.a进行比较，然后整体排序。不能用a、b比较，例如a="ba"，b="b"，如果单纯比较a和b，则会有b < a，ba拼接出来为"bba" 显然大于 ab="bab"
 * 注：字典序：①长度相等时，直接比大小；②长度不相等时，较短字符串低位补0，再比大小
 */
public class NC85_MinLexicographicalOrder {

    public static String minString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        Arrays.sort(strs, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));

        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }

}
