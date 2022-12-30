package com.jzh.leetcode;

import com.jzh.common.Node;

import java.util.HashMap;
import java.util.Map;

// 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
// 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
// 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
// 返回复制链表的头节点。
// 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
//    val：一个表示 Node.val 的整数。
//    random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。

// 思路：找到
public class _0138_CopyListWithRandomPointer {
    public static void main(String[] args) {
        Node n1 = new Node(7);
        Node n2 = new Node(13);
        Node n3 = new Node(11);
        Node n4 = new Node(10);
        Node n5 = new Node(1);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        n1.random = n1;
        n2.random = n1;
        n3.random = n5;
        n4.random = n3;
        n5.random = n1;

        Node newHead = copyRandomList(n1);
        System.out.println(1);
    }

    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // 复制链表及next，并找到 node 和 node' 的对应关系
        Map<Node, Node> map = new HashMap<>();
        Node p = head;
        Node newHead = null, tail = null;
        while (p != null) {
            Node node = new Node(p.val);
            if (newHead == null) {
                newHead = node;
            } else {
                tail.next = node;
            }
            tail = node;
            map.put(p, node);
            p = p.next;
        }

        // 复制random
        p = head;
        tail = newHead;
        while (p != null) {
            if (p.random != null) {
                tail.random = map.get(p.random);
            }
            p = p.next;
            tail = tail.next;
        }
        return newHead;
    }
}
