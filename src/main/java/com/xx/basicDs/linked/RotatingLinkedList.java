package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2023/3/19
 * <p>
 * 旋转链表
 * LeetCode 061
 * <p>
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * <p>
 * 示例 2：
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 */
public class RotatingLinkedList implements Answer {

    public static void main(String[] args) {
        new RotatingLinkedList().answer();
    }

    /**
     * 解1：找到倒数K个节点，然后接到头结点上即可。
     * 找倒数第K个，用快慢指针。需要先遍历一遍求出length，然后取余
     */
    @Override
    public void answer() {
        //int K = 2;
    }

    /**
     * 先弄成环，然后再拆除环
     */
    private ListNode answerTwo(int k) {
        ListNode head = initData();
        // 预处理阶段
        if (k == 0 || head == null || head.next == null) {
            return head;
        }

        int listLength = 1, Kth = 0;
        ListNode last = head, first;
        // 找到链表尾节点，并计算链表长度
        while (last.next != null) {
            listLength++;
            last = last.next;
        }

        // (n-1)-(k%n)
        Kth = listLength - (k % listLength);
        if (Kth == listLength) return head;
        // 构建环
        last.next = head;
        // 根据公式计算出新链表的头节点位置并拆解环
        while (Kth-- > 0) last = last.next;
        first = last.next;
        last.next = null;
        return first;
    }

    /**
     * 输出数据
     */
    @Override
    public ListNode initData() {
        return DataFactory.generateLinkedList();
    }
}
