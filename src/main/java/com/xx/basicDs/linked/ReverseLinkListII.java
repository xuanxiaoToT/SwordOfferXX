package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/19
 * <p>
 * 反转链表II
 * LeetCode 92. Medium
 * <p>
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * <p>
 * 示例 2：
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 * <p>
 * 提示：
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 */
public class ReverseLinkListII implements Answer {

    public static void main(String[] args) {
        new ReverseLinkListII().answerOne();
    }

    /**
     * 先把left之前的断开
     * 反转left到right之间的节点。
     * 连接right之后的
     * 连接left之后的
     */
    @Override
    public void answerOne() {
        int left = 1;
        int right = 6;
        ListNode head = initData();
        if (left == right) {
            return;
        }
        System.out.println(head);
        ListNode cur = head;
        ListNode leftNode = null;
        ListNode preLeft = null;
        ListNode afterRight = null;
        int i = 1;
        while (cur != null) {
            if (i == left - 1) {
                preLeft = cur;
            }
            if (i == left) {
                leftNode = cur;
            }
            if (i == right + 1) {
                afterRight = cur;
            }
            cur = cur.next;
            i++;
        }
        ListNode point = leftNode;
        ListNode prePoint = afterRight;
        while (point != null && point != afterRight) {
            ListNode temp = point;
            point = point.next;
            temp.next = prePoint;
            prePoint = temp;
        }
        if (preLeft != null) {
            preLeft.next = prePoint;
            System.out.println(head);
        } else {
            System.out.println(prePoint);
        }
    }

    /**
     * 头插法
     * 每次把后一个查到本节点之前，也是反转
     */
    private ListNode answerTwo() {
        ListNode head = initData();
        int left = 2;
        int right = 5;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
        ListNode curr = prev.next;
        for (int i = 0; i < right - left; i++) {
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }
        return dummy.next;
    }


    @Override
    public ListNode initData() {
        return DataFactory.generateLinkedList();
    }
}