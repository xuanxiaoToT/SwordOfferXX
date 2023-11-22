package com.xx.algorithm.dynamicProgram.简单DP;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/1
 * <p>
 * 翻转字符
 * <p>
 * 输入一个只包含'0'和'1'的字符串，其中，'0'可以翻转
 * 成'1'，'1'可以翻转成'0'。请问至少需要翻转几个字符，才可以使
 * 翻转之后的字符串中所有的'0'位于'1'的前面？翻转之后的字符串
 * 可能只包含字符'0'或'1'。
 * <p>
 * 例如，输入字符串"00110"，至少需要翻
 * 转一个字符才能使所有的'0'位于'1'的前面。可以将最后一个字
 * 符'0'翻转成'1'，得到字符串"00111"。
 */
public class FlipCharacter implements Answer {

    public static void main(String[] args) {
        new FlipCharacter().answerOne();
    }

    /**
     * 1.对每个下标都进行计算，如果该下标的左边全为0，右边全为1，那么该翻转多少。
     * 遍历完求得最小值即可。
     */
    @Override
    public void answerOne() {
        String data = initData();
        int[] dp = new int[data.length()];
        int count1 = computeCharCount(data, '1');
        int count0 = 0;
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '0') {
                // 从0变1
                dp[i] = i - count0 + data.length() - 1 - i - count1 + 1;
                count0++;
            } else {
                count1--;
                // 左侧1全改为0，右侧0全改为1
                int temp = i - count0 + data.length() - 1 - i - count1;
                dp[i] = temp;
            }
        }

        System.out.println(Arrays.toString(dp));
    }


    /**
     * 1.函数f(i)表示把字符串中从下标为0的字符到下标为i的字符（记为S[0..i]，字符串中前i+1个字符组成的子字符串）变成符合要求的字符串
     * 并且最后一个字符是'0'所需要的最少翻转次数。
     * 2.函数g(i)表示最后一个字符串是1 所需要的最少反转次数。
     */
    public void answerTwo() {
        String data = initData();
        int[] dpF = new int[data.length()];
        int[] dpG = new int[data.length()];
        if (data.charAt(0) == '0') {
            dpF[0] = 0;
            dpG[0] = 1;
        } else {
            dpF[0] = 1;
            dpG[0] = 0;
        }

        for (int i = 1; i < data.length(); i++) {
            if (data.charAt(i) == '1') {
                // 把1翻为0;
                dpF[i] = dpF[i - 1] + 1;
                dpG[i] = dpG[i - 1];
            } else {
                dpF[i] = dpF[i - 1];
                // 把0翻为1
                dpG[i] = dpG[i - 1] + 1;
            }
        }
    }

    private int computeCharCount(String str, Character character) {
        int result = 0;
        for (char c : str.toCharArray()) {
            if (character.equals(c)) {
                result++;
            }
        }
        return result;
    }

    /**
     * something
     */
    @Override
    public String initData() {
        return "111000";
    }
}
