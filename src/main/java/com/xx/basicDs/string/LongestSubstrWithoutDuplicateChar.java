package com.xx.basicDs.string;

import com.xx.Answer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/8
 * LeetCode 003
 * 不含重复字符的最长子字符串
 * <p>
 * 输入一个字符串，求该字符串中不含重复字符的最长子
 * 字符串的长度。例如，输入字符串"babcca"，其最长的不含重复字
 * 符的子字符串是"abc"，长度为3。
 * <p>
 * 举一反三：
 * 输入两个字符串s和t，请找出字符串s中包含字符串t的
 * 所有字符的最短子字符串。例如，输入的字符串s为"ADDBANCAD"，
 * 字符串t为"ABC"，则字符串s中包含字符'A'、'B'和'C'的最短子字
 * 符串是"BANC"。如果不存在符合条件的子字符串，则返回空字符
 * 串""。如果存在多个符合条件的子字符串，则返回任意一个。
 * <p>
 * 同样可以用双指针的方式来进行计算。只需要修改方法whetherDistinct即可，
 * 改为判断是否全部包含t即可。
 */
public class LongestSubstrWithoutDuplicateChar implements Answer {

    private int max = 0;

    public static void main(String[] args) {
        new LongestSubstrWithoutDuplicateChar().answerOne();
    }

    /**
     * 使用双指针 滑动窗口 法
     */
    @Override
    public void answerOne() {
        String oriStr = initData();
        int left = 0;
        int right = 0;
        Map<Character, Integer> map = new HashMap<>(26);
        map.put(oriStr.charAt(left), 1);
        while (right < oriStr.length() && left <= right) {
            // 缺点：每次都需要扫描map
            // 解决办法：用一个总变量来替代，如果map.containsKey为true，则此变量+1
            // 每次需要
            if (whetherDistinct(map)) {
                System.out.println(left + "  " + right);
                if (map.size() > max) {
                    max = map.size();
                }
                right++;
                mapPut(map, oriStr, right);
            } else {
                map.put(oriStr.charAt(left), map.get(oriStr.charAt(left)) - 1);
                if (map.get(oriStr.charAt(left)) == 0) {
                    map.remove(oriStr.charAt(left));
                }
                left++;
            }
        }
        System.out.println(max);
    }


    private void mapPut(Map<Character, Integer> map, String oriStr, int index) {
        if (index >= oriStr.length()) {
            return;
        }
        if (map.containsKey(oriStr.charAt(index))) {
            map.put(oriStr.charAt(index), map.get(oriStr.charAt(index)) + 1);
        } else {
            map.put(oriStr.charAt(index), 1);
        }
    }

    private boolean whetherDistinct(Map<Character, Integer> map) {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * something
     */
    @Override
    public String initData() {
        return "babcdefgcbaa";
    }
}