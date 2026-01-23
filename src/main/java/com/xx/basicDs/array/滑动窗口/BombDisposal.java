package com.xx.basicDs.array.滑动窗口;

import com.xx.Answer;

import java.util.Arrays;

/**
 * @author XuanXiao
 * @CreateDate 2024/5/5
 * <p>
 * 拆炸弹
 * LeetCode 1652. Easy
 * <p>
 * 你有一个炸弹需要拆除，时间紧迫！你的情报员会给你一个长度为 n 的 循环 数组 code 以及一个密钥 k 。
 * 为了获得正确的密码，你需要替换掉每一个数字。所有数字会 同时 被替换。
 * 如果 k > 0 ，将第 i 个数字用 接下来 k 个数字之和替换。
 * 如果 k < 0 ，将第 i 个数字用 之前 k 个数字之和替换。
 * 如果 k == 0 ，将第 i 个数字用 0 替换。
 * 由于 code 是循环的， code[n-1] 下一个元素是 code[0] ，且 code[0] 前一个元素是 code[n-1] 。
 * 给你 循环 数组 code 和整数密钥 k ，请你返回解密后的结果来拆除炸弹！
 * <p>
 * 示例 1：
 * 输入：code = [5,7,1,4], k = 3
 * 输出：[12,10,16,13]
 * 解释：每个数字都被接下来 3 个数字之和替换。解密后的密码为 [7+1+4, 1+4+5, 4+5+7, 5+7+1]。注意到数组是循环连接的。
 * <p>
 * 示例 2：
 * 输入：code = [1,2,3,4], k = 0
 * 输出：[0,0,0,0]
 * 解释：当 k 为 0 时，所有数字都被 0 替换。
 * <p>
 * 示例 3：
 * 输入：code = [2,4,9,3], k = -2
 * 输出：[12,5,6,13]
 * 解释：解密后的密码为 [3+9, 2+3, 4+2, 9+4] 。注意到数组是循环连接的。如果 k 是负数，那么和为 之前 的数字。
 * <p>
 * 提示：
 * n == code.length
 * 1 <= n <= 100
 * 1 <= code[i] <= 100
 * -(n - 1) <= k <= n - 1
 * <p>
 * Tag:循环数组  数组的遍历
 */
public class BombDisposal implements Answer {
    public static void main(String[] args) {
        new BombDisposal().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        //int[] code = {5, 7, 1, 4};
        //int k = 3;
        int[] code = {2, 4, 9, 3};
        int k = -2;
        System.out.println(Arrays.toString(decrypt(code, k)));
    }

    /**
     * 取模，简单算
     */
    public int[] decrypt(int[] code, int k) {
        int[] result = new int[code.length];
        if (k > 0) {
            for (int i = 0; i < code.length; i++) {
                int tempSum = 0;
                for (int j = 1; j <= k; j++) {
                    int index = (i + j) % code.length;
                    tempSum += code[index];
                }
                result[i] = tempSum;
            }
        }
        if (k < 0) {
            for (int i = 0; i < code.length; i++) {
                int tempSum = 0;
                for (int j = 1; j <= Math.abs(k); j++) {
                    int index = ((i - j) + code.length) % code.length;
                    tempSum += code[index];
                }
                result[i] = tempSum;
            }
        }
        return result;
    }

    /**
     * 定长滑动窗口
     * 注意无论 k>00 还是 k<0，窗口都是在向右移动
     */
    public int[] decrypt2(int[] code, int k) {
        int n = code.length;
        int[] ans = new int[n];
        int r = k > 0 ? k + 1 : n;
        k = Math.abs(k);
        int s = 0;
        for (int i = r - k; i < r; i++) {
            s += code[i]; // 计算 ans[0]
        }
        for (int i = 0; i < n; i++) {
            ans[i] = s;
            s += code[r % n] - code[(r - k) % n];
            r++;
        }
        return ans;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
