package com.xx.algorithm.math;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/20
 * <p>
 * 使三个字符串相等
 * LeetCode 2937. Easy
 * <p>
 * 给你三个字符串 s1、s2 和 s3。 你可以根据需要对这三个字符串执行以下操作 任意次数 。
 * 在每次操作中，你可以选择其中一个长度至少为 2 的字符串 并删除其 最右位置上 的字符。
 * 如果存在某种方法能够使这三个字符串相等，请返回使它们相等所需的 最小 操作次数；否则，返回 -1。
 * <p>
 * 示例 1：
 * 输入：s1 = "abc"，s2 = "abb"，s3 = "ab"
 * 输出：2
 * 解释：对 s1 和 s2 进行一次操作后，可以得到三个相等的字符串。
 * 可以证明，不可能用少于两次操作使它们相等。
 * <p>
 * 示例 2：
 * 输入：s1 = "dac"，s2 = "bac"，s3 = "cac"
 * 输出：-1
 * 解释：因为 s1 和 s2 的最左位置上的字母不相等，所以无论进行多少次操作，它们都不可能相等。因此答案是 -1 。
 * <p>
 * 提示：
 * 1 <= s1.length, s2.length, s3.length <= 100
 * s1、s2 和 s3 仅由小写英文字母组成。
 * <p>
 * Tag:  最长公共前缀
 */
public class MakeThreeStringsEqual implements Answer {

    public static void main(String[] args) {
        new MakeThreeStringsEqual().answerOne();
    }


    @Override
    public void answerOne() {
        String s1 = "abc";
        String s2 = "a";
        String s3 = "ab";
        System.out.println(findMinimumOperations(s1, s2, s3));
    }

    /**
     * 解1：计算最长公共前缀，然后用各自的length()去减。
     */
    public int findMinimumOperations(String s1, String s2, String s3) {
        int minLength = Math.min(s3.length(), Math.min(s1.length(), s2.length()));
        int commonCount = 0;
        for (int i = 0; i < minLength; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            char c3 = s3.charAt(i);
            if (c1 == c2 && c2 == c3) {
                commonCount++;
            } else {
                break;
            }
        }
        if (commonCount == 0) {
            return -1;
        }
        return s3.length() + s1.length() + s2.length() - 3 * commonCount;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
