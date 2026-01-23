package com.xx.basicDs.stack;

import com.xx.Answer;

import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/6
 * <p>
 * 后缀表达式
 * LeetCode 150. 逆波兰表达式求值  Medium
 * <p>
 * 后缀表达式是一种算术表达式，它的操作符在操作数的
 * 后面。输入一个用字符串数组表示的后缀表达式，请输出该后缀表
 * 达式的计算结果。假设输入的一定是有效的后缀表达式。
 *
 * <p>
 * 例如，后缀表达式["2"，"1"，"3"，"*"，"+"]对应的算术表达式是
 * “2+1*3”，因此输出它的计算结果5。
 * <p>
 * 示例 1：
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * <p>
 * 示例 2：
 * 输入：tokens = ["4","13","5","/","+"]
 * 输出：6
 * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 * <p>
 * 示例 3：
 * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * 输出：22
 * 解释：该算式转化为常见的中缀算术表达式为：
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * <p>
 * 提示：
 * 1 <= tokens.length <= 10^4
 * tokens[i] 是一个算符（"+"、"-"、"*" 或 "/"），或是在范围 [-200, 200] 内的一个整数
 */
public class PostfixExpression implements Answer {

    public static void main(String[] args) {
        new PostfixExpression().answer();
    }

    /**
     * 可以看出是运算符左侧的两个数字进行运算。考虑将不用的数字放在一个容器里，
     * 那么栈就是理所应当的选择。
     */
    @Override
    public void answer() {
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
                    tempResult = num2 - num1;
                }
                if ("*".equals(s)) {
                    Integer num1 = stack.pop();
                    Integer num2 = stack.pop();
                    tempResult = num1 * num2;
                }
                if ("/".equals(s)) {
                    Integer num1 = stack.pop();
                    Integer num2 = stack.pop();
                    tempResult = num2 / num1;
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
        return !"+".equals(s) && !"-".equals(s) && !"*".equals(s) && !"/".equals(s);
    }

    /**
     * something
     */
    @Override
    public String[] initData() {
        //return new String[]{"2", "1", "3", "*", "+"};
        //return new String[]{"2", "1", "+", "3", "*"};
        return new String[]{"4", "13", "5", "/", "+"};
        //return new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
    }
}
