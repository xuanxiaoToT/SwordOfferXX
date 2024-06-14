package com.xx.basicDs.string;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/6/14
 * <p>
 * LeetCode  一次编辑
 * <p>
 * 字符串有三种编辑操作:插入一个英文字符、删除一个英文字符或者替换一个英文字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 * <p>
 * 示例 1:
 * 输入:
 * first = "pale"
 * second = "ple"
 * 输出: True
 * <p>
 * 示例 2:
 * 输入:
 * first = "pales"
 * second = "pal"
 * 输出: False
 * <p>
 * Tag：字符串比较
 */
public class OneTimeEditing implements Answer {

    public static void main(String[] args) {
        new OneTimeEditing().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        String first = "a";
        String second = "ab";
        System.out.println(oneEditAway(first, second));
    }

    /**
     * 易错点：边界条件考虑的太少了
     * 比如:空串时、以及while里的边界条件  要多构造一些测试样例
     */
    public boolean oneEditAway(String first, String second) {
        int abs = Math.abs(first.length() - second.length());
        if (abs >= 2) {
            return false;
        }
        if (abs == 1 && (first.isEmpty() || second.isEmpty())) {
            return true;
        }
        if (first.length() == second.length()) {
            // 相等，是否是替换
            int notEqu = 0;
            for (int i = 0; i < first.length(); i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    notEqu++;
                }
            }
            return notEqu <= 1;
        }
        if (first.length() == second.length() + 1) {
            int i = 0;
            int j = 0;
            int notEqu = 0;
            while (j < second.length() && i < first.length()) {
                if (first.charAt(i) != second.charAt(j)) {
                    notEqu++;
                    i++;
                } else {
                    i++;
                    j++;
                }
            }
            return notEqu <= 1;
        }
        if (first.length() + 1 == second.length()) {
            int i = 0;
            int j = 0;
            int notEqu = 0;
            while (j < second.length() && i < first.length()) {
                if (first.charAt(i) != second.charAt(j)) {
                    notEqu++;
                    j++;
                } else {
                    i++;
                    j++;
                }
            }
            return notEqu <= 1;
        }
        return true;
    }


    /**
     * 其他解法：
     * 1.正向遍历与反向遍历一次，分别取得两者的首个不同的位置，再根据该位置与字符串末尾的距离差值判断是否只有一处不同。
     * 2.一个双指针的思路，思路是如果只有一次编辑，first和second的前缀和后缀应该是相同的，三种情况都是满足这个规律的
     */
    public boolean oneEditAway2(String first, String second) {
        return true;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
