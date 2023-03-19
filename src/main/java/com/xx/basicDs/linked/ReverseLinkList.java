package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/22
 * <p>
 * 反转链表
 * <p>
 * 定义一个函数，输入一个链表的头节点，反转该链表并
 * 输出反转后链表的头节点。
 */
public class ReverseLinkList implements Answer {

    public static void main(String[] args) {
        new ReverseLinkList().answerOne();
    }

    /**
     * 反转链表
     */
    @Override
    public void answerOne() {
        ListNode head = initData();
        ListNode point = head;
        ListNode newPoint = null;
        while (point != null) {
            ListNode cur = point;
            point = point.next;
            cur.next = newPoint;
            newPoint = cur;
        }
        System.out.println(newPoint);
    }

    /**
     * something
     */
    @Override
    public ListNode initData() {
        return DataFactory.generateLinkedList();
    }
}
