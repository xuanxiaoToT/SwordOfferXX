package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/24
 * 回文链表
 * 如何判断一个链表是不是回文？要求解法的时间复杂度
 * 是O（n），并且不得使用超过O（1）的辅助空间。如果一个链表是
 * 回文，那么链表的节点序列从前往后看和从后往前看是相同的。
 */
public class PalindromeLinkList implements Answer {

    public static void main(String[] args) {
        new PalindromeLinkList().answerOne();
    }

    /**
     * 思路：将后半段连表逆置。然后比较。一共O(3n)也是O(n)
     */
    @Override
    public void answerOne() {

    }

    /**
     * something
     */
    @Override
    public ListNode initData() {
        return DataFactory.generateLinkedList();
    }
}