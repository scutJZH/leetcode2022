package com.jzh.algorithm;

import com.jzh.util.ListNode;

// 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点。
// 思路：先检查头节点是否为val，再利用前后指针删除为val的节点
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
}
