package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.DataFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @author XuanXiao
 * @CreateDate 2024/6/29
 * <p>
 * 移除重复节点
 * LeetCode Easy
 * <p>
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 * <p>
 * 示例1:
 * 输入：[1, 2, 3, 3, 2, 1]
 * 输出：[1, 2, 3]
 * <p>
 * 示例2:
 * 输入：[1, 1, 1, 1, 2]
 * 输出：[1, 2]
 * <p>
 * 提示：
 * 链表长度在[0, 20000]范围内。
 * 链表元素在[0, 20000]范围内。
 * <p>
 * 进阶：
 * 如果不得使用临时缓冲区，该怎么解决？
 */
public class RemoveDuplicateNode implements Answer {

    public static void main(String[] args) {
        new RemoveDuplicateNode().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        ListNode listNode = DataFactory.generateLinkedList();
        System.out.println(removeDuplicateNodes(listNode));
    }

    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        Set<Integer> set = new HashSet<>();
        ListNode newHead = head;
        ListNode point = head;
        ListNode p = head.next;
        set.add(head.val);
        while (p != null) {
            if (!set.contains(p.val)) {
                point.next = p;
                point = point.next;
                set.add(p.val);
            }
            p = p.next;
        }
        point.next = null;
        return newHead;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
