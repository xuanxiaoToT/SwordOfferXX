package com.xx.basicDs.string;

import com.xx.Answer;

import java.util.HashMap;
import java.util.Map;

/**
 * 统计凯撒加密对数目
 * LeetCode 3805. Medium
 * <p>
 * 给你一个由 n 个字符串组成的数组 words。每个字符串的长度均为 m 且仅包含小写英文字母。
 * 如果我们可以通过执行以下操作任意次数（可能为零次）使得两个字符串 s 和 t 变得 相等，则称这两个字符串是 相似 的。
 * 选择 s 或 t 。
 * 将所选字符串中的 每个 字母替换为字母表中的下一个字母（循环替换）。'z' 之后的下一个字母是 'a'。
 * 计算满足以下条件的下标对 (i, j) 的数量：
 * i < j
 * words[i] 和 words[j] 是 相似 的。
 * 返回一个整数，表示此类下标对的数量。
 * <p>
 * 示例 1：
 * 输入： words = ["fusion","layout"]
 * 输出： 1
 * 解释：
 * words[0] = "fusion" 和 words[1] = "layout" 是相似的，因为我们可以对 "fusion" 执行 6 次操作。字符串 "fusion" 的变化如下。
 * "fusion"
 * "gvtjpo"
 * "hwukqp"
 * "ixvlrq"
 * "jywmsr"
 * "kzxnts"
 * "layout"
 * <p>
 * 示例 2：
 * 输入： words = ["ab","aa","za","aa"]
 * 输出： 2
 * 解释：
 * words[0] = "ab" 和 words[2] = "za" 是相似的。words[1] = "aa" 和 words[3] = "aa" 是相似的。
 * <p>
 * 提示：
 * 1 <= n == words.length <= 10^5
 * 1 <= m == words[i].length <= 10^5
 * 1 <= n * m <= 10^5
 * words[i] 仅由小写英文字母组成。
 * <p>
 * Tag：哈希 环状计算  字符串相似
 */
public class CountCaesarCipherPairs implements Answer {
    public static void main(String[] args) {
        new CountCaesarCipherPairs().answer();
    }

    @Override
    public void answer() {
        String[] words = new String[]{"fusion", "layout"};
        // String[] words = new String[]{"ab", "aa", "za", "aa"};
        // String[] words = new String[]{"ab", "za"};
        System.out.println(countPairs(words));
    }

    /**
     * 字符串S1如果跟S2相似，那意味着S1加的偏移量都必须相同。
     * 我们以第一个字符为基准，计算出一个偏移值 列表，如果这个列表相同，可以证明，这两个之间就可以相互转换。
     * 例如：开头的为1也好5也好，只要偏移值一样，前者就能通过集体+4转变为后者。
     * 这就是特征值的含义
     */
    public long countPairs(String[] words) {
        Map<String, Integer> featureMap = new HashMap<>();
        // 遍历每个字符串，生成特征Key并统计次数
        for (String word : words) {
            // 计算第一个字符的基准值（a=0, b=1...z=25）
            int base = word.charAt(0) - 'a';
            StringBuilder featureKey = new StringBuilder();
            //  计算每个偏移量，并求出特征值
            for (char c : word.toCharArray()) {
                int offset = (c - 'a' - base + 26) % 26;
                featureKey.append(offset).append("@#");
            }
            // 统计该特征的出现次数（存在则+1，不存在则设为1）
            featureMap.merge(featureKey.toString(), 1, Integer::sum);
        }
        long result = 0L;
        for (int count : featureMap.values()) {
            if (count >= 2) {
                // 因为可以两两配对，所以就是C(n,2)
                result += (long) count * (count - 1) / 2;
            }
        }
        return result;
    }

    /**
     * 题目明确了words.length的长度位10^5
     * 一半这种如果双重遍历，肯定会超时
     */
    public long countPairsOld(String[] words) {
        if (words.length == 1) {
            return 0L;
        }
        long result = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (whetherSimilar(words[i], words[j])) {
                    result++;
                }
            }
        }
        return result;
    }

    private boolean whetherSimilar(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        int base = (str2.charAt(0) - 'a' + 26 - (str1.charAt(0) - 'a')) % 26;
        for (int i = 1; i < str1.length(); i++) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            // 环状结构的计算公式
            int temp = (c2 - 'a' + 26 - (c1 - 'a')) % 26;
            if (map.containsKey(c1) && map.get(c1) != c2) {
                return false;
            }
            if (temp != base) {
                return false;
            }
            map.put(c1, c2);
        }
        return true;
    }

    @Override
    public Object initData() {
        return null;
    }
}
