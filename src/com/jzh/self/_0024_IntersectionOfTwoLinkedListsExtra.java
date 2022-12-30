package com.jzh.self;

import com.jzh.common.Node;

/**
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。两个链表可能存在相交节点，如果没有返回null。
 * 是LeetCode160的扩展题目
 *
 * 思路：
 *      ① 查看两个链表是否有环，（快慢指针）
 *      ② 若一个链表有环，一个链表无环，则不存在相交节点
 *      ③ 若两个链表都无环，则查找最后一个节点是否相同（可以通过快慢指针找到相遇点，当找到相遇点时，将快指针重置到头节点，并且将步数改为1，再次相遇时则是环的第一个相遇点）
 *              i:  若不同则不想交
 *              ii: 若相同，则通过n步差值法找到第一个相交的节点
 *      ④ 若两个链表都有环，通过快慢指针找到入环的第一个节点。
 *              i:  当两个节点相同，则说明第一个交点在入环第一个节点或之前，通过n步差值法找到第一个交点
 *              ii: 当两个节点不相同，则通过其中一个节点向下寻找，若能找到另一个节点说明在同一个环上，随意返回一个，若不能找到另一个节点，则无交点
 */
public class _0024_IntersectionOfTwoLinkedListsExtra {

    public static Node intersectionOfTwoLinkedListsExtra(Node head1, Node head2) {
        Node fast = null;
        Node slow = null;

        if (head1 == null || head2 == null) {
            return null;
        }

        // 检查是否有环
        Node node1 = getCycleNode(head1);
        Node node2 = getCycleNode(head2);
        if (node1 != null && node2 != null) {
            // 当两个入环点相同时，则说明在环外相交，利用N步差值法来找到交点
            // 当两个入环点不同时，将一个交点一直往下找，看是否能找到交点
            if (node1 == node2) {
                int step1 = getStep(head1, node1);
                int step2 = getStep(head2, node2);

                Node longer = step1 > step2 ? head1 : head2;
                Node shoter = longer == head1 ? head2 : head1;
                int abs = Math.abs(step1 - step2);
                for(int i = 0; i < abs; i++) {
                    longer = longer.next;
                }

                while (longer != shoter) {
                    longer = longer.next;
                    shoter = shoter.next;
                }
                return longer;

            } else {
                Node p = node1.next;
                while (p != node1 && p != node2) {
                    p = p.next;
                }
                return p == node2 ? node1 : null;
            }

        } else if (node1 != null || node2 != null) {
            // 当只有一个存在环时，肯定不相交
            return null;
        } else {
            // 当两个都没环时，使用N步差值法来找到交点
            int step1 = getStep(head1, null);
            int step2 = getStep(head2, null);
            Node longer = step1 > step2 ? head1 : head2;
            Node shoter = longer == head1 ? head2 : head1;
            int abs = Math.abs(step1 - step2);
            for(int i = 0; i < abs; i++) {
                longer = longer.next;
            }

            while (longer != shoter) {
                longer = longer.next;
                shoter = shoter.next;
            }
            return longer;
        }

    }

    public static Node getCycleNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        // 利用快慢指针找是否为环形链表
        Node fast = head.next.next;
        Node slow = head.next;
        while (fast.next != null && fast.next.next != null && fast != slow) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 当快慢指针不相等时，则无环
        if (fast != slow) {
            return null;
        }

        // 当相遇时，将fast调到头结点，步数设为1，再次相遇时则为入环交点
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    public static int getStep(Node head, Node target) {
        if (head == null) {
            return 0;
        }
        Node p = head;
        int step = 1;
        while (p != target) {
            step++;
            p = p.next;
        }
        return step;
    }
}
