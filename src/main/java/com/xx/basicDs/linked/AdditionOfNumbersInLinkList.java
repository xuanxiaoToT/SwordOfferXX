package com.xx.basicDs.linked;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/24
 * 链表中的数字相加
 * <p>
 * 给定两个表示非负整数的单向链表，请问如何实现这两
 * 个整数的相加并且把它们的和仍然用单向链表表示？链表中的每个
 * 节点表示整数十进制的一位，并且头节点对应整数的最高位数而尾
 * 节点对应整数的个位数。
 */
public class AdditionOfNumbersInLinkList implements Answer {
    public static void main(String[] args) {
        new AdditionOfNumbersInLinkList().answerOne();
    }

    /**
     * 注意点：涉及到整数相加的这种题目，一定要考虑溢出的问题。
     * 所以可以先遍历，求出两个string，然后按照string相加的方式进行计算。
     */
    @Override
    public void answerOne() {
        //省略
    }

    /**
     * 先反转链表，再进行相加计算。
     */
    public void answerTwo() {

    }

    /**
     * something
     */
    @Override
    public Object initData() {
        return null;
    }
}
