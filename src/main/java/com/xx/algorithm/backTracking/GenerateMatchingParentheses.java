package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/11/24
 * <p>
 * 生成匹配的括号
 * Lercode 22. 括号生成 Medium
 * <p>
 * 输入一个正整数n，请输出所有包含n个左括号和n个右括
 * 号的组合，要求每个组合的左括号和右括号匹配。例如，当n等于2
 * 时，有两个符合条件的括号组合，分别是"（()）"和"()()"。
 * <p>
 * ps:本质上是括号的全排列
 * <p>
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 * <p>
 * 提示：
 * 1 <= n <= 8
 */
public class GenerateMatchingParentheses implements Answer {

    public static void main(String[] args) {
        new GenerateMatchingParentheses().answerOne();
    }

    private List<List<String>> result = new ArrayList();
    private int n = 0;

    /**
     * something
     */
    @Override
    public void answerOne() {
        n = initData();
        diGui(new ArrayList<>(), n, n);
        System.out.println(result);
    }

    /**
     * @param left  : 剩余的左括号数目。
     * @param right : 剩余的右括号数目。
     */
    private void diGui(List<String> temp, int left, int right) {
        if (left == 0 && right == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (left > 0) {
            temp.add("(");
            left--;
            diGui(temp, left, right);
            temp.remove(temp.size() - 1);
            left++;
        }
        // 右括号比左括号少的时候才允许添加
        if (right > 0 && right > left) {
            temp.add(")");
            right--;
            diGui(temp, left, right);
            temp.remove(temp.size() - 1);
            right++;
        }

    }

    /**
     * something
     */
    @Override
    public Integer initData() {
        return 2;
    }
}