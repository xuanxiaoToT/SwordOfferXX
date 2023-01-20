package com.xx.basicDs.number;

import com.xx.Answer;
import com.xx.util.NumberUtil;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/2
 * <p>
 * 单词长度的最大乘积
 * <p>
 * 输入一个字符串数组words，请计算不包含相同字符的两
 * 个字符串words[i]和words[j]的长度乘积的最大值。
 * 如果所有字符串都包含至少一个相同字符，那么返回0。
 * 假设字符串中只包含英文小写字母。
 * 例如，输入的字符串数组words为 ["abcw"，"foo"，"bar"，"fxyz"，"abcdef"]，数组中的字符
 * 串"bar"与"foo"没有相同的字符，它们长度的乘积为9。"abcw"与"fxyz"也没有相同的字符，它们长度的乘积为16，这是
 * 该数组不包含相同字符的一对字符串的长度乘积的最大值。
 */
public class MaximumProductWordLength implements Answer {

    public static void main(String[] args) {
        new MaximumProductWordLength().answerOne();
    }

    /**
     * 1.最简单的两两遍历，并计算 O(N2)
     */
    @Override
    public void answerOne() {
        int max = 0;
        String[] data = initData();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (isCommon(data[i], data[j])) {
                    if (max < data[i].length() * data[j].length()) {
                        max = data[i].length() * data[j].length();
                    }
                }
            }
        }
        System.out.println(max);
    }

    // 此题的关键是判断两个字符串是否拥有相同字母。
    private boolean isCommon(String str1, String str2) {
        //方法1，直接用hashMap实现

        //方法2：用长度26的数组来作为map

        //方法3：二进制表示：可以用一个int型整数记录某个字符串中
        // 出现的字符。如果字符串中包含'a'，那么整数最右边的数位为1；如
        // 果字符串中包含'b'，那么整数的倒数第2位为1，其余以此类推。这样
        // 做的好处是能更快地判断两个字符串是否包含相同的字符。如果两个
        // 字符串中包含相同的字符，那么它们对应的整数相同的某个数位都为
        // 1，两个整数的与运算将不会等于0。如果两个字符串没有相同的字
        // 符，那么它们对应的整数的与运算的结果等于0。
        int bitFlag1 = 0;
        int bitFlag2 = 0;
        for (char c : str1.toCharArray()) {
            bitFlag1 = NumberUtil.setBit(bitFlag1, c - 'a');
        }
        for (char c : str2.toCharArray()) {
            bitFlag2 = NumberUtil.setBit(bitFlag2, c - 'a');
        }
        return (bitFlag1 & bitFlag2) == 0;
    }

    /**
     * something
     */
    @Override
    public String[] initData() {
        return new String[]{"abcw", "foo", "bar", "fxyz", "abcdef"};
    }
}