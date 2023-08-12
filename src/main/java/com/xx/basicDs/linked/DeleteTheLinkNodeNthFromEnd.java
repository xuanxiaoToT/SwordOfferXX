package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2023/8/12
 * <p>
 * 删除链表的倒数第 N 个结点
 * LeetCode 19.
 * <p>
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * <p>
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 */
public class DeleteTheLinkNodeNthFromEnd implements Answer {

    public static void main(String[] args) {
        new DeleteTheLinkNodeNthFromEnd().answerOne();
    }

    /**
     * 解1：快慢指针。定位到倒数第N个节点即可。
     * 注意使用哨兵节点，这样可以更好操作。
     */
    @Override
    public void answerOne() {
        ListNode listNode = initData();
        System.out.println(listNode);
        ListNode result = removeNthFromEnd(listNode, 2);
        System.out.println(result);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        //哨兵节点
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    /**
     * 输出数据
     */
    @Override
    public ListNode initData() {
        return DataFactory.generateLinkedList();
    }
}
