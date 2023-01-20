package com.xx.algorithm.sort;

import com.xx.Answer;
import com.xx.domain.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/25
 * 合并排序链表
 * 输入k个排序的链表，请将它们合并成一个排序的链表。
 */
public class MergeSortLinkedList implements Answer {
    public static void main(String[] args) {
        new MergeSortLinkedList().answerOne();
    }

    /**
     * 1.归并排序，每次分一半。
     * 2.采用最小堆，
     */
    @Override
    public void answerOne() {
        // 两两分组

        // 两两汇并
    }

    /**
     * something
     */
    @Override
    public List<ListNode> initData() {
        return new ArrayList<>();
    }
}
