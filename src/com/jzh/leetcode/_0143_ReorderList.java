package com.jzh.leetcode;

import com.jzh.common.ListNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 思路：
 * 解1：将链表放入容器中，前后指针操作容器进行重组
 * 解2：反转后半部分链表，进行拼接（通过快慢指针找中点）
 */
public class _0143_ReorderList {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        ListNode newHead = reorderList1(n1);
        System.out.println(1);
    }

    public static ListNode reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 将所有节点放入list
        List<ListNode> list = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            list.add(p);
            p = p.next;
        }

        head = list.get(0);
        p = head;
        int size = list.size();
        for (int i = 1, k = 1, j = 0; i < size; i++) {
            if (i % 2 == 0) {
                p.next = list.get(k);
                k++;
            } else {
                p.next = list.get(size - 1 - j);
                j++;
            }
            p = p.next;
        }
        p.next = null;

        return head;
    }

    public static ListNode reorderList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 快慢指针找链表中点
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 找到需要翻转的点p，从中点反转链表
        ListNode p = slow.next;
        slow.next = null;
        ListNode pre = null;
        while (p != null) {
            ListNode next = p.next;
            p.next = pre;
            pre = p;
            p = next;
        }

        // 链表拼接
        ListNode node = head;
        p = head.next;
        while (p != null && pre != null) {
            node.next = pre;
            pre = pre.next;
            node.next.next = p;
            node = p;
            p = p.next;
        }
        if (pre != null) {
            node.next = pre;
        }

        return head;
    }
}
