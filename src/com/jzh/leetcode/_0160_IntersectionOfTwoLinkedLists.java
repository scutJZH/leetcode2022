package com.jzh.leetcode;

import com.jzh.common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null。
 *
 * 思路：
 * 解1：利用容器
 * 解2：相交后最后一个节点肯定是同一个，并且记录两个节点的长度，利用长度差n，让长的链表先走n步，再同时步长为1一起走，当相遇时则为相交的第一个点
 */

public class _0160_IntersectionOfTwoLinkedLists {
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        Set<ListNode> set = new HashSet<>();
        ListNode p = headA;
        while (p != null) {
            set.add(p);
            p = p.next;
        }

        p = headB;
        while (p != null) {
            if (!set.add(p)) {
              return p;
            }
            p = p.next;
        }

        return null;
    }

    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode p1 = headA;
        ListNode p2 = headB;

        int step = 0;
        while (p1.next != null) {
            p1 = p1.next;
            step++;
        }

        while (p2.next != null) {
            p2 = p2.next;
            step--;
        }

        if (p1 != p2) {
            return null;
        }

        ListNode longer = step >= 0 ? headA : headB;
        ListNode shorter = longer == headA ? headB : headA;

        step = Math.abs(step);

        for (int i = 0; i < step; i++) {
            longer = longer.next;
        }

        while (longer != shorter) {
            longer = longer.next;
            shorter = shorter.next;
        }

        return longer;
    }
}
