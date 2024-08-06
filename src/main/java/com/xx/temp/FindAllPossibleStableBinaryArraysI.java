package com.xx.temp;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2024/8/6
 * <p>
 * 找出所有稳定的二进制数组I
 * LeetCode 3129. Medium
 * <p>
 * 给你 3 个正整数 zero ，one 和 limit 。
 * <p>
 * 一个 二进制数组 arr 如果满足以下条件，那么我们称它是 稳定的 ：
 * 0 在 arr 中出现次数 恰好 为 zero 。
 * 1 在 arr 中出现次数 恰好 为 one 。
 * arr 中每个长度超过 limit 的 子数组都 同时 包含 0 和 1 。
 * 请你返回 稳定 二进制数组的 总 数目。
 * <p>
 * 由于答案可能很大，将它对 109 + 7 取余 后返回。
 * <p>
 * 示例 1：
 * 输入：zero = 1, one = 1, limit = 2
 * <p>
 * 输出：2
 * 解释：
 * 两个稳定的二进制数组为 [1,0] 和 [0,1] ，两个数组都有一个 0 和一个 1 ，且没有子数组长度大于 2 。
 * <p>
 * 示例 2：
 * 输入：zero = 1, one = 2, limit = 1
 * 输出：1
 * 解释：
 * 唯一稳定的二进制数组是 [1,0,1] 。
 * 二进制数组 [1,1,0] 和 [0,1,1] 都有长度为 2 且元素全都相同的子数组，所以它们不稳定。
 * <p>
 * 示例 3：
 * 输入：zero = 3, one = 3, limit = 2
 * 输出：14
 * 解释：
 * 所有稳定的二进制数组包括 [0,0,1,0,1,1] ，[0,0,1,1,0,1] ，[0,1,0,0,1,1] ，[0,1,0,1,0,1] ，[0,1,0,1,1,0] ，
 * [0,1,1,0,0,1] ，[0,1,1,0,1,0] ，[1,0,0,1,0,1] ，[1,0,0,1,1,0] ，[1,0,1,0,0,1] ，[1,0,1,0,1,0] ，
 * [1,0,1,1,0,0] ，[1,1,0,0,1,0] 和 [1,1,0,1,0,0] 。
 * <p>
 * 提示：
 * 1 <= zero, one, limit <= 200
 * <p>
 * Tag：动态规划
 */
public class FindAllPossibleStableBinaryArraysI implements Answer {

    public static void main(String[] args) {
        new FindAllPossibleStableBinaryArraysI().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {

    }

    /**
     * 题解参考：https://leetcode.cn/problems/find-all-possible-stable-binary-arrays-ii/solutions/2758868/dong-tai-gui-hua-cong-ji-yi-hua-sou-suo-37jdi/
     * <p>
     * dfs(i−1,j,0) 包含了「最后连续 limit 个位置填 0」的方案，如果在这个方案末尾再加一个 0，就有连续 limit+1 个 0 了，这是不合法的，要减掉。
     */
    public int numberOfStableArrays(int zero, int one, int limit) {
        final long MOD = 1000000007;
        long[][][] dp = new long[zero + 1][one + 1][2];
        for (int i = 0; i <= Math.min(zero, limit); i++) {
            dp[i][0][0] = 1;
        }
        for (int j = 0; j <= Math.min(one, limit); j++) {
            dp[0][j][1] = 1;
        }
        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                if (i > limit) {
                    dp[i][j][0] = dp[i - 1][j][0] + dp[i - 1][j][1] - dp[i - limit - 1][j][1];
                } else {
                    dp[i][j][0] = dp[i - 1][j][0] + dp[i - 1][j][1];
                }
                dp[i][j][0] = (dp[i][j][0] % MOD + MOD) % MOD;
                if (j > limit) {
                    dp[i][j][1] = dp[i][j - 1][1] + dp[i][j - 1][0] - dp[i][j - limit - 1][0];
                } else {
                    dp[i][j][1] = dp[i][j - 1][1] + dp[i][j - 1][0];
                }
                dp[i][j][1] = (dp[i][j][1] % MOD + MOD) % MOD;
            }
        }
        return (int) ((dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
