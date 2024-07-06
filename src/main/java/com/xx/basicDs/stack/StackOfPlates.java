package com.xx.basicDs.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2024/7/6
 * <p>
 * 堆盘子
 * LeetCode Middle
 * <p>
 * 堆盘子。设想有一堆盘子，堆太高可能会倒下来。因此，在现实生活中，盘子堆到一定高度时，
 * 我们就会另外堆一堆盘子。请实现数据结构SetOfStacks，模拟这种行为。
 * SetOfStacks应该由多个栈组成，并且在前一个栈填满时新建一个栈。
 * 此外，SetOfStacks.push()和SetOfStacks.pop()应该与普通栈的操作方法相同（也就是说，pop()返回的值，
 * 应该跟只有一个栈时的情况一样）。
 * 进阶：实现一个popAt(int index)方法，根据指定的子栈，执行pop操作。
 * 当某个栈为空时，应当删除该栈。当栈中没有元素或不存在该栈时，pop，popAt 应返回 -1.
 * <p>
 * 示例1:
 * 输入：
 * ["StackOfPlates", "push", "push", "popAt", "pop", "pop"]
 * [[1], [1], [2], [1], [], []]
 * 输出：
 * [null, null, null, 2, 1, -1]
 * <p>
 * 示例2:
 * 输入：
 * ["StackOfPlates", "push", "push", "push", "popAt", "popAt", "popAt"]
 * [[2], [1], [2], [3], [0], [0], [0]]
 * 输出：
 * [null, null, null, null, 2, 1, 3]
 * <p>
 * Tag：栈
 */
public class StackOfPlates {
    public static void main(String[] args) {
        StackOfPlates stackOfPlates = new StackOfPlates(1);
        stackOfPlates.push(1);
        stackOfPlates.push(2);
        System.out.println(stackOfPlates.popAt(1));
        System.out.println(stackOfPlates.pop());
        System.out.println(stackOfPlates.pop());
    }

    List<Stack<Integer>> stackList;
    int capacity = 0;

    public StackOfPlates(int cap) {
        capacity = cap;
        stackList = new ArrayList<>();
    }

    /**
     *
     */
    public void push(int val) {
        // 容量为0，不需要继续放盘子
        if (capacity <= 0) {
            return;
        }
        if (stackList.isEmpty()) {
            Stack<Integer> stack = new Stack<>();
            stack.push(val);
            stackList.add(stack);
        } else {
            Stack<Integer> stack = stackList.get(stackList.size() - 1);
            if (stack.size() == capacity) {
                Stack<Integer> newStack = new Stack<>();
                newStack.push(val);
                stackList.add(newStack);
            } else {
                stack.push(val);
            }
        }
    }

    public int pop() {
        if (stackList.isEmpty()) {
            return -1;
        } else {
            Stack<Integer> stack = stackList.get(stackList.size() - 1);
            if (stack == null) {
                return -1;
            }
            Integer pop = stack.pop();
            if (stack.isEmpty()) {
                stackList.remove(stackList.size() - 1);
            }
            return pop;
        }
    }

    public int popAt(int index) {
        if (index < 0 || index >= stackList.size()) {
            return -1;
        }
        Stack<Integer> stack = stackList.get(index);
        if (stack == null) {
            return -1;
        }
        Integer pop = stack.pop();
        if (stack.isEmpty()) {
            stackList.remove(index);
        }
        return pop;
    }
}
