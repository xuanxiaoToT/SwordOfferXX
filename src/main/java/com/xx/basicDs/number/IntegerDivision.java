package com.xx.basicDs.number;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/1
 * <p>
 * 计算除法
 * <p>
 * 输入2个int型整数，它们进行除法计算并返回商，要求
 * 不得使用乘号'*'、除号'/'及求余符号'%'。当发生溢出时，返回最
 * 大的整数值。假设除数不为0。例如，输入15和2，输出15/2的结
 * 果，即7。
 */
public class IntegerDivision implements Answer {

    public static void main(String[] args) {
        new IntegerDivision().answer();
    }

    @Override
    public void answer() {
        // 用减法替代，但是如果数很小，需要除的很大时，则有问题
        // 负数未考虑到
        // 改进：使用2 4 6 8倍数
        int num1 = 37;
        int num2 = 2;
        int result = 0;
        while (num1 > num2) {
            int n = 1;
            int temp = num2;
            while (num1 - multiplicationTwo(temp) > 0) {
                temp = multiplicationTwo(temp);
                n = multiplicationTwo(n);
            }
            num1 = num1 - temp;
            result = result + n;
        }
        System.out.println(result);
    }

    private int multiplicationTwo(int value) {
        return value + value;
    }

    @Override
    public Object initData() {
        return null;
    }
}