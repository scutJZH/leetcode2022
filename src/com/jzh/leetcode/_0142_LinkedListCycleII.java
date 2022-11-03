package com.jzh.leetcode;

// 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
// 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。

import com.jzh.util.ListNode;

import java.util.HashSet;
import java.util.Set;

// 思路：
// 解1：通过set辅助找到环的头结点
// 解2：可以通过快慢指针找到相遇点，当找到相遇点时，将快指针重置到头节点，并且将步数改为1，再次相遇时则是环的第一个相遇点
public class _0142_LinkedListCycleII {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(-4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2;
//        ListNode node1 = detectCycle(n1);
//        System.out.println(node1 != null ? node1.val : "null");

        ListNode node2 = detectCycle2(n1);
        System.out.println(node2 != null ? node2.val : "null");
    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        Set<ListNode> set = new HashSet<>();
        ListNode p = head;
        do {
            if (set.contains(p)) {
                return p;
            }
            set.add(p);
            p = p.next;
        } while (p != null);
        return null;
    }

    public static ListNode detectCycle2(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        ListNode fast = head.next.next;
        ListNode slow = head.next;

        // 快慢指针找到相遇位置
        while (fast.next != null && fast.next.next != null && fast != slow) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast != slow) {
            return null;
        }

        // 找到相遇位置后，fast重置为head，并将步数改为1，再次相遇时，则为环形链表的第一个节点
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
