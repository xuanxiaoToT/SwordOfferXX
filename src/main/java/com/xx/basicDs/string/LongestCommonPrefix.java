package com.xx.basicDs.string;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/9/27
 * <p>
 * 最长公共前缀
 * LeetCode 14. 简单
 * <p>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * <p>
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * <p>
 * 提示：
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 */
public class LongestCommonPrefix implements Answer {

    public static void main(String[] args) {
        new LongestCommonPrefix().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        String[] strs = initData();
        char c = ' ';
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 0; j < strs.length; j++) {
                String str = strs[j];
                if (j == 0) {
                    c = str.charAt(i);
                    continue;
                }
                if (str.length() - 1 < i || str.charAt(i) != c) {
                    System.out.println(result.toString());
                    return;
                }
            }
            result.append(c);
        }

        return;
    }

    /**
     * 输出数据
     */
    @Override
    public String[] initData() {
        return new String[]{"flower", "flow", "flight"};
    }
}
