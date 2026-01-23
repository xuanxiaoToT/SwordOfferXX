package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.DataFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/16
 * <p>
 * 链表中环的入口节点
 * LeetCode 142. 环形链表 II
 * <p>
 * 如果一个链表中包含环，那么应该如何找出环的入口节点？
 * 从链表的头节点开始顺着next指针方向进入环的第1个节点为环
 * 的入口节点。
 */
public class EntryNodeInLinkedList implements Answer {

    public static void main(String[] args) {
        new EntryNodeInLinkedList().answer();
    }

    /**
     * 1.快慢法，快的每次走两格，慢的走一格，最后能追上。能追上只能证明存在环(还需要知晓环的大小。)
     */
    @Override
    public void answer() {
        ListNode head = initData();
        ListNode quick = head;
        ListNode slow = head;
        do {
            quick = quick.next.next;
            slow = slow.next;
            if (quick == null || slow == null) {
                System.out.println("error~!");
                return;
            }
        } while (slow != quick);
        System.out.println(quick.val);
        // 求环的大小l

        // 双指针，快的先走l步，最后会跟慢的在入口处相遇。
    }

    /**
     * 直接用set做。
     */
    public void answerTwo() {
        Set<ListNode> visited = new HashSet<ListNode>();

        ListNode node = initData();
        while (node != null) {
            if (visited.contains(node)) {
                System.out.println(node);
                return;
            }
            visited.add(node);
            node = node.next;
        }
    }

    /**
     * something
     */
    @Override
    public ListNode initData() {
        return DataFactory.generateRingLinkedList();
    }
}