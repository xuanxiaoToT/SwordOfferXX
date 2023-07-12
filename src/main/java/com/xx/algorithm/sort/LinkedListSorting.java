package com.xx.algorithm.sort;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/25
 * <p>
 * 链表排序
 * LeetCode 148. 排序链表
 * <p>
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * <p>
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * <p>
 * 示例 2：
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * <p>
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 */
public class LinkedListSorting implements Answer {

    public static void main(String[] args) {
        new LinkedListSorting().answerTwo();
    }

    /**
     * 1.交换排序，若前比后大，则进行交换。循环N次。时间复杂O(N2)
     * 2.选择排序，每次选个最小的放前面即可。
     */
    @Override
    public void answerOne() {
        // 略
    }

    /**
     * 采用归并排序。
     * 问题：怎么快速找到其中间点？快慢指针！
     */
    public void answerTwo() {
        ListNode node = initData();
        ListNode node1 = sortList(node);
        System.out.println(node1);
    }

    public ListNode sortList(ListNode root) {
        if (root == null || root.next == null) {
            return root;
        }

        ListNode mid = split(root);
        ListNode head1 = sortList(root);
        ListNode head2 = sortList(mid);
        return mergeList(head1, head2);
    }

    private ListNode mergeList(ListNode head1, ListNode head2) {
        ListNode newRoot = null;
        ListNode p = null;
        while (head1 != null && head2 != null) {
            if (head1.value < head2.value) {
                if (p == null) {
                    p = head1;
                    newRoot = p;
                } else {
                    p.next = head1;
                    p = p.next;
                }
                head1 = head1.next;
            } else {
                if (p == null) {
                    p = head2;
                    newRoot = p;
                } else {
                    p.next = head2;
                    p = p.next;
                }
                head2 = head2.next;
            }

        }
        if (head2 != null) {
            head1 = head2;
        }
        while (head1 != null) {
            p.next = head1;
            p = p.next;
            head1 = head1.next;
        }
        return newRoot;
    }

    //快慢指针.同时直接把链断开
    private ListNode split(ListNode root) {
        ListNode slow = root;
        ListNode quick = root.next;
        while (quick != null && quick.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        ListNode result = slow.next;
        slow.next = null;
        //既mid+1
        return result;
    }

    /**
     * something
     */
    @Override
    public ListNode initData() {
        return DataFactory.generateLinkedList();
    }
}