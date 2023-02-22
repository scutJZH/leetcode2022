package com.jzh.exam.huawei;

/**
 * 长度K冗余覆盖
 */
public class GetFirstIndex {

    public static void main(String[] args) {
        String s1 = "";
        String s2 = "";
        int k = 10;
        System.out.println(getFirstIndex(s1, s2, k));
    }

    public static int getFirstIndex(String s1, String s2, int k) {
        // 统计词频
        int[] counts = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            counts[s1.charAt(i) - 'a']++;
        }

        int length = s1.length() + k;

        int[] counts2 = new int[26];
        for (int i = 0; i < length && i < s2.length(); i++) {
            counts2[s2.charAt(i) - 'a']++;
        }
        if (isValid(counts, counts2)) {
            return 0;
        }
        counts2[s2.charAt(0) - 'a']--;

        int start = 1;
        int end = length;

        for (;end < s2.length(); end++, start++) {
            counts2[s2.charAt(end) - 'a']++;
            if (isValid(counts, counts2)) {
                return start;
            }
            counts2[s2.charAt(start) - 'a']--;
        }

        return -1;
    }

    public static boolean isValid(int[] counts, int[] counts2) {
        for (int i = 0; i < 26; i++) {
            if (counts2[i] < counts[i]) {
                return false;
            }
        }
        return true;
    }
}
