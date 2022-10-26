package com.jzh.leetcode;

import com.jzh.util.ListNode;

// 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
//你应当 保留 两个分区中每个节点的初始相对位置。(稳定性)
public class _086_PartitionList {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode smallHead = null, smallTail = null;
        ListNode bigHead = null, bigTail = null;

        ListNode p = head;
        while (p != null) {
            if (p.val < x) {
                if (smallHead == null) {
                    smallHead = p;
                    smallTail = p;
                } else {
                    smallTail.next = p;
                    smallTail = p;
                }
            } else {
                if (bigHead == null) {
                    bigHead = p;
                    bigTail = p;
                } else {
                    bigTail.next = p;
                    bigTail = p;
                }
            }
            p = p.next;
        }
        if (bigTail != null) {
            bigTail.next = null;
        }

        // 连接
        ListNode newHead, tail;
        if (smallHead != null) {
            newHead = smallHead;
            smallTail.next = bigHead;
        } else {
            newHead = bigHead;
        }

        return newHead;
    }
}
