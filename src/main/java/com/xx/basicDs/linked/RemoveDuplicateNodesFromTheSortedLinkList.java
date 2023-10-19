package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.basicDs.array.RemoveDuplicatesFromOrderedArraysII;
import com.xx.domain.ListNode;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/19
 * <p>
 * 删除排序链表中的重复元素II
 * LeetCode 82. Medium
 * <p>
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，
 * 只留下不同的数字 。返回 已排序的链表 。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 * <p>
 * 示例 2：
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 * <p>
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序 排列
 */
public class RemoveDuplicateNodesFromTheSortedLinkList implements Answer {

    public static void main(String[] args) {
        new RemoveDuplicateNodesFromTheSortedLinkList().answerOne();
    }

    /**
     * fixme：添加哨兵节点，去掉麻烦的各类判空
     * 思路：利用count完成对相同元素的计数，当遇到前后不相等的时候，进行处理。
     * 参考: {@link RemoveDuplicatesFromOrderedArraysII}
     */
    @Override
    public void answerOne() {
        ListNode head = initData();
        if (head == null || head.next == null) {
            System.out.println(head);
            return;
        }
        ListNode point = head.next;
        ListNode pre = head;
        ListNode newPoint = null;
        int count = 1;
        while (point != null) {
            if (point.value != pre.value) {
                if (count == 1) {
                    if (newPoint == null) {
                        newPoint = pre;
                        head = newPoint;
                    } else {
                        newPoint.next = pre;
                        newPoint = newPoint.next;
                    }
                }
                count = 1;
            } else {
                count++;
            }
            point = point.next;
            pre = pre.next;
        }
        if (count == 1) {
            if (newPoint == null) {
                newPoint = pre;
                head = newPoint;
            } else {
                newPoint.next = pre;
                newPoint = newPoint.next;
            }
        }
        if (newPoint != null) {
            newPoint.next = null;
        } else {
            System.out.println();
            return;
        }

        System.out.println(head);
    }

    @Override
    public ListNode initData() {
        ListNode root = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        //ListNode node4 = new ListNode(3);
        //ListNode node5 = new ListNode(4);
        //ListNode node6 = new ListNode(5);
        root.next = node2;
        node2.next = node3;
        //node3.next = node4;
        //node4.next = node5;
        //node5.next = node6;
        return root;
    }
}