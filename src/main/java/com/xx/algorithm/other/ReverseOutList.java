package com.xx.algorithm.other;

import com.xx.domain.ListNode;

import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2022/6/16
 */
public class ReverseOutList {

    public static void main(String[] args) {
        ReverseOutList reverseOutList = new ReverseOutList();
        reverseOutList.answerOne(null);
    }

    public void answerOne(ListNode listNode) {
        Stack stack = new Stack();
        ListNode point = listNode;
        while (point != null) {
            stack.push(point.value);
            point = point.next;
        }

        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}