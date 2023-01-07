package com.xx.basicDs.string;

import com.xx.Answer;

/**
 * @author 玄霄
 * @CreateDate 2022/7/13
 * 第一个只出现一次的字符
 * 在字符串 s 中找出 第一个 只出现一次 的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 * <p>
 * 示例 1:
 * 输入：s = "abaccdeff"
 * 输出：'b'
 * <p>
 * 示例 2:
 * 输入：s = ""
 * 输出：' '
 */
public class FirstCharacterAppearsOnce implements Answer {

    public static void main(String[] args) {
        new FirstCharacterAppearsOnce().answerOne();
    }

    @Override
    public void answerOne() {
        //复杂度O(2N)
        charCountDto[] numRecord = new charCountDto[26];
        String oriStr = initData();

        for (int i = 0; i < oriStr.length(); i++) {
            char c = oriStr.charAt(i);
            int index = c - 'a';
            if (numRecord[index] == null) {
                numRecord[index] = new charCountDto(1, i);
            } else {
                numRecord[index].count++;
            }
        }
        Integer min = null;
        for (charCountDto dto : numRecord) {
            if (dto != null && dto.count == 1) {
                if (min == null) {
                    min = dto.charIndex;
                } else {
                    if (dto.charIndex < min) {
                        min = dto.charIndex;
                    }
                }
            }
        }
        if (min == null) {
            System.out.println("  ");
        } else {
            System.out.println(oriStr.charAt(min));
        }
    }

    @Override
    public String initData() {
        return "abaccdeff";
    }


    private class charCountDto {
        public Integer count;
        public Integer charIndex;

        public charCountDto(int count, int charIndex) {
            this.count = count;
            this.charIndex = charIndex;
        }
    }

}