package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2023/8/29
 * <p>
 * K 个一组翻转链表
 * LeetCode 025
 * <p>
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，
 * 那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 示例 1：
 * 1->2->3->4->5
 * 2->1->4->3->5
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * <p>
 * 示例 2：
 * 1->2->3->4->5
 * 3->2->1->4->5
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 */
public class ReverseKGroup implements Answer {

    public static void main(String[] args) {
        new ReverseKGroup().answerOne();
    }

    @Override
    public void answerOne() {
        ListNode head = initData();
        ListNode right = head;
        ListNode left = head;
        ListNode lastRight = null;
        ListNode newHead = null;
        boolean flag = true;
        int k = 3;
        while (right != null) {
            for (int i = 0; i < k - 1; i++) {
                right = right.next;
                if (right == null) {
                    return;
                }
            }
            ListNode nextLeft = right.next;
            right.next = null;
            ListNode[] handleList = reverseLinkList(left);
            if (flag) {
                newHead = handleList[0];
                flag = false;
            }
            if (lastRight != null) {
                lastRight.next = handleList[0];
            }
            handleList[1].next = nextLeft;
            left = nextLeft;
            right = left;
            lastRight = handleList[1];
        }

        System.out.println(newHead);
    }

    /**
     * 处理完后，head为尾。
     * newPoint为头
     * 思路参考{@link ReverseLinkList}
     */
    public ListNode[] reverseLinkList(ListNode head) {
        ListNode point = head;
        ListNode newPoint = null;
        while (point != null) {
            ListNode cur = point;
            point = point.next;
            cur.next = newPoint;
            newPoint = cur;
        }
        return new ListNode[]{newPoint, head};
    }

    /**
     * 输出数据
     */
    @Override
    public ListNode initData() {
        return DataFactory.generateLinkedList();
    }
}
