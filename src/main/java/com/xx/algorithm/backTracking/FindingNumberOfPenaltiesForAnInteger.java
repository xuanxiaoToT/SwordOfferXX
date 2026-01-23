package com.xx.algorithm.backTracking;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/25
 * <p>
 * 求一个整数的惩罚数
 * LeetCode 2698  Medium
 * <p>
 * 给你一个正整数 n ，请你返回 n 的 惩罚数 。
 * n 的 惩罚数 定义为所有满足以下条件 i 的数的平方和：
 * 1 <= i <= n
 * i * i 的十进制表示的字符串可以分割成若干连续子字符串，且这些子字符串对应的整数值之和等于 i 。
 * <p>
 * 示例 1：
 * 输入：n = 10
 * 输出：182
 * 解释：总共有 3 个整数 i 满足要求：
 * - 1 ，因为 1 * 1 = 1
 * - 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
 * - 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
 * 因此，10 的惩罚数为 1 + 81 + 100 = 182
 * <p>
 * 示例 2：
 * 输入：n = 37
 * 输出：1478
 * 解释：总共有 4 个整数 i 满足要求：
 * - 1 ，因为 1 * 1 = 1
 * - 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
 * - 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
 * - 36 ，因为 36 * 36 = 1296 ，且 1296 可以分割成 1 + 29 + 6 。
 * 因此，37 的惩罚数为 1 + 81 + 100 + 1296 = 1478
 * <p>
 * 提示：
 * 1 <= n <= 1000
 */
public class FindingNumberOfPenaltiesForAnInteger implements Answer {

    public static void main(String[] args) {
        new FindingNumberOfPenaltiesForAnInteger().answer();
    }

    /**
     * 解1：关键在于如何找到所有满足要求的数字 i
     */
    @Override
    public void answer() {
        int n = initData();
        int result = 0;
        for (int i = 1; i <= n; i++) {
            int targetNum = i * i;
            if (isPenaltiesNum(targetNum, i)) {
                result += targetNum;
            }
        }
        System.out.println(result);
    }

    private boolean isPenaltiesNum(int targetNum, int i) {
        if (targetNum < 10) {
            return targetNum == i;
        } else {
            return isPenaltiesByBT(String.valueOf(targetNum), 0, 0, i);
        }
    }

    /**
     * 使用回溯法，计算num，可能的 字符整数值之和
     * 思路同《分割回文子字符串》
     * {@link SplitPalindromeSubstring}
     */
    private boolean isPenaltiesByBT(String targetNum, int start, int tempSum, int iValue) {
        if (start == targetNum.length()) {
            return tempSum == iValue;
        }
        for (int i = start; i < targetNum.length(); i++) {
            int temp = Integer.parseInt(targetNum.substring(start, i + 1));
            tempSum = tempSum + temp;
            if (tempSum > iValue) {
                return false;
            }
            boolean result = isPenaltiesByBT(targetNum, i + 1, tempSum, iValue);
            if (result) {
                return true;
            }
            tempSum = tempSum - temp;
        }
        return false;
    }

    /**
     * 输出数据
     */
    @Override
    public Integer initData() {
        return 37;
    }
}
