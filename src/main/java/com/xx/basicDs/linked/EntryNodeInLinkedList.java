package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.DataFactory;

/**
 * @author 玄霄
 * @CreateDate 2022/8/16
 * 链表中 环的入口节点
 * <p>
 * 如果一个链表中包含环，那么应该如何找出环的入口节
 * 点？从链表的头节点开始顺着next指针方向进入环的第1个节点为环
 * 的入口节点。
 */
public class EntryNodeInLinkedList implements Answer {

    public static void main(String[] args) {
        new EntryNodeInLinkedList().answerOne();
    }

    /**
     * 1.快慢法，快的每次走两格，慢的走一格，最后能追上。能追上只能证明存在环(还需要知晓环的大小。)
     * 2.用map，比较花费空间。
     */
    @Override
    public void answerOne() {
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
        System.out.println(quick.value);
        // 求环的大小l

        // 双指针，快的先走l步，最后会跟慢的在入口处相遇。
    }

    /**
     * something
     */
    @Override
    public ListNode initData() {
        return DataFactory.generateRingLinkedList();
    }
}