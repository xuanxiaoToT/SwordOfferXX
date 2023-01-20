package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/16
 * Penultimate：倒数第二的
 * 删除倒数第k个节点
 * <p>
 * 如果给定一个链表，请问如何删除链表中的倒数第k个节
 * 点？假设链表中节点的总数为n，那么1≤k≤n。要求只能遍历链表
 * 一次。
 */
public class DeleteThePenultimateNode implements Answer {
    public static void main(String[] args) {
        new DeleteThePenultimateNode().answerOne();
    }

    /**
     * 用哨兵机制，可以避免判空等复杂操作。
     *
     * 双指针：第一个先走K步，第二个再开始走。
     */
    @Override
    public void answerOne() {
        int k = 2;
        ListNode node = initData();
        System.out.println(node);
        if (node == null) {
            return;
        }
        ListNode left = node;
        ListNode right = left;
        for (int i = 0; i < k; i++) {
            if (right == null) {
                System.out.println("不存在倒数第K个节点");
            }
            right = right.next;
        }

        // 删的是第一个节点。
        if (right == null) {
            node = node.next;
            System.out.println(node);
            return;
        } else {
            // 要删除第K个，left需要跑到第k+1。也就是前面。
            right = right.next;
        }

        while (right != null) {
            right = right.next;
            left = left.next;
        }

        // 删除
        if (left.next != null) {
            left.next = left.next.next;
        } else {
            left.next = null;
        }

        System.out.println(node);
    }

    /**
     * something
     */
    @Override
    public ListNode initData() {
        return DataFactory.generateLinkedList();
    }
}