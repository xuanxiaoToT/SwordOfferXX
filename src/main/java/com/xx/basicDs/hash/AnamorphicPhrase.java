package com.xx.basicDs.hash;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/31
 * 变位词组
 * <p>
 * 给定一组单词，请将它们按照变位词分组。
 * <p>
 * 例如，输入
 * 一组单词["eat"，"tea"，"tan"，"ate"，"nat"，"bat"]，这组单
 * 词可以分成3组，分别是["eat"，"tea"，"ate"]、["tan"，"nat"]
 * 和["bat"]。假设单词中只包含英文小写字母。
 */
public class AnamorphicPhrase implements Answer {

    public static void main(String[] args) {
        new AnamorphicPhrase().answerOne();
    }

    /**
     * 同样的，用一个数组来模拟map，或者直接用map。
     * 然后相同的变位词分为一组。
     * <p>
     * 2.先按照子母顺序给每个排序，然后再对整体排序，最后再分组。
     */
    @Override
    public void answerOne() {
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
