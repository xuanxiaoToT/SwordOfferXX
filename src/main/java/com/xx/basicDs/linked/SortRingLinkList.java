package com.xx.basicDs.linked;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/30
 * 对循环链表进行排序
 * 在一个循环链表中节点的值递增排序，请设计一个算法
 * 在该循环链表中插入节点，并保证插入节点之后的循环链表仍然是
 * 排序的。
 */
public class SortRingLinkList implements Answer {
    /**
     * 1.插入的值比当前节点大，比后一个节点小。直接插入在此节点之后。
     * 2.插入的值比当前节点大，比下一个也大，直接插入在此节点之后。
     * 3.插入的值比当前值小，则判断下一个值是否比当前值小，如果是则插入。
     * 可以遍历两次：第一次找到交接点，也就是头结点(最小值)。第二次遍历再正式插入
     */
    @Override
    public void answerOne() {

    }

    /**
     * something
     */
    @Override
    public Object initData() {
        return null;
    }
}