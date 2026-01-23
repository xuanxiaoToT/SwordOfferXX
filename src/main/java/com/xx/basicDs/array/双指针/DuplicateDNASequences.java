package com.xx.basicDs.array.双指针;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/5
 * <p>
 * 重复的DNA序列
 * LeetCode 187. Medium
 * <p>
 * DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。
 * 例如，"ACGAATTCCG" 是一个 DNA序列 。
 * 在研究 DNA 时，识别 DNA 中的重复序列非常有用。
 * 给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。
 * 你可以按 任意顺序 返回答案。
 * <p>
 * 示例 1：
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 * <p>
 * 示例 2：
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 * <p>
 * 提示：
 * 0 <= s.length <= 10^5
 * s[i]=='A'、'C'、'G' or 'T'
 */
public class DuplicateDNASequences implements Answer {
    public static void main(String[] args) {
        new DuplicateDNASequences().answer();
    }

    /**
     * 解1：
     */
    @Override
    public void answer() {
        String s = initData();
        System.out.println(findRepeatedDnaSequences(s));
    }

    /**
     * 双指针简单做
     */
    public List<String> findRepeatedDnaSequences(String s) {
        HashMap<String, Integer> hasVisited = new HashMap<>();
        List<String> result = new ArrayList<>();
        int left = 0;
        int right = 9;
        while (right < s.length()) {
            String substring = s.substring(left, right + 1);
            if (hasVisited.containsKey(substring)) {
                hasVisited.put(substring, hasVisited.get(substring) + 1);
            } else {
                hasVisited.put(substring, 1);
            }
            left++;
            right++;
        }
        for (Map.Entry<String, Integer> entry : hasVisited.entrySet()) {
            if (entry.getValue() > 1) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public int computeVal(Character c) {
        switch (c) {
            case 'A':
                return 1;
            case 'C':
                return 2;
            case 'G':
                return 3;
            case 'T':
                return 4;
        }
        return 0;
    }

    /**
     * 输出数据
     */
    @Override
    public String initData() {
        return "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
    }
}
