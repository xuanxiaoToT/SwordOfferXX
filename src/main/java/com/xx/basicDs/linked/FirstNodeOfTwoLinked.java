package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2022/7/14
 * 编写一个程序，找到两个单链表相交的起始节点。
 */
public class FirstNodeOfTwoLinked implements Answer {

    public static void main(String[] args) {
        new FirstNodeOfTwoLinked().answerOne();
    }

    /**
     * 长度法
     * 求出两个链表的长度，然后计算差值。
     */
    @Override
    public void answerOne() {
        ListNode root1 = initData();
        ListNode root2 = initData();
        int len1 = findLinkLength(root1);
        int len2 = findLinkLength(root2);
        System.out.println(len1);
        // if (len2 > len1) {
        //     int p = 0;
        //     ListNode r2 = root2;
        //     int n = len2 - len1;
        //     //  先走n步，最后一起。
        // }

    }

    private int findLinkLength(ListNode node) {
        if (node == null) {
            return 0;
        }
        int len = 1;
        ListNode point = node;
        while (point != null) {
            point = point.next;
            len++;
        }
        return len - 1;
    }

    @Override
    public ListNode initData() {
        return DataFactory.generateLinkedList();
    }
}