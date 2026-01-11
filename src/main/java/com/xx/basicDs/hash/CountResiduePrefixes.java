package com.xx.basicDs.hash;

import com.xx.Answer;

import java.util.HashSet;
import java.util.Set;

/**
 * 统计残差前缀
 * <p>
 * 给你一个仅由小写英文字母组成的字符串 s。
 * 如果字符串 s 的某个 前缀 中 不同字符的数量 等于 len(prefix) % 3，
 * 则该前缀被称为残差前缀（residue）。
 * <p>
 * 返回字符串 s 中 残差前缀 的数量。
 * 字符串的 前缀 是一个 非空子字符串，从字符串的开头起始并延伸到任意位置。
 * <p>
 * 示例 1：
 * 输入: s = "abc"
 * 输出: 2
 * 解释:
 * 前缀 "a" 有 1 个不同字符，且长度模 3 为 1，因此它是一个残差前缀。
 * 前缀 "ab" 有 2 个不同字符，且长度模 3 为 2，因此它是一个残差前缀。
 * 前缀 "abc" 不满足条件，因此不是残差前缀。
 * 因此，答案是 2。
 * <p>
 * 示例 2：
 * 输入: s = "dd"
 * 输出: 1
 * 解释:
 * 前缀 "d" 有 1 个不同字符，且长度模 3 为 1，因此它是一个残差前缀。
 * 前缀 "dd" 有 1 个不同字符，但长度模 3 为 2，因此它不是残差前缀。
 * 因此，答案是 1。
 * <p>
 * 示例 3：
 * 输入: s = "bob"
 * 输出: 2
 * 解释:
 * 前缀 "b" 有 1 个不同字符，且长度模 3 为 1，因此它是一个残差前缀。
 * 前缀 "bo" 有 2 个不同字符，且长度模 3 为 2，因此它是一个残差前缀。
 * 前缀 "bob" 不满足条件。
 * 因此，答案是 2。
 * <p>
 * 提示：
 * 1 <= s.length <= 100
 * s 仅包含小写英文字母。
 * <p>
 * Tag:哈希 字符串遍历  前缀和
 */
public class CountResiduePrefixes implements Answer {
    public static void main(String[] args) {
        new CountResiduePrefixes().answerOne();
    }

    @Override
    public void answerOne() {
        String s = "abc";
        System.out.println(residuePrefixes(s));
    }

    /**
     * set存放前缀中不相同字符的数量
     */
    public int residuePrefixes(String s) {
        Set<Character> set = new HashSet<>();
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            set.add(c);
            int len = (i + 1) % 3;
            if (len == set.size()) {
                result++;
            }
        }
        return result;
    }


    @Override
    public Object initData() {
        return null;
    }
}
