package com.xx.basicDs.number.位运算;

import com.xx.Answer;
import com.xx.util.NumberUtil;

/**
 * @author XuanXiao
 * @CreateDate 2024/4/11
 * <p>
 * 判定字符是否唯一
 * LeetCode
 * <p>
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 * <p>
 * 示例 1：
 * 输入: s = "leetcode"
 * 输出: false
 * <p>
 * 示例 2：
 * 输入: s = "abc"
 * 输出: true
 * <p>
 * 限制：
 * 0 <= len(s) <= 100
 * s[i]仅包含小写字母
 * 如果你不使用额外的数据结构，会很加分。
 *
 * Tag:看到不使用额外的数据结构，就想到位运算。
 */
public class WhetherStringAreUnique implements Answer {
    public static void main(String[] args) {
        new WhetherStringAreUnique().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        String astr = "letcode";
        System.out.println(isUnique2(astr));
    }

    /**
     * 使用额外数组进行判断
     */
    public boolean isUnique(String astr) {
        int[] flag = new int[32];
        for (char c : astr.toCharArray()) {
            flag[c - 'a']++;
            if (flag[c - 'a'] > 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 使用位运算
     * int 刚好32位
     */
    public boolean isUnique2(String astr) {
        int flag = 0;
        for (char c : astr.toCharArray()) {
            int n = c - 'a';
            if ((flag >> (n-1) & 1) == 1) {
                return false;
            }
            flag = NumberUtil.setBit(flag, n);
        }
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
