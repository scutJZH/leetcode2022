package com.jzh.leetcode;

import java.util.Stack;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * 实现 MinStack 类:
 *    MinStack() 初始化堆栈对象。
 *    void push(int val) 将元素val推入堆栈。
 *    void pop() 删除堆栈顶部的元素。
 *    int top() 获取堆栈顶部的元素。
 *    int getMin() 获取堆栈中的最小元素。
 *
 * 思路：用两个栈，一个栈存入栈元素，另一个栈存每次存入元素时当时的最小元素
 */

public class _0155_MinStack {
    public static void main(String[] args) {
        _0155_MinStack minStack = new _0155_MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();
    }

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public _0155_MinStack() {
        this.stack = new Stack<>();
        this.minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty()) {
            minStack.push(val);
        } else {
            minStack.push(Math.min(minStack.peek(), val));
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
