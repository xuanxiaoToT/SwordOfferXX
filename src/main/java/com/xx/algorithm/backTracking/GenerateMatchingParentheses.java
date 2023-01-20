package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/11/24
 * <p>
 * 生成匹配的括号
 * <p>
 * 输入一个正整数n，请输出所有包含n个左括号和n个右括
 * 号的组合，要求每个组合的左括号和右括号匹配。例如，当n等于2
 * 时，有两个符合条件的括号组合，分别是"（()）"和"()()"。
 * <p>
 * ps:本质上是括号的全排列
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