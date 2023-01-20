package com.xx.basicDs.stack;

import com.xx.Answer;

import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/6
 * 后缀表达式
 * 后缀表达式是一种算术表达式，它的操作符在操作数的
 * 后面。输入一个用字符串数组表示的后缀表达式，请输出该后缀表
 * 达式的计算结果。假设输入的一定是有效的后缀表达式。例如，后
 * 缀表达式["2"，"1"，"3"，"*"，"+"]对应的算术表达式是
 * “2+1*3”，因此输出它的计算结果5。
 */
public class PostfixExpression implements Answer {

    public static void main(String[] args) {
        new PostfixExpression().answerOne();
    }

    /**
     * 可以看出是运算符左侧的两个数字进行运算。考虑将不用的数字放在一个容器里，
     * 那么栈就是理所应当的选择。
     */
    @Override
    public void answerOne() {
        Stack<Integer> stack = new Stack<>();
        String[] date = initData();
        for (String s : date) {
            if (isNum(s)) {
                stack.push(Integer.parseInt(s));
            } else {
                Integer tempResult = null;
                if ("+".equals(s)) {
                    Integer num1 = stack.pop();
                    Integer num2 = stack.pop();
                    tempResult = num1 + num2;
                }
                if ("-".equals(s)) {
                    Integer num1 = stack.pop();
                    Integer num2 = stack.pop();
                    tempResult = num1 - num2;
                }
                if ("*".equals(s)) {
                    Integer num1 = stack.pop();
                    Integer num2 = stack.pop();
                    tempResult = num1 * num2;
                }
                if ("/".equals(s)) {
                    Integer num1 = stack.pop();
                    Integer num2 = stack.pop();
                    tempResult = num1 / num2;
                }
                stack.push(tempResult);
            }
        }
        if (stack.size() == 1) {
            System.out.println(stack.pop());
        } else {
            System.out.println("有问题");
        }
    }

    private boolean isNum(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * something
     */
    @Override
    public String[] initData() {
        return new String[]{"2", "1", "3", "*", "+"};
    }
}
