package com.xx.algorithm.math;

import com.xx.Answer;

import java.util.Arrays;

/**
 * 跳水板
 * LeetCode Easy
 * <p>
 * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
 * 返回的长度需要从小到大排列。
 * <p>
 * 示例 1：
 * 输入：
 * shorter = 1
 * longer = 2
 * k = 3
 * 输出：[3,4,5,6]
 * 解释：
 * 可以使用 3 次 shorter，得到结果 3；使用 2 次 shorter 和 1 次 longer，得到结果 4 。以此类推，得到最终结果。
 * <p>
 * 提示：
 * 0 < shorter <= longer
 * 0 <= k <= 100000
 * <p>
 * Tag: 注意输入边界
 */
public class DivingBoard implements Answer {
    public static void main(String[] args) {
        new DivingBoard().answerOne();
    }

    @Override
    public void answerOne() {
        int shorter = 1;
        int longer = 2;
        int k = 3;
        System.out.println(Arrays.toString(divingBoard(shorter, longer, k)));
    }

    /**
     * 注意边界特殊情况：比如k==0，shorter == longer等没考虑到位
     */
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (shorter == longer) {
            return new int[]{k * shorter};
        }
        int[] result = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            result[i] = i * longer + (k - i) * shorter;
        }
        return result;
    }

    @Override
    public Object initData() {
        return null;
    }
}
