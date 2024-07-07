package com.xx.basicDs.stack;

import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2024/7/7
 * <p>
 * 栈排序
 * LeetCode Easy
 * <p>
 * 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，
 * 但不得将元素复制到别的数据结构（如数组）中。该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。
 * <p>
 * 示例1:
 * 输入：
 * ["SortedStack", "push", "push", "peek", "pop", "peek"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null,null,null,1,null,2]
 * <p>
 * 示例2:
 * 输入：
 * ["SortedStack", "pop", "pop", "push", "pop", "isEmpty"]
 * [[], [], [], [1], [], []]
 * 输出：
 * [null,null,null,null,null,true]
 * 说明:
 * <p>
 * 栈中的元素数目在[0, 5000]范围内。
 * <p>
 * Tag：惰性更新
 */
class SortedStack {

    public static void main(String[] args) {
        SortedStack sortedStack1 = new SortedStack();
        sortedStack1.push(5);
        sortedStack1.push(3);
        sortedStack1.push(1);
        sortedStack1.push(2);
        sortedStack1.push(6);

    }

    Stack<Integer> sortedStack;
    Stack<Integer> tempStack;

    public SortedStack() {
        sortedStack = new Stack<>();
        tempStack = new Stack<>();
    }

    /**
     * 如果每次push完了都从temp移动回来。
     * 那么连续N次push时，就会浪费。
     * 因此采用惰性更新，把移动放到pop的时候弄。
     */
    public void push(int val) {
        while (true) {
            int stackMin = sortedStack.isEmpty() ? Integer.MAX_VALUE : sortedStack.peek();
            int tmpMax = tempStack.isEmpty() ? Integer.MIN_VALUE : tempStack.peek();
            if (stackMin < val) {
                tempStack.push(sortedStack.pop());
            } else if (tmpMax > val) {
                sortedStack.push(tempStack.pop());
            } else {
                sortedStack.push(val);
                break;
            }
        }
    }

    public void pop() {
        updateStack();
        if (!sortedStack.isEmpty()) {
            sortedStack.pop();
        }
    }

    public int peek() {
        updateStack();
        if (sortedStack.isEmpty()) {
            return -1;
        }
        return sortedStack.peek();
    }

    public boolean isEmpty() {
        return sortedStack.isEmpty();
    }

    private void updateStack() {
        while (!tempStack.isEmpty()) {
            sortedStack.push(tempStack.pop());
        }
    }
}

