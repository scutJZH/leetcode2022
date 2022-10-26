package com.jzh.leetcode;

import com.jzh.util.ListNode;

// 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
// 如果有两个中间结点，则返回第二个中间结点。

// 思路：两次遍历
public class _876_MiddleOfTheLinkedList {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);

//        n1.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        n4.next = n5;
//        n5.next = n6;

        ListNode midNode = middleNode(n1);
        System.out.println(midNode.val);
    }

    public static ListNode middleNode(ListNode head) {
        if (head == null) {
            return head;
        }

        int i = 0;
        ListNode node = head;
        while (node != null) {
            i++;
            node = node.next;
        }

        int mid = i / 2 + 1;

        node = head;
        for (i = 1; i < mid; i++) {
            node = node.next;
        }
        return node;
    }
}
