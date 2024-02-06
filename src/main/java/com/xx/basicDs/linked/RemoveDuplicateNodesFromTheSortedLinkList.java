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
            if (point.val != pre.val) {
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

    /**
     * 官解：我们从指针 cur 指向链表的哑节点，随后开始对链表进行遍历。如果当前 cur.next 与 cur.next.next 对应的元素相同，
     * 那么我们就需要将 cur.next 以及所有后面拥有相同元素值的链表节点全部删除。我们记下这个元素值 x，随后不断将 cur.next 从链表中移除，
     * 直到 cur.next 为空节点或者其元素值不等于 x 为止。此时，我们将链表中所有元素值为 x 的节点全部删除。
     * 如果当前 cur.next对应的元素不相同，那么说明链表中只有一个元素值为 cur.next 的节点，那么我们就可以将 cur 指向 cur.next。
     * <p>
     * 当遍历完整个链表之后，我们返回链表的的哑节点的下一个节点 dummy.next 即可。
     * 链接：https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);

        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
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