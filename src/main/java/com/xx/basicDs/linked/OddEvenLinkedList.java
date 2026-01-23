package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/6
 * <p>
 * 奇偶链表
 * LeetCode 328. Medium
 * <p>
 * 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
 * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
 * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
 * 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
 * <p>
 * 示例 1:
 * 输入: head = [1,2,3,4,5]
 * 输出: [1,3,5,2,4]
 * <p>
 * 示例 2:
 * 输入: head = [2,1,3,5,6,4,7]
 * 输出: [2,3,6,7,1,5,4]
 * <p>
 * 提示:
 * n ==  链表中的节点数
 * 0 <= n <= 10^4
 * -10^6 <= Node.val <= 10^6
 * <p>
 * Tag：双指针
 */
public class OddEvenLinkedList implements Answer {

    public static void main(String[] args) {
        new OddEvenLinkedList().answer();
    }

    @Override
    public void answer() {
        //ListNode listNode = DataFactory.generateLinkedList();
        ListNode listNode = new ListNode(5, null);
        System.out.println(oddEvenList(listNode));
    }

    /**
     * 注意最后一个节点，可能会出现两边都next指着的情况
     * 此时需要断开
     */
    public ListNode oddEvenList(ListNode head) {
        ListNode even = null;
        ListNode odd = null;
        ListNode pOdd = odd;
        ListNode pEven = even;
        ListNode point = head;
        int index = 1;
        while (point != null) {
            if (index % 2 == 0) {
                if (even == null) {
                    even = point;
                    pEven = even;
                } else {
                    pEven.next = point;
                    pEven = point;
                }
            } else {
                if (odd == null) {
                    odd = point;
                    pOdd = odd;
                } else {
                    pOdd.next = point;
                    pOdd = point;
                }
            }
            point = point.next;
            if (point == null && pEven != null) {
                pOdd.next = even;
                pEven.next = null;
            }
            index++;
        }
        return odd;
    }

    @Override
    public Object initData() {
        return null;
    }
}