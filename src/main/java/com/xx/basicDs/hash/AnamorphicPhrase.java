package com.xx.basicDs.hash;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/31
 * <p>
 * 变位词组
 * LeetCode 49 字母异位词分组
 * <p>
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 * 假设单词中只包含英文小写字母。
 * <p>
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */
public class AnamorphicPhrase implements Answer {

    public static void main(String[] args) {
        new AnamorphicPhrase().answer();
    }

    /**
     * 同样的，用一个数组来模拟map，或者直接用map。
     * 然后相同的变位词分为一组。
     * <p>
     * 2.先按照子母顺序给每个排序，然后再对整体排序，最后再分组。
     */
    @Override
    public void answer() {
        Map<Map<Character, Integer>, List<String>> mapIntegerMap = new HashMap<>();
        String[] data = initData();
        for (String datum : data) {
            Map<Character, Integer> tempMap = new HashMap<>();
            for (int i = 0; i < datum.length(); i++) {
                char c = datum.charAt(i);
                tempMap.put(c, tempMap.get(c) == null ? 1 : tempMap.get(c) + 1);
            }
            if (mapIntegerMap.containsKey(tempMap)) {
                mapIntegerMap.get(tempMap).add(datum);
            } else {
                List<String> tempList = new ArrayList<>();
                tempList.add(datum);
                mapIntegerMap.put(tempMap, tempList);
            }
        }
        System.out.println(mapIntegerMap);
    }

    /**
     * something
     */
    @Override
    public String[] initData() {
        return new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
    }
}
