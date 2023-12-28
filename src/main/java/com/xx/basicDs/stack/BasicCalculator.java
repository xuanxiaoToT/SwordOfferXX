package com.xx.basicDs.stack;

import com.xx.Answer;

import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2023/12/27
 * <p>
 * 基本计算器
 * LeetCode 224 Hard
 * <p>
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 * <p>
 * 示例 1：
 * 输入：s = "1 + 1"
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * <p>
 * 示例 3：
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 * <p>
 * 提示：
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效)
 * '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)
 * 输入中不存在两个连续的操作符
 * 每个数字和运行的计算将适合于一个有符号的 32位 整数
 * <p>
 * Tag：递归  栈
 */
public class BasicCalculator implements Answer {

    public static void main(String[] args) {
        new BasicCalculator().answerOne();
    }

    @Override
    public void answerOne() {
        //String data = "-1+(-3)+(6+8)";
        //String data = "2-4-(8+2-6+(8+4-(1)+8-10))";
        String data = "1+2-(3+4+5)+6";
        System.out.println(myCalculator("1  +  1"));
    }


    /**
     * https://leetcode.cn/problems/basic-calculator/solutions/2384893/javapython3czhan-zhan-kai-gua-hao-qiu-he-uu72/?envType=study-plan-v2&envId=top-interview-150
     */
    public static int calculate(String s) {
        //计算结果，部分计算结果，括号中计算结果
        int res = 0;
        //当前的数字，例如：1+23中的1或者23
        int num = 0;
        //符号，加号(+1)或者减号(-1)
        int sign = 1;
        //当右括号时，用于存储计算结果
        Stack<Integer> stack = new Stack<>();

        char[] chars = s.toCharArray();
        int len = chars.length;

        for (int i = 0; i < len; i++) {
            char c = chars[i];
            //如果当前字符为' '，则直接continue
            if (c == ' ') {
                continue;
            }
            //如果当前字符是一个数字，则用num进行记录
            //当前有可能是一个>9的数字，所以需要num = num * 10 + c - '0'
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
                //判断当前数字是否已经取完
                //例如：123+4，只有当取到+时，才能确定123为当前的num
                if (i < len - 1 && '0' <= chars[i + 1] && chars[i + 1] <= '9') {
                    continue;
                }
                //如果当前字符为'+'或者'-'
            } else if (c == '+' || c == '-') {
                //将num置为0，用来存放当前符号(+/-)之后的数字
                num = 0;
                //如果当前符号为+，则sign为+1
                //如果当前符号为-，则sign为-1
                sign = c == '+' ? 1 : -1;
                //如果当前符号为'('
            } else if (c == '(') {
                //例如当前表达式为：'123+(...)'
                //则将res:123，入栈
                stack.push(res);
                //则将sign:+，入栈
                stack.push(sign);
                //同时res置为0，用来保存()中的计算结果
                res = 0;
                //sign置为初始状态，为1
                sign = 1;
                //如果当前符号为')'
            } else if (c == ')') {
                // '('前边的符号出栈
                sign = stack.pop();
                // 将num替换为括号中的计算结果
                num = res;
                //将res替换为括号前边的计算结果
                res = stack.pop();
            }
            //每遍历一次，得到一个res
            res += sign * num;
        }
        return res;
    }

    public int myCalculator(String data) {
        int sign = 1;
        StringBuilder sb = new StringBuilder();
        int result = 0;
        Stack<Integer> signStack = new Stack<>();
        Stack<Integer> tempResultStack = new Stack<>();
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                sb.append(c);
            } else {
                if (sb.length() > 0) {
                    int num = Integer.parseInt(sb.toString());
                    sb = new StringBuilder();
                    result = result + sign * num;
                }
                if (c == '+' || c == '-') {
                    sign = c == '+' ? 1 : -1;
                }
                if (c == '(') {
                    signStack.push(sign);
                    tempResultStack.push(result);
                    result = 0;
                    sign = 1;
                }
                if (c == ')') {
                    int tempResult = tempResultStack.pop();
                    sign = signStack.pop();
                    result = tempResult + result * sign;
                }
            }
        }
        if (sb.length() > 0) {
            int num = Integer.parseInt(sb.toString());
            result = result + sign * num;
        }
        return result;
    }


    @Override
    public Object initData() {
        return null;
    }
}