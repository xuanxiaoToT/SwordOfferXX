package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/6
 * <p>
 * 删除链表的中间节点
 * LeetCode 2095. Medium
 * <p>
 * 给你一个链表的头节点 head 。删除 链表的 中间节点 ，并返回修改后的链表的头节点 head 。
 * 长度为 n 链表的中间节点是从头数起第 ⌊n / 2⌋ 个节点（下标从 0 开始），其中 ⌊x⌋ 表示小于或等于 x 的最大整数。
 * 对于 n = 1、2、3、4 和 5 的情况，中间节点的下标分别是 0、1、1、2 和 2 。
 * <p>
 * 示例 1：
 * 输入：head = [1,3,4,7,1,2,6]
 * 输出：[1,3,4,1,2,6]
 * 解释：
 * 上图表示给出的链表。节点的下标分别标注在每个节点的下方。
 * 由于 n = 7 ，值为 7 的节点 3 是中间节点，用红色标注。
 * 返回结果为移除节点后的新链表。
 * <p>
 * 示例 2：
 * 输入：head = [1,2,3,4]
 * 输出：[1,2,4]
 * 解释：
 * 上图表示给出的链表。
 * 对于 n = 4 ，值为 3 的节点 2 是中间节点，用红色标注。
 * <p>
 * 示例 3：
 * 输入：head = [2,1]
 * 输出：[2]
 * 解释：
 * 上图表示给出的链表。
 * 对于 n = 2 ，值为 1 的节点 1 是中间节点，用红色标注。
 * 值为 2 的节点 0 是移除节点 1 后剩下的唯一一个节点。
 * <p>
 * 提示：
 * 链表中节点的数目在范围 [1, 10^5] 内
 * 1 <= Node.val <= 10^5
 */
public class DeleteMidNodeInLinkedList implements Answer {

    public static void main(String[] args) {
        new DeleteMidNodeInLinkedList().answerOne();
    }

    @Override
    public void answerOne() {
        ListNode root = initData();
        System.out.println(removeMidNode(root));
    }

    /**
     * 最简单的，两次遍历。计数，
     */
    public ListNode removeMidNode(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        int length = 1;
        ListNode point = head;
        while (point != null) {
            point = point.next;
            if (point != null) {
                length++;
            }
        }
        if (length == 2) {
            head.next = null;
            return head;
        }

        int mid = length / 2;
        ListNode pre = head;
        int preInx = 1;
        while (preInx != mid) {
            pre = pre.next;
            preInx++;
        }
        ListNode newNext = pre.next.next;
        pre.next = newNext;
        return head;
    }

    /**
     * 快慢指针法
     */
    public ListNode deleteMiddle(ListNode head) {
        if (head.next == null) {
            return null;
        }

        ListNode fast = head, slow = head;
        ListNode pre = new ListNode();
        pre.next = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }

        pre.next = slow.next;
        return head;
    }


    @Override
    public ListNode initData() {
        return DataFactory.generateLinkedList();
    }
}