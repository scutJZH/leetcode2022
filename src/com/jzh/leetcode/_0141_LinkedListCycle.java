package com.jzh.leetcode;

import com.jzh.common.ListNode;

import java.util.HashSet;
import java.util.Set;

// 给你一个链表的头节点 head ，判断链表中是否有环。
// 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
// 如果链表中存在环 ，则返回 true 。 否则，返回 false 。

// 思路：
// 解1：遍历链表时对节点进行记录
// 解2：快慢指针，快指针一定会套圈慢指针
public class _0141_LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        Set<ListNode> set = new HashSet<>();
        ListNode p = head;
        while (p != null) {
            if (!set.add(p)) {
                return true;
            }
            p = p.next;
        }

        return false;
    }

    public boolean hasCycle1(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }

        ListNode fast = head.next.next, slow = head.next;
        while (fast.next != null && fast.next.next != null && fast != slow) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return fast == slow;
    }
}
