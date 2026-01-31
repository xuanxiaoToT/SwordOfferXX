package com.xx.basicDs.graph.shortestPath;

import com.xx.Answer;

/**
 * 转换字符串的最小成本I
 * LeetCode 2976. Medium
 * <p>
 * 给你两个下标从 0 开始的字符串 source 和 target ，它们的长度均为 n 并且由 小写 英文字母组成。
 * 另给你两个下标从 0 开始的字符数组 original 和 changed ，以及一个整数数组 cost ，其中 cost[i] 代表将字符 original[i] 更改为字符 changed[i] 的成本。
 * 你从字符串 source 开始。在一次操作中，如果 存在 任意 下标 j 满足 cost[j] == z  、original[j] == x 以及 changed[j] == y 。你就可以选择字符串中的一个字符 x 并以 z 的成本将其更改为字符 y 。
 * 返回将字符串 source 转换为字符串 target 所需的 最小 成本。如果不可能完成转换，则返回 -1 。
 * 注意，可能存在下标 i 、j 使得 original[j] == original[i] 且 changed[j] == changed[i] 。
 * <p>
 * 示例 1：
 * 输入：source = "abcd", target = "acbe", original = ["a","b","c","c","e","d"], changed = ["b","c","b","e","b","e"], cost = [2,5,5,1,2,20]
 * 输出：28
 * 解释：将字符串 "abcd" 转换为字符串 "acbe" ：
 * - 更改下标 1 处的值 'b' 为 'c' ，成本为 5 。
 * - 更改下标 2 处的值 'c' 为 'e' ，成本为 1 。
 * - 更改下标 2 处的值 'e' 为 'b' ，成本为 2 。
 * - 更改下标 3 处的值 'd' 为 'e' ，成本为 20 。
 * 产生的总成本是 5 + 1 + 2 + 20 = 28 。
 * 可以证明这是可能的最小成本。
 * <p>
 * 示例 2：
 * 输入：source = "aaaa", target = "bbbb", original = ["a","c"], changed = ["c","b"], cost = [1,2]
 * 输出：12
 * 解释：要将字符 'a' 更改为 'b'：
 * - 将字符 'a' 更改为 'c'，成本为 1
 * - 将字符 'c' 更改为 'b'，成本为 2
 * 产生的总成本是 1 + 2 = 3。
 * 将所有 'a' 更改为 'b'，产生的总成本是 3 * 4 = 12 。
 * <p>
 * 示例 3：
 * 输入：source = "abcd", target = "abce", original = ["a"], changed = ["e"], cost = [10000]
 * 输出：-1
 * 解释：无法将 source 字符串转换为 target 字符串，因为下标 3 处的值无法从 'd' 更改为 'e' 。
 * <p>
 * <p>
 * 提示：
 * 1 <= source.length == target.length <= 10^5
 * source、target 均由小写英文字母组成
 * 1 <= cost.length== original.length == changed.length <= 2000
 * original[i]、changed[i] 是小写英文字母
 * 1 <= cost[i] <= 10^6
 * original[i] != changed[i]
 * <p>
 * Tag：动态规划  弗洛伊德算法   多源最短路径
 */
public class MinimumCostToConvertStringI implements Answer {
    public static void main(String[] args) {
        new MinimumCostToConvertStringI().answer();
    }

    @Override
    public void answer() {
        // String source = "abcd";
        // String target = "acbe";
        // char[] original = {'a', 'b', 'c', 'c', 'e', 'd'};
        // char[] changed = {'b', 'c', 'b', 'e', 'b', 'e'};
        // int[] cost = {2, 5, 5, 1, 2, 20};

        String source = "abadcdadac";
        String target = "baddbccdac";
        char[] original = {'d', 'c', 'd', 'c', 'b', 'a'};
        char[] changed = {'b', 'b', 'c', 'a', 'd', 'd'};
        int[] cost = {8, 5, 9, 1, 10, 2};
        System.out.println(minimumCost(source, target, original, changed, cost));
    }

    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int[][] dp = new int[26][26];

        for (int i = 0; i < original.length; i++) {
            char ori = original[i];
            char cha = changed[i];
            int cos = cost[i];
            dp[ori - 'a'][cha - 'a'] = dp[ori - 'a'][cha - 'a'] == 0 ? cos : Math.min(dp[ori - 'a'][cha - 'a'], cos);
        }

        // 为什么要把中间节点放在最外层：
        // 它的含义是：只允许使用前 k 个节点（节点编号 0,1,…,k）作为中间点时，节点 i 到节点 j 的最短路径长度。
        // 表达了一个关键逻辑：要计算dp[i][j]，必须依赖已经计算完成的dp[..][..]，也就是必须先把前 k−1 个节点作为中间点的所有情况处理完，才能处理第 k 个节点
        for (int k = 0; k < dp.length; k++) {
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp.length; j++) {
                    if (i == k || j == k || i == j) {
                        continue;
                    } else {
                        if (dp[i][k] != 0 && dp[k][j] != 0) {
                            // 表示i到j的距离，k是中间节点
                            dp[i][j] = dp[i][j] == 0 ? dp[i][k] + dp[k][j] : Math.min(dp[i][k] + dp[k][j], dp[i][j]);
                        }
                    }
                }
            }
        }

        long res = 0;
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            char t = target.charAt(i);
            if (c == t) {
                continue;
            }
            if (dp[c - 'a'][t - 'a'] == 0) {
                return -1;
            }
            res += dp[c - 'a'][t - 'a'];
        }

        return res;
    }


    @Override
    public Object initData() {
        return null;
    }
}
