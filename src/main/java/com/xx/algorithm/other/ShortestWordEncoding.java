package com.xx.algorithm.other;

import com.xx.Answer;
import com.xx.basicDs.tree.TrieDemo;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/13
 * 最短的单词编码
 * 输入一个包含n个单词的数组，可以把它们编码成一个字
 * 符串和n个下标。例如，单词数组["time"，"me"，"bell"]可以编码
 * 成一个字符串"time＃bell＃"，然后这些单词就可以通过下标[0，
 * 2，5]得到。对于每个下标，都可以从编码得到的字符串中相应的位
 * 置开始扫描，直到遇到'＃'字符前所经过的子字符串为单词数组中
 * 的一个单词。例如，从"time＃bell＃"下标为2的位置开始扫描，直
 * 到遇到'＃'前经过子字符串"me"是给定单词数组的第2个单词。给定
 * 一个单词数组，请问按照上述规则把这些单词编码之后得到的最短
 * 字符串的长度是多少？
 *
 * 例如：如果输入的是字符串数组
 * ["time"，"me"，"bell"]，那么编码之后最短的字符串是"time＃
 * bell＃"，长度是10。
 */
public class ShortestWordEncoding implements Answer {
    public static void main(String[] args) {
        new ShortestWordEncoding().answerOne();
    }

    /**
     * 转换为逆向的前缀树。然后计算每个分之的长度即可。
     */
    @Override
    public void answerOne() {
        TrieDemo trie = new TrieDemo();
        String[] data = initData();
        for (String datum : data) {
            String reverse = new StringBuffer(datum).reverse().toString();
            trie.insert(reverse);
        }
        System.out.println(trie.root);
        // 计算每个分之长度，然后+分之数即可。 略
    }

    /**
     * something
     */
    @Override
    public String[] initData() {
        return new String[]{"time", "me", "bell"};
    }
}
