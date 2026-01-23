package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.*;

/**
 *
 * 金字塔转换矩阵
 * LeetCode 756. Medium
 * <p>
 * 你正在把积木堆成金字塔。每个块都有一个颜色，用一个字母表示。每一行的块比它下面的行 少一个块 ，并且居中。
 * 为了使金字塔美观，只有特定的 三角形图案 是允许的。一个三角形的图案由 两个块 和叠在上面的 单个块 组成。
 * 模式是以三个字母字符串的列表形式 allowed 给出的，其中模式的前两个字符分别表示左右底部块，第三个字符表示顶部块。
 * <p>
 * 例如，"ABC" 表示一个三角形图案，其中一个 “C” 块堆叠在一个 'A' 块(左)和一个 'B' 块(右)之上。请注意，这与 "BAC" 不同，"B" 在左下角，"A" 在右下角。
 * 你从作为单个字符串给出的底部的一排积木 bottom 开始，必须 将其作为金字塔的底部。
 * 在给定 bottom 和 allowed 的情况下，如果你能一直构建到金字塔顶部，使金字塔中的 每个三角形图案 都是在 allowed 中的，则返回 true ，否则返回 false 。
 * <p>
 * 示例 1：
 * 输入：bottom = "BCD", allowed = ["BCC","CDE","CEA","FFF"]
 * 输出：true
 * 解释：允许的三角形图案显示在右边。
 * 从最底层(第 3 层)开始，我们可以在第 2 层构建“CE”，然后在第 1 层构建“A”。
 * 金字塔中有三种三角形图案，分别是 “BCC”、“CDE” 和 “CEA”。都是允许的。
 * <p>
 * 示例 2：
 * 输入：bottom = "AAAA", allowed = ["AAB","AAC","BCD","BBE","DEF"]
 * 输出：false
 * 解释：允许的三角形图案显示在右边。
 * 从最底层(即第 4 层)开始，创造第 3 层有多种方法，但如果尝试所有可能性，你便会在创造第 1 层前陷入困境。
 * <p>
 * 提示：
 * 2 <= bottom.length <= 6
 * 0 <= allowed.length <= 216
 * allowed[i].length == 3
 * 所有输入字符串中的字母来自集合 {'A', 'B', 'C', 'D', 'E', 'F'}。
 * allowed 中所有值都是 唯一的
 * <p>
 * Tag：回溯  剪枝  哈希
 */
public class PyramidTransitionMatrix implements Answer {
    public boolean result = false;

    public static void main(String[] args) {
        new PyramidTransitionMatrix().answer();
    }

    @Override
    public void answer() {
        // String bottom = "BCD";
        // List<String> allowed = Arrays.asList("BCC", "CDE", "CEA");

        String bottom = "DCAADB";
        List<String> allowed = Arrays.asList("AAD", "ACB", "ACA", "AAA", "AAC", "AAB", "BCC", "BAD", "BAC",
                "CAC", "CCD", "BDD", "CCA", "CAA", "CCC", "CAD", "DAA", "DAC", "DCD", "ACD", "DCB", "DCA", "CCB", "CBB", "ABD",
                "BDC", "BDB", "BBD", "BBB", "BBA", "ADD", "ADC", "ADA", "DDC", "DDA", "DDD", "CDD", "CBA", "CBD", "CDB", "CDC", "DBA", "DBC", "DBB", "BDA");
        System.out.println(pyramidTransition(bottom, allowed));
    }

    /**
     * 回溯
     * 第一版：超市，复杂度 (2^N)
     * 原因是重复计算
     * 直接把无法构造的错误数据，加入到一个错误记录表里
     */
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        // 层数
        int level = bottom.length();
        Map<String, List<String>> allowMap = new HashMap<>();
        for (String str : allowed) {
            String substring = str.substring(0, 2);
            if (allowMap.containsKey(substring)) {
                allowMap.get(substring).add(String.valueOf(str.charAt(2)));
            } else {
                List<String> tempList = new ArrayList<>();
                tempList.add(String.valueOf(str.charAt(2)));
                allowMap.put(substring, tempList);
            }
        }
        // 已经知道错误的，可以提前记录，提前终止
        Set<String> errorVis = new HashSet<>();
        myDiGui(allowMap, 0, bottom, new StringBuilder(), bottom.length(), errorVis);
        return result;
    }

    private void myDiGui(Map<String, List<String>> allowMap, int index, String thisLevel, StringBuilder nextLevel, int level, Set<String> errorVis) {
        // 超时就是因为没加这个，在我们这个架构中，由于return只是跳出方法，不是跳出递归，所以可能还会继续遍历。所以这里成功后就可以提前终止了
        if (result) {
            return;
        }
        if (level == 1 && thisLevel.length() == 1) {
            result = true;
            return;
        }
        if (errorVis.contains(nextLevel.toString())) {
            return;
        }
        if (thisLevel.isEmpty()) {
            return;
        }
        if (nextLevel.length() == level - 1) {
            // 需要下一层了
            myDiGui(allowMap, 0, nextLevel.toString(), new StringBuilder(), level - 1, errorVis);
        }
        if (index >= thisLevel.length() - 1) {
            return;
        }

        String substring = thisLevel.substring(index, index + 2);
        // 这里可以处理一下：获取的词需要跟上一个组合成允许的才行
        List<String> allowStrs = allowMap.get(substring);
        if (allowStrs == null) {
            errorVis.add(nextLevel.toString());
            return;
        } else {
            //  可能会出现已经走到最后位true了，但还是继续遍历的情况

            List<String> newAllowStrs = computeAllowChar(allowMap, allowStrs, nextLevel);
            for (String str : newAllowStrs) {
                nextLevel.append(str);
                myDiGui(allowMap, index + 1, thisLevel, nextLevel, level, errorVis);
                nextLevel.deleteCharAt(nextLevel.length() - 1);
            }
        }
    }

    public List<String> computeAllowChar(Map<String, List<String>> allowMap, List<String> allowStrs, StringBuilder nextLevel) {
        if (nextLevel.isEmpty()) {
            return allowStrs;
        }
        char last = nextLevel.charAt(nextLevel.length() - 1);
        return allowStrs.stream().filter(c -> allowMap.containsKey(last + c)).toList();
    }

    @Override
    public Object initData() {
        return null;
    }
}
