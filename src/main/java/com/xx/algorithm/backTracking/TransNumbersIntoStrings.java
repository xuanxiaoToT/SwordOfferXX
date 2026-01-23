package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2022/7/11
 * <p>
 * 把数字翻译成字符串
 * <p>
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。
 * 请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * <p>
 * 示例：
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 */
public class TransNumbersIntoStrings implements Answer {

    private int stringSize;
    private int result = 0;

    public static void main(String[] args) {
        new TransNumbersIntoStrings().answer();
    }

    /**
     * 回溯法递归
     */
    @Override
    public void answer() {
        String ori = initData();
        stringSize = ori.length();
        int point = 0;
        find(ori, point);
        System.out.println(result);
    }

    private void find(String ori, int point) {
        // 递归的退出条件
        if (point == (stringSize - 1)) {
            result = result + 1;
            return;
        }
        if (point == (stringSize - 2)) {
            if (Integer.parseInt(ori.substring(point)) <= 25) {
                result = result + 2;
                return;
            } else {
                result = result + 1;
                return;
            }
        }
        find(ori, point + 1);
        if (Integer.parseInt(ori.substring(point, point + 2)) <= 25) {
            find(ori, point + 2);
        }
    }

    public void answerTwo() {
        String s = initData();
        // dp[i] 表示前 i 位可以解码的总数
        int[] dp = new int[s.length()];
        // dp[0] 表示前 0 位可以解码的总数
        dp[0] = 1;
        // 通过 for 循环填充 dp 数组
        for (int i = 1; i < s.length(); i++) {
            // 在当前遍历的过程中，第 i 个字符为倒数第一个字符
            // 那么第 i - 1 就是倒数第二个字符
            // 1、如果发现第 i - 1 的字符为 1
            // 2、或者发现第 i - 1 的字符为 2 并且第 i 个字符的值小于等于 5
            // 这两个判断条件指的值当前遍历的字符的倒数两个字符为
            // 10、11、12、13、14、15、16、17、18、19、20、21、22、23、24、25
            if (Integer.parseInt(s.substring(i - 1, i + 1)) <= 25) {
                // 如果 i == 1，说明此时只有两个数字，那么 i - 2 是不存在的
                // 对于这种情况有两种翻译方法，比如 num = 14
                // 1、两个数字单独拿出来翻译 1 翻译为 b，4 翻译为 e，结果就是 be
                // 2、直接把 14 翻译为字母 ，结果就是 o
                // 所以 dp[i] = 2
                if (i == 1) {
                    // 有两种翻译方法
                    dp[i] = 2;
                    // 否则的话，说明遍历的字符串有三个数字了
                    // 并且最后两个数字可以翻译为字母
                } else {
                    // 那么此时 dp[i] = dp[i - 1] + dp[i - 2];
                    dp[i] = dp[i - 1] + dp[i - 2];
                }
                // 如果最后两个数字组合在一起都无法翻译为字母
                // 比如 1258，最后两个数字 58 无法翻译为字母，8 只能单独翻译，所以方法数和 125 一样
            } else {
                // 那么 dp[i] = dp[i - 1]
                dp[i] = dp[i - 1];
            }
        }
        // dp[s.length() - 1] 表示前 s.length() - 1 位可以解码的总数
        System.out.println(Arrays.toString(dp));
        System.out.println(dp[s.length() - 1]);
    }

    @Override
    public String initData() {
        return "12258";
    }
}