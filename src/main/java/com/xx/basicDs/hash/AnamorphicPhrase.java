package com.xx.basicDs.hash;

import com.xx.Answer;

/**
 * @author 玄霄
 * @CreateDate 2022/8/31
 * 变位词组
 * <p>
 * 给定一组单词，请将它们按照变位词分组。例如，输入
 * 一组单词["eat"，"tea"，"tan"，"ate"，"nat"，"bat"]，这组单
 * 词可以分成3组，分别是["eat"，"tea"，"ate"]、["tan"，"nat"]
 * 和["bat"]。假设单词中只包含英文小写字母。
 */
public class AnamorphicPhrase implements Answer {

    public static void main(String[] args) {
        new AnamorphicPhrase().answerOne();
    }

    /**
     * 同样的，用一个数组来模拟map，或者直接用map。
     * 然后相同的变位词分为一组。
     *
     * 2.先按照子母顺序给每个排序，然后再对整体排序，最后再分组。
     *
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
