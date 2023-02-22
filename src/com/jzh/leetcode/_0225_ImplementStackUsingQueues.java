package com.jzh.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 * 实现 MyStack 类：
 *    void push(int x) 将元素 x 压入栈顶。
 *    int pop() 移除并返回栈顶元素。
 *    int top() 返回栈顶元素。
 *    boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 * 注意：
 *    你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 *    你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 *
 * 思路：使用两个队列，取出时将一个队列倒完只剩一个元素，返回该元素即可
 */
public class _0225_ImplementStackUsingQueues {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public _0225_ImplementStackUsingQueues() {
        this.queue1 = new LinkedList<>();
        this.queue2 = new LinkedList<>();
    }

    public void push(int x) {
        if (queue1.isEmpty()) {
            queue2.add(x);
        } else {
            queue1.add(x);
        }
    }

    public int pop() {
        if (queue1.isEmpty()) {
            return pushToAnotherAndReturnLast(queue2, queue1);
        } else {
            return pushToAnotherAndReturnLast(queue1, queue2);
        }
    }

    public int top() {
        int x;
        if (queue1.isEmpty()) {
            x = pushToAnotherAndReturnLast(queue2, queue1);
            queue1.add(x);
        } else {
            x = pushToAnotherAndReturnLast(queue1, queue2);
            queue2.add(x);
        }
        return x;
    }

    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }

    private int pushToAnotherAndReturnLast(Queue<Integer> resource, Queue<Integer> target) {
        while (resource.size() > 1) {
            target.add(resource.poll());
        }
        return resource.poll();
    }
}
