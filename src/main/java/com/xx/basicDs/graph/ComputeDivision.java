package com.xx.basicDs.graph;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/26
 * 计算除法
 * 输入两个数组equations和values，其中，数组
 * equations的每个元素包含两个表示变量名的字符串，数组values的
 * 每个元素是一个浮点数值。如果equations[i]的两个变量名分别是
 * Ai和Bi，那么Ai/Bi=values[i]。再给定一个数组queries，它的每个
 * 元素也包含两个变量名。对于queries[j]的两个变量名Cj和Dj，请
 * 计算Cj/Dj的结果。假设任意values[i]大于0。如果不能计算，那么
 * 返回-1。
 *
 * 输入数组equations为[["a"，"b"]，["b"，"c"]]，数组
 * values为[2.0，3.0]，如果数组queries为[["a"，"c"]，
 * ["b"，"a"]，["a"，"e"]，["a"，"a"]，["x"，"x"]]，那么对应的计
 * 算结果为[6.0，0.5，-1.0，1.0，-1.0]。由数组equations和values
 * 可知，a/b=2.0，b/c=3.0，所以，a/c=6.0，b/a=0.5，a/a=1.0。
 */
public class ComputeDivision implements Answer {
    public static void main(String[] args) {
        new ComputeDivision().answerOne();
    }
    /**
     * 解
     */
    @Override
    public void answerOne() {

    }

    /**
     * 输入数据
     */
    @Override
    public Object initData() {
        return null;
    }
}