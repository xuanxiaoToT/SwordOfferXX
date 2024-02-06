package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.DataFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/6
 * <p>
 * 链表最大孪生和
 * LeetCode 2130. Medium
 * <p>
 * 在一个大小为 n 且 n 为 偶数 的链表中，对于 0 <= i <= (n / 2) - 1 的 i ，第 i 个节点（下标从 0 开始）的孪生节点为第 (n-1-i) 个节点 。
 * 比方说，n = 4 那么节点 0 是节点 3 的孪生节点，节点 1 是节点 2 的孪生节点。这是长度为 n = 4 的链表中所有的孪生节点。
 * 孪生和 定义为一个节点和它孪生节点两者值之和。
 * 给你一个长度为偶数的链表的头节点 head ，请你返回链表的 最大孪生和 。
 * <p>
 * 示例 1：
 * 输入：head = [5,4,2,1]
 * 输出：6
 * 解释：
 * 节点 0 和节点 1 分别是节点 3 和 2 的孪生节点。孪生和都为 6 。
 * 链表中没有其他孪生节点。
 * 所以，链表的最大孪生和是 6 。
 * <p>
 * 示例 2：
 * 输入：head = [4,2,2,3]
 * 输出：7
 * 解释：
 * 链表中的孪生节点为：
 * - 节点 0 是节点 3 的孪生节点，孪生和为 4 + 3 = 7 。
 * - 节点 1 是节点 2 的孪生节点，孪生和为 2 + 2 = 4 。
 * 所以，最大孪生和为 max(7, 4) = 7 。
 * <p>
 * 示例 3：
 * 输入：head = [1,100000]
 * 输出：100001
 * 解释：
 * 链表中只有一对孪生节点，孪生和为 1 + 100000 = 100001 。
 * <p>
 * 提示：
 * 链表的节点数目是 [2, 10^5] 中的 偶数 。
 * 1 <= Node.val <= 10^5
 * <p>
 * Tag:链表逆置
 */
public class MaximumTwinSumOfLinkList implements Answer {
    public static void main(String[] args) {
        new MaximumTwinSumOfLinkList().answerOne();
    }

    @Override
    public void answerOne() {
        ListNode listNode = DataFactory.generateLinkedList();
        System.out.println(maximumTwinSum2(listNode));
    }

    /**
     * 借用临时表
     */
    public int maximumTwinSum(ListNode head) {
        List<Integer> intList = new ArrayList<>();
        ListNode point = head;
        while (point != null) {
            intList.add(point.val);
            point = point.next;
        }
        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = intList.size() - 1;
        while (left < right) {
            int temp = intList.get(left) + intList.get(right);
            max = Math.max(temp, max);
            left++;
            right--;
        }
        return max;
    }

    /**
     * 不用额外空间的方法，先逆置
     */
    public int maximumTwinSum2(ListNode head) {
        ListNode slow = head;
        ListNode quick = head.next;
        while (quick != null && quick.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        ListNode right = slow.next;
        slow.next = null;
        right = reverseLinkList(right);
        ListNode point = head;
        int max = Integer.MIN_VALUE;
        while (point != null && right != null) {
            max = Math.max(point.val + right.val, max);
            point = point.next;
            right = right.next;
        }
        return max;
    }

    public ListNode reverseLinkList(ListNode head) {
        ListNode point = head;
        ListNode last = null;
        while (point != null) {
            ListNode cur = point;
            point = point.next;
            cur.next = last;
            last = cur;
        }
        return last;
    }


    @Override
    public Object initData() {
        return null;
    }
}