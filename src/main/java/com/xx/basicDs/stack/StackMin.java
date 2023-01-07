package com.xx.basicDs.stack;

import com.xx.Answer;

import java.util.Stack;

/**
 * @author 玄霄
 * @CreateDate 2022/6/23
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数，
 * 在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 */
public class StackMin implements Answer {

    private Stack<Integer> mainStack;
    private Stack<Integer> minStack;

    public static void main(String[] args) {
        StackMin stackMin = new StackMin();
        stackMin.answerOne();
    }

    @Override
    public void answerOne() {

    }

    public void push(Integer value) {
        mainStack.push(value);
        if (minStack.isEmpty()) {
            minStack.push(value);
        } else {
            Integer lastMin = minStack.peek();
            if (value < lastMin) {
                minStack.push(value);
            } else {
                minStack.push(lastMin);
            }
        }
    }

    public Integer pop() {
        minStack.pop();
        return mainStack.pop();
    }

    public Integer min() {
        return minStack.peek();
    }

    @Override
    public Object initData() {
        return null;
    }
}