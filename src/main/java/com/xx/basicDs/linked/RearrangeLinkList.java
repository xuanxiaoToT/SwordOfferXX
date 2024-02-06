package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.ArrayUtil;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/24
 * <p>
 * 重排链表
 * <p>
 * 给定一个链表，链表中节点的顺序是L0→L1→L2→… →Ln-1→Ln，
 * 请问如何重排链表使节点的顺序变成L0→Ln→L1→Ln-1→L2→Ln-2→…？
 */
public class RearrangeLinkList implements Answer {
    public static void main(String[] args) {
        new RearrangeLinkList().answerOne();
    }

    /**
     * 思路：先反转一半
     * 然后拼接即可
     */
    @Override
    public void answerOne() {
        ListNode head = initData();
        ListNode midNode = findLinkMidNode(head);
        System.out.println(midNode.val);

        ListNode newReverseNode = ArrayUtil.reverseLinkList(midNode);

        ListNode newHead = null;
        ListNode newPoint = newHead;
        ListNode leftPoint = head;
        ListNode rightPoint = newReverseNode;
        while (leftPoint != null && rightPoint != null) {
            if (newPoint == null) {
                newPoint = leftPoint;
                newHead = newPoint;
                leftPoint = leftPoint.next;
                newPoint.next = rightPoint;
                rightPoint = rightPoint.next;
                newPoint = newPoint.next;
            } else {
                newPoint.next = leftPoint;
                leftPoint = leftPoint.next;
                newPoint = newPoint.next;
                newPoint.next = rightPoint;
                rightPoint = rightPoint.next;
                newPoint = newPoint.next;
            }
            System.out.println(newHead);
        }
        if (leftPoint != null) {
            newPoint.next = leftPoint;
        }
        if (rightPoint != null) {
            newPoint.next = rightPoint;
        }

        System.out.println(newHead);
    }

    private ListNode findLinkMidNode(ListNode node) {
        ListNode quick = node;
        ListNode slow = node;
        while (quick != null) {
            quick = quick.next;
            if (quick != null) {
                quick = quick.next;
            } else {
                break;
            }
            slow = slow.next;
        }
        return slow;
    }

    /**
     * something
     */
    @Override
    public ListNode initData() {
        return DataFactory.generateLinkedList();
    }
}