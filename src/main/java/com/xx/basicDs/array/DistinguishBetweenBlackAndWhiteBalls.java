package com.xx.basicDs.array;

import com.xx.Answer;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/20
 * <p>
 * 区分黑球与白球
 * LeetCode 2938. Medium
 * <p>
 * 桌子上有 n 个球，每个球的颜色不是黑色，就是白色。
 * 给你一个长度为 n 、下标从 0 开始的二进制字符串 s，其中 1 和 0 分别代表黑色和白色的球。
 * 在每一步中，你可以选择两个相邻的球并交换它们。
 * 返回「将所有黑色球都移到右侧，所有白色球都移到左侧所需的 最小步数」。
 * <p>
 * 示例 1：
 * 输入：s = "101"
 * 输出：1
 * 解释：我们可以按以下方式将所有黑色球移到右侧：
 * - 交换 s[0] 和 s[1]，s = "011"。
 * 最开始，1 没有都在右侧，需要至少 1 步将其移到右侧。
 * <p>
 * 示例 2：
 * 输入：s = "100"
 * 输出：2
 * 解释：我们可以按以下方式将所有黑色球移到右侧：
 * - 交换 s[0] 和 s[1]，s = "010"。
 * - 交换 s[1] 和 s[2]，s = "001"。
 * 可以证明所需的最小步数为 2 。
 * <p>
 * 示例 3：
 * 输入：s = "0111"
 * 输出：0
 * 解释：所有黑色球都已经在右侧。
 * <p>
 * 提示：
 * 1 <= n == s.length <= 105
 * s[i] 不是 '0'，就是 '1'。
 */
public class DistinguishBetweenBlackAndWhiteBalls implements Answer {

    public static void main(String[] args) {
        new DistinguishBetweenBlackAndWhiteBalls().answer();
    }


    @Override
    public void answer() {
        System.out.println(minimumSteps("1010101010101"));
    }

    /**
     * 思路：
     * 先假设已经完成排序，1的位置已经全部排好。那么第一个1开始的位置，就是0的个数。
     * 再遍历原数组，对原来的每个1，其最终都是要到目标位置，这中间的差值就是交换数。
     * fixme：注意看返回值！！
     */
    public long minimumSteps(String s) {
        int zeroCount = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                zeroCount++;
            }
        }
        int oneIndex = zeroCount;
        long result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '1') {
                result += oneIndex - i;
                oneIndex++;
            }
        }
        return result;
    }

    /**
     * 还可以以0为标准进行。思路同解1
     **/
    public long minimumSteps2(String s) {
        long ans = 0;
        int n = s.length();
        int num_0 = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                ans += i - num_0;
                num_0++;
            }
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
