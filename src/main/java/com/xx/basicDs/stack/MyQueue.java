package com.xx.basicDs.stack;

import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2024/7/6
 * <p>
 * 化栈为队
 * LeetCode  Easy
 * <p>
 * 实现一个MyQueue类，该类用两个栈来实现一个队列。
 * <p>
 * 示例：
 * MyQueue queue = new MyQueue();
 * <p>
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 * <p>
 * 说明：
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 * <p>
 * Tag:栈 队列
 */
class MyQueue {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.pop();
        myQueue.empty();
    }

    Stack<Integer> stackA;
    Stack<Integer> stackB;
    Integer peek;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        stackA = new Stack<>();
        stackB = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        stackA.push(x);
        if (peek == null) {
            peek = x;
        }
    }

    /**
     * Removes the element from in front of queue and returns that element.
     * 就是左右颠回来
     */
    public int pop() {
        while (stackA.size() > 1) {
            stackB.push(stackA.pop());
        }
        Integer pop = stackA.pop();
        peek = stackB.isEmpty() ? null : stackB.peek();
        while (!stackB.isEmpty()) {
            stackA.push(stackB.pop());
        }
        return pop;
    }

    /**
     * Get the front element.
     */
    public int peek() {
        return peek;
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return stackA.isEmpty();
    }
}
