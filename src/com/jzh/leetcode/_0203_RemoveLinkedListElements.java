package com.jzh.leetcode;

import com.jzh.common.ListNode;

/**
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点。
 *
 * 思路：先检查头节点是否为val，再利用前后指针删除为val的节点
 */

public class _0203_RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        // 检查head是否为val
        while (head != null && head.val == val) {
            head = head.next;
        }

        ListNode pre = head;
        ListNode next;
        while (pre != null) {
            next = pre.next;
            if (next != null && next.val == val) {
                pre.next = next.next;
            } else {
                pre = next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        System.out.println(getMaxLength("..x..x..xx...", 2));
    }

    public static int getMaxLength(String memory, int rest) {
        if (memory == null || "".equals(memory)) {
            return 0;
        }

        char[] chars = memory.toCharArray();

        int[][] dp = new int[chars.length][rest + 1];

        dp[0][0] = chars[0] == 'x' ? 0 : 1;
        int maxLength = dp[0][0];

        for (int i = 1; i < rest + 1; i++) {
            dp[0][i] = 1;
            maxLength = Math.max(maxLength, dp[0][i]);
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < rest + 1; j++) {
                if (j == 0) {
                    dp[i][0] = chars[i] == 'x' ? 0 : 1 + dp[i - 1][0];
                } else {
                    dp[i][j] = chars[i] == 'x' ? dp[i - 1][j - 1] + 1 : dp[i - 1][j] + 1;
                }
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }

        return maxLength;
    }
}

