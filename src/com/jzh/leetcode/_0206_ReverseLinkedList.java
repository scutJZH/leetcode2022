package com.jzh.leetcode;

import com.jzh.common.ListNode;

// 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。

// 思路：前后指针
public class _0206_ReverseLinkedList {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        n1.next = n2;
        n2.pre = n1;
        n2.next = n3;
        n3.pre = n2;
        n3.next = n4;
        n4.pre = n3;
        n4.next = n5;
        n5.pre = n4;

        ListNode n = reverseDoubleNode(n1);
        System.out.println(1);
    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = head;
        while (next != null) {
            ListNode node = next.next;
            next.next = pre;
            pre = next;
            next = node;
        }
        return pre;
    }

    public static ListNode reverseDoubleNode(ListNode head) {
        ListNode pre = null;
        ListNode next = head;
        while (next != null) {
            ListNode node = next.next;
            next.pre = node;
            next.next = pre;
            pre = next;
            next = node;
        }
        return pre;
    }
}
