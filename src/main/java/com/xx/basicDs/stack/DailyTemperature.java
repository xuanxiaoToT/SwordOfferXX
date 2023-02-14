package com.xx.basicDs.stack;

import com.xx.Answer;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/7
 * <p>
 * 每日温度
 * <p>
 * 输入一个数组，它的每个数字是某天的温度。请计算每
 * 天需要等几天才会出现更高(比自己高)的温度。
 * <p>
 * 例如，如果输入数组[35，31，33，36，34]，
 * 那么输出为[3，1，1，0，0]。由于第1天的温度是
 * 35℃，要等3天才会出现更高的温度36℃，因此对应的输出为3。第4
 * 天的温度是36℃，后面没有更高的温度，它对应的输出是0。其他的
 * 以此类推。
 */
public class DailyTemperature implements Answer {

    public static void main(String[] args) {
        new DailyTemperature().answerTwo();
    }

    /**
     * 方法一：遍历O(N2)
     * 解法略
     */
    @Override
    public void answerOne() {

    }

    /**
     * 方法二：用一个栈保存每天的温度在数
     * 组中的下标。每次从数组中读取一个温度，然后将其与栈中保存的温
     * 度（根据下标可以得到温度）进行比较。如果当前温度比位于栈顶的
     * 温度高，那么就能知道位于栈顶那一天需要等待几天才会出现更高的
     * 温度。然后出栈1次，将当前温度与下一个位于栈顶的温度进行比较。
     * 如果栈中已经没有比当前温度低的温度，则将当前温度在数组中的下
     * 标入栈。
     */
    public void answerTwo() {
        int[] testDate = initData();
        int[] result = new int[testDate.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < testDate.length; i++) {
            while (!stack.isEmpty() && testDate[i] > testDate[stack.peek()]) {
                int pre = stack.pop();
                result[pre] = i - pre;
            }
            stack.push(i);
        }
        System.out.println(stack);
    }

    /**
     * 方法三，每次跟左侧的比较。
     */
    public void answerThree() {
        int[] testDate = initData();
        int[] result = new int[testDate.length];

        for (int i = 1; i < testDate.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (result[j] != 0) {
                    continue;
                }
                if (testDate[i] > testDate[j]) {
                    result[j] = i - j;
                }
            }
        }
        System.out.println(Arrays.toString(result));
    }

    /**
     * something
     */
    @Override
    public int[] initData() {
        return new int[]{35, 31, 33, 36, 34};
    }
}