package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2023/8/28
 * <p>
 * 两两交换链表中的节点
 * LeetCode 024.
 * <p>
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * <p>
 * 示例 1：
 * 1->2->3->4
 * 2->1->4->3
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * <p>
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 */
public class SwappingNodesInLinkedListInPairs implements Answer {

    public static void main(String[] args) {
        new SwappingNodesInLinkedListInPairs().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        ListNode head = initData();
        System.out.println(head);
        head = swapPairs(head);
        System.out.println(head);
    }

    public ListNode swapPairs(ListNode head) {
        ListNode sentinel = new ListNode();
        sentinel.next = head;
        ListNode pre = sentinel;
        ListNode p1 = head;
        ListNode p2 = head.next;
        boolean flag = true;
        while (pre != null && p1 != null && p2 != null) {
            ListNode temp = p2.next;
            p2.next = p1;
            p1.next = temp;
            pre.next = p2;
            if (flag) {
                head = pre.next;
                flag = false;
            }
            if (pre.next == null) {
                break;
            } else {
                pre = pre.next.next;
                if (pre == null) {
                    break;
                }
                p1 = pre.next;
                if (p1 == null) {
                    break;
                }
                p2 = p1.next;
            }
        }
        return head;
    }


    /**
     * 输出数据
     */
    @Override
    public ListNode initData() {
        return DataFactory.generateLinkedList();
    }
}
