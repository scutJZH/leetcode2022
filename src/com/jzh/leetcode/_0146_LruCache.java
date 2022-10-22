package com.jzh.leetcode;

import java.util.HashMap;
import java.util.Map;

// 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
// 实现 LRUCache 类：
//
//    LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
//    int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
//    void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。


// 思路：构造双向链表（一定要加尾指针，每次修改链表后都要注意prev和next是否赋值）
public class _0146_LruCache {

    public static void main(String[] args) {
        _0146_LruCache lru = new _0146_LruCache();
        lru.initLRUCache(2);
        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println(lru.get(1));
        lru.put(3, 3);
        System.out.println(lru.get(2));
        lru.put(4, 4);
        System.out.println(lru.get(1));
        System.out.println(lru.get(3));
        System.out.println(lru.get(4));
    }

    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        Node(int key, int value, Node prev, Node next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int capacity;
    private Map<Integer, Node> cache;

    public void initLRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
    }

    public int get(int key) {
       if (cache.containsKey(key)) {
           Node node  = cache.get(key);
           if (node.prev != null) {
               node.prev.next = node.next;
               if (node.next != null) {
                   node.next.prev = node.prev;
               } else {
                   tail = node.prev;
               }
               node.prev = null;
               node.next = head;
               head.prev = node;
               head = node;
           }
           return node.value;
       }
       return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node  = cache.get(key);
            node.value = value;
            if (node.prev != null) {
                node.prev.next = node.next;
                if (node.next != null) {
                    node.next.prev = node.prev;
                } else {
                    tail = node.prev;
                }
                node.prev = null;
                head.prev = node;
                node.next = head;
                head = node;
            }
        } else {
            Node node = new Node(key, value, null, head);
            if (head != null) {
                head.prev = node;
            } else {
                tail = node;
            }
            head = node;
            cache.put(key, node);
            if (cache.size() > capacity) {
                Node p = tail;
                cache.remove(p.key);
                tail = tail.prev;
                tail.next = null;
            }
        }
    }
}
