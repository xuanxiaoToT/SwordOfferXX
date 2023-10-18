package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/18
 * <p>
 * 分隔链表
 * LeetCode 86. Medium
 * <p>
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * <p>
 * 示例 1：
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * <p>
 * 示例 2：
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 * <p>
 * 提示：
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */
public class SeparatedLinkedList implements Answer {

    public static void main(String[] args) {
        new SeparatedLinkedList().answerOne();
    }

    /**
     * 解1：直观来说我们只需维护两个链表 small 和 large 即可，small 链表按顺序存储所有小于 x的节点，
     * large 链表按顺序存储所有大于等于 x 的节点。遍历完原链表后，
     * 我们只要将 small 链表尾节点指向 large 链表的头节点即能完成对链表的分隔。
     */
    @Override
    public void answerOne() {
        ListNode head = initData();
        int target = 3;
        ListNode point = head;
        ListNode newHeadLess = null;
        ListNode lessPoint = null;
        ListNode newHeadGreater = null;
        ListNode greaterPoint = null;
        while (point != null) {
            if (point.value < target) {
                if (newHeadLess == null) {
                    newHeadLess = point;
                    lessPoint = newHeadLess;
                } else {
                    lessPoint.next = point;
                    lessPoint = lessPoint.next;
                }
            } else {
                if (newHeadGreater == null) {
                    newHeadGreater = point;
                    greaterPoint = newHeadGreater;
                } else {
                    greaterPoint.next = point;
                    greaterPoint = greaterPoint.next;
                }
            }
            point = point.next;
        }
        if (greaterPoint != null) {
            greaterPoint.next = null;
        }
        if (lessPoint != null) {
            lessPoint.next = newHeadGreater;
        }
        if (newHeadLess != null) {
            System.out.println(newHeadLess);
        } else {
            System.out.println(head);
        }
    }

    /**
     * 输出数据
     */
    @Override
    public ListNode initData() {
        ListNode root = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(2);
        root.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        return root;
    }
}
