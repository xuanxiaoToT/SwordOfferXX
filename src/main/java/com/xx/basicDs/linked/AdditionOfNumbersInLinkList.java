package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/24
 * <p>
 * 链表中的数字相加
 * LeetCode 002 两数相加
 * <p>
 * 给定两个表示非负整数的单向链表，请问如何实现这两
 * 个整数的相加并且把它们的和仍然用单向链表表示？链表中的每个
 * 节点表示整数十进制的一位，并且头节点对应整数的最高位数而尾
 * 节点对应整数的个位数。
 * <p>
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * <p>
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * <p>
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 */
public class AdditionOfNumbersInLinkList implements Answer {
    public static void main(String[] args) {
        new AdditionOfNumbersInLinkList().answerOne();
    }

    /**
     * 注意点：涉及到整数相加的这种题目，一定要考虑溢出的问题。
     * 所以可以先遍历，求出两个string，然后按照string相加的方式进行计算。
     */
    @Override
    public void answerOne() {
        //省略
    }

    /**
     * 先反转链表，再进行相加计算。
     */
    public void answerTwo() {

    }

    /**
     * 本身就是逆序存储
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.value : 0;
            int y = (q != null) ? q.value : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    /**
     * something
     */
    @Override
    public Object initData() {
        return null;
    }
}
