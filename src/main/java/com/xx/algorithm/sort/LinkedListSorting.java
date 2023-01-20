package com.xx.algorithm.sort;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/25
 * 链表排序
 * 输入一个链表的头节点，请将该链表排序。例如，输入
 * 图12.4（a）中的链表，该链表排序后如图12.4（b）所示。
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