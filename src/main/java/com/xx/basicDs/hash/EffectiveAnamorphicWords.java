package com.xx.basicDs.hash;

import com.xx.Answer;

/**
 * @author 玄霄
 * @CreateDate 2022/8/31
 * 有效的变位词
 * 给定两个字符串s和t，请判断它们是不是一组变位词。
 * 在一组变位词中，它们中的字符及每个字符出现的次数都相同，但
 * 字符的顺序不能相同。例如，"anagram"和"nagaram"就是一组变位
 * 词。
 */
public class EffectiveAnamorphicWords implements Answer {

    public static void main(String[] args) {
        new EffectiveAnamorphicWords().answerOne();
    }

    /**
     * 可以用列表来实现map，此处简易用map替代.key为子母，value为数目
     * 对的上 则就是一组变位词
     * <p>
     * 如果用列表实现，则在是第一组加，第二个字符串处减。
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