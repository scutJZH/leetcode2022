package com.jzh.leetcode;

import com.jzh.common.ListNode;

// 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。

// 思路：
// 解1：将链表的值放入数组进行比较
// 解2：找到链表中点，反转链表，进行比较
public class _0234_PalindromeLinkedList {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(2);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(1);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
//        n4.next = n5;

        System.out.println(isPalindrome1(n1));
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 统计链表大小
        int i = 0;
        ListNode p = head;
        while (p != null) {
            i++;
            p = p.next;
        }

        int[] helper = new int[i];

        p = head;
        for (int k = 0; k < helper.length; k++, p = p.next) {
            helper[k] = p.val;
        }

        int mid = i / 2;
        for(int k = 0, j = i - 1; k < mid; k++, j--) {
           if (helper[k] != helper[j]) {
               return false;
           }
        }
        return true;
    }

    public static boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 统计链表大小
        int size = 0;
        ListNode p = head;
        while (p != null) {
            size++;
            p = p.next;
        }

        // 为偶数时 1->2->2->1 变为 1->2->null和null<-1<-2
        // 为奇数时 1->2->3->2->1 变为1->2->3<-1<-2，3指向null
        int mid = (size - 1) / 2;
        p = head;
        for (int i = 0; i < mid; i++) {
            p = p.next;
        }

        // 反转数组
        ListNode pre = size % 2 == 0 ? null : p;
        ListNode node = p.next;
        p.next = null;

        while (node != null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }

        // 比较
        p = head;
        while (p != null) {
            if (pre == null || p.val != pre.val) {
                return false;
            }
            p = p.next;
            pre = pre.next;
        }
        return true;
    }
}
