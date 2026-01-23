package com.xx.basicDs.string;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/9/27
 * <p>
 * 最后一个单词的长度
 * LeetCode 58.  Easy
 * <p>
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 * <p>
 * 示例 1：
 * 输入：s = "Hello World"
 * 输出：5
 * 解释：最后一个单词是“World”，长度为5。
 * <p>
 * 示例 2：
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 * 解释：最后一个单词是“moon”，长度为4。
 * <p>
 * 示例 3：
 * 输入：s = "luffy is still joyboy"
 * 输出：6
 * 解释：最后一个单词是长度为6的“joyboy”。
 * <p>
 * 提示：
 * 1 <= s.length <= 104
 * s 仅有英文字母和空格 ' ' 组成
 * s 中至少存在一个单词
 */
public class TheLengthOfTheLastWord implements Answer {

    public static void main(String[] args) {
        new TheLengthOfTheLastWord().answer();
    }

    @Override
    public void answer() {
        String data = initData();
        boolean end = false;
        int count = 0;
        for (int i = data.length() - 1; i >= 0; i--) {
            char c = data.charAt(i);
            if (c != ' ') {
                count++;
                end = true;
            } else {
                if (end) {
                    break;
                }
            }
        }
        System.out.println(count);
    }

    @Override
    public String initData() {
        return "hello world asd 123";
    }
}