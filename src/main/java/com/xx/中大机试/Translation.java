package com.xx.中大机试;

import java.util.HashMap;
import java.util.Map;

/**
 * 翻译
 * <p>
 * 主要考察输入、输出
 */
public class Translation {

    public Map<String, String> map = new HashMap<>();

    public static void main(String[] args) {
        Translation translation = new Translation();
    }

    public String getTranslationResult(String word) {

        if (map.containsKey(word)) {
            return map.get(word);
        }
        return "eh";
    }


}
