package com.xx.basicDs.hash;

import com.xx.Answer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/2
 * <p>
 * 环和杆
 * LeetCode 2103. Easy
 * <p>
 * 总计有 n 个环，环的颜色可以是红、绿、蓝中的一种。这些环分别穿在 10 根编号为 0 到 9 的杆上。
 * 给你一个长度为 2n 的字符串 rings ，表示这 n 个环在杆上的分布。rings 中每两个字符形成一个 颜色位置对 ，用于描述每个环：
 * 第 i 对中的 第一个 字符表示第 i 个环的 颜色（'R'、'G'、'B'）。
 * 第 i 对中的 第二个 字符表示第 i 个环的 位置，也就是位于哪根杆上（'0' 到 '9'）。
 * 例如，"R3G2B1" 表示：共有 n == 3 个环，红色的环在编号为 3 的杆上，绿色的环在编号为 2 的杆上，蓝色的环在编号为 1 的杆上。
 * 找出所有集齐 全部三种颜色 环的杆，并返回这种杆的数量。
 * <p>
 * 示例 1：
 * 输入：rings = "B0B6G0R6R0R6G9"
 * 输出：1
 * 解释：
 * - 编号 0 的杆上有 3 个环，集齐全部颜色：红、绿、蓝。
 * - 编号 6 的杆上有 3 个环，但只有红、蓝两种颜色。
 * - 编号 9 的杆上只有 1 个绿色环。
 * 因此，集齐全部三种颜色环的杆的数目为 1 。
 * <p>
 * 示例 2：
 * 输入：rings = "B0R0G0R9R0B0G0"
 * 输出：1
 * 解释：
 * - 编号 0 的杆上有 6 个环，集齐全部颜色：红、绿、蓝。
 * - 编号 9 的杆上只有 1 个红色环。
 * 因此，集齐全部三种颜色环的杆的数目为 1 。
 * <p>
 * 示例 3：
 * 输入：rings = "G4"
 * 输出：0
 * 解释：
 * 只给了一个环，因此，不存在集齐全部三种颜色环的杆。
 * <p>
 * 提示：
 * rings.length == 2 * n
 * 1 <= n <= 100
 * 如 i 是 偶数 ，则 rings[i] 的值可以取 'R'、'G' 或 'B'（下标从 0 开始计数）
 * 如 i 是 奇数 ，则 rings[i] 的值可以取 '0' 到 '9' 中的一个数字（下标从 0 开始计数）
 */
public class RingAndRod implements Answer {

    public static void main(String[] args) {
        new RingAndRod().answerOne();
    }

    /**
     * 解1：用map简单做即可
     */
    @Override
    public void answerOne() {
        String rings = initData();
        System.out.println(countPoints(rings));
    }

    public int countPoints(String rings) {
        int left = 0;
        int right = 1;
        int result = 0;
        Map<Character, HashSet<Character>> map = new HashMap<>();
        while (right < rings.length()) {
            char color = rings.charAt(left);
            char rodNum = rings.charAt(right);
            if (map.containsKey(rodNum)) {
                map.get(rodNum).add(color);
            } else {
                HashSet<Character> set = new HashSet<>();
                set.add(color);
                map.put(rodNum, set);
            }
            left += 2;
            right += 2;
        }
        for (Map.Entry<Character, HashSet<Character>> entry : map.entrySet()) {
            if (entry.getValue().size() >= 3) {
                result++;
            }
        }
        return result;
    }

    /**
     * 输出数据
     */
    @Override
    public String initData() {
        return "B0B6G0R6R0R6G9";
    }
}
