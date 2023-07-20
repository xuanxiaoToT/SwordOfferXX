package com.xx.basicDs.stack;

import com.xx.Answer;

import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2022/6/23
 * <p>
 * 最小栈
 * LeetCode 155.
 * <p>
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数，
 * 在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 * <p>
 * 示例 1:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */
public class StackMin implements Answer {

    private Stack<Integer> mainStack;
    private Stack<Integer> minStack;

    public static void main(String[] args) {
        StackMin stackMin = new StackMin();
        stackMin.answerOne();
    }


    /**
     * 只需要设计一个数据结构，使得每个元素 a 与其相应的最小值 m 时刻保持一一对应。因此我们可以使用一个辅助栈，与元素栈同步插入与删除，
     * 用于存储与每个元素对应的最小值。
     * ·当一个元素要入栈时，我们取当前辅助栈的栈顶存储的最小值，与当前元素比较得出最小值，将这个最小值插入辅助栈中；
     * ·当一个元素要出栈时，我们把辅助栈的栈顶元素也一并弹出；
     * ·在任意一个时刻，栈内元素的最小值就存储在辅助栈的栈顶元素中。
     */
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