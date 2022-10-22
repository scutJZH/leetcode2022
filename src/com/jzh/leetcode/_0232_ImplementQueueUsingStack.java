package com.jzh.leetcode;

// 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
//
// 实现 MyQueue 类：
//    void push(int x) 将元素 x 推到队列的末尾
//    int pop() 从队列的开头移除并返回元素
//    int peek() 返回队列开头的元素
//    boolean empty() 如果队列为空，返回 true ；否则，返回 false
//
// 说明：
//    你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
//    你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。

import java.util.Stack;

// 思路：
// 一个push栈，一个pop栈，当pop栈为空时，将push栈中所有元素倒入pop栈中，再返回pop栈的栈顶元素（反反得正）
// 若pop栈不为空时，直接返回pop栈的栈顶元素（为了避免倒入push栈元素时，将栈顶元素覆盖，导致顺序错乱）
public class _0232_ImplementQueueUsingStack {
    private Stack<Integer> pushStack;
    private Stack<Integer> popStack;

    public _0232_ImplementQueueUsingStack() {
        this.pushStack = new Stack<>();
        this.popStack = new Stack<>();
    }

    public void push(int x) {
        pushStack.push(x);
    }

    public int pop() {
        pushStackIntoPopStack();
        return popStack.pop();
    }

    public int peek() {
        pushStackIntoPopStack();
        return popStack.peek();
    }

    public boolean empty() {
        return pushStack.isEmpty() && popStack.isEmpty();
    }

    private void pushStackIntoPopStack() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }
}
