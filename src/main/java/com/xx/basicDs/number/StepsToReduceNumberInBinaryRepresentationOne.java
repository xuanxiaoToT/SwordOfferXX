package com.xx.basicDs.number;

import com.xx.Answer;

/**
 * 将二进制表示减到 1 的步骤数
 * LeetCode 1404. Medium-
 * <p>
 * 给你一个以二进制形式表示的数字 s 。请你返回按下述规则将其减少到 1 所需要的步骤数：
 * 如果当前数字为偶数，则将其除以 2 。
 * 如果当前数字为奇数，则将其加上 1 。
 * 题目保证你总是可以按上述规则将测试用例变为 1 。
 * <p>
 * 示例 1：
 * 输入：s = "1101"
 * 输出：6
 * 解释："1101" 表示十进制数 13 。
 * Step 1) 13 是奇数，加 1 得到 14
 * Step 2) 14 是偶数，除 2 得到 7
 * Step 3) 7  是奇数，加 1 得到 8
 * Step 4) 8  是偶数，除 2 得到 4
 * Step 5) 4  是偶数，除 2 得到 2
 * Step 6) 2  是偶数，除 2 得到 1
 * <p>
 * 示例 2：
 * 输入：s = "10"
 * 输出：1
 * 解释："10" 表示十进制数 2 。
 * Step 1) 2 是偶数，除 2 得到 1
 * <p>
 * 示例 3：
 * 输入：s = "1"
 * 输出：0
 * <p>
 * 提示：
 * 1 <= s.length <= 500
 * s 由字符 '0' 或 '1' 组成。
 * s[0] == '1'
 * <p>
 * Tag：位运算  进位
 */
public class StepsToReduceNumberInBinaryRepresentationOne implements Answer {
    public static void main(String[] args) {
        new StepsToReduceNumberInBinaryRepresentationOne().answer();
    }

    @Override
    public void answer() {
        String s = "1101";
        System.out.println(numSteps(s));
    }

    /**
     * 注意s长度最大位500，不能转换为数字，不然长度不够
     * <p>
     * 思路：除以2就是右移1位
     * 然后记住需要进位的
     */
    public int numSteps(String s) {
        int result = 0;
        int index = s.length() - 1;
        int jin = 0;
        while (index >= 0) {
            int temp = Integer.parseInt(String.valueOf(s.charAt(index)));
            temp = temp + jin;
            if (index == 0 && temp == 1) {
                return result;
            }
            // 当前为偶数
            if (temp == 2) {
                jin = 1;
                index--;
            }
            if (temp == 0) {
                index--;
            }
            // 奇数，这里包含了两个操作，奇数的+1和偶数的进位，所以多加1
            if (temp == 1) {
                jin = 1;
                result++;
                index--;
            }
            result++;
        }

        return result;
    }

    @Override
    public Object initData() {
        return null;
    }
}
