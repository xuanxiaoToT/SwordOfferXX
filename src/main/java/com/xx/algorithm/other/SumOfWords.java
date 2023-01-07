package com.xx.algorithm.other;

import com.xx.Answer;
import com.xx.basicDs.tree.TrieDemo;

/**
 * @author 玄霄
 * @CreateDate 2022/10/13
 * <p>
 * 单词之和
 * <p>
 * 请设计实现一个类型MapSum，它有如下两个操作。
 * ● 函数insert，输入一个字符串和一个整数，在数据集合中添加一个
 * 字符串及其对应的值。如果数据集合中已经包含该字符串，则将该
 * 字符串对应的值替换成新值。
 * ● 函数sum，输入一个字符串，返回数据集合中所有以该字符串为
 * 前缀的字符串对应的值之和。
 * <p>
 * 例如，第1次调用函数insert添加字符串"happy"和它的值3，此时
 * 如果输入"hap"调用函数sum则返回3。第2次调用函数insert添加字符
 * 串"happen"和它的值2，此时如果输入"hap"调用函数sum则返回5。
 */
public class SumOfWords implements Answer {

    public static void main(String[] args) {
        new SumOfWords().answerOne();
    }

    /**
     * 用前缀树来解决，但是最后的flag为true时，还要同时保留他的value值，或者将此value值保存到一个map中也可以。
     * 然后用前缀求sum时，只需要在其结束处遍历所有的子节点即可。
     */
    @Override
    public void answerOne() {
        TrieDemo trie = new TrieDemo();
        // 以下略
    }

    private void insert(String word, int num) {

    }

    private int sum(String word) {
        return 0;
    }

    /**
     * something
     */
    @Override
    public Object initData() {
        return null;
    }
}
