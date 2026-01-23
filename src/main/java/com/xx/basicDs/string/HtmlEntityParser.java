package com.xx.basicDs.string;

import com.xx.Answer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/23
 * <p>
 * HTML实体解析器
 * LeetCode  1410.  Medium
 * <p>
 * 「HTML 实体解析器」 是一种特殊的解析器，它将 HTML 代码作为输入，并用字符本身替换掉所有这些特殊的字符实体。
 * HTML 里这些特殊字符和它们对应的字符实体包括：
 * <p>
 * 双引号：字符实体为 &quot; ，对应的字符是 " 。
 * 单引号：字符实体为 &apos; ，对应的字符是 ' 。
 * 与符号：字符实体为 &amp; ，对应对的字符是 & 。
 * 大于号：字符实体为 &gt; ，对应的字符是 > 。
 * 小于号：字符实体为 &lt; ，对应的字符是 < 。
 * 斜线号：字符实体为 &frasl; ，对应的字符是 / 。
 * 给你输入字符串 text ，请你实现一个 HTML 实体解析器，返回解析器解析后的结果。
 * <p>
 * <p>
 * 示例 1：
 * 输入：text = "&amp; is an HTML entity but &ambassador; is not."
 * 输出："& is an HTML entity but &ambassador; is not."
 * 解释：解析器把字符实体 &amp; 用 & 替换
 * <p>
 * 示例 2：
 * 输入：text = "and I quote: &quot;...&quot;"
 * 输出："and I quote: \"...\""
 * <p>
 * 示例 3：
 * 输入：text = "Stay home! Practice on Leetcode :)"
 * 输出："Stay home! Practice on Leetcode :)"
 * <p>
 * 示例 4：
 * 输入：text = "x &gt; y &amp;&amp; x &lt; y is always false"
 * 输出："x > y && x < y is always false"
 * <p>
 * 示例 5：
 * 输入：text = "leetcode.com&frasl;problemset&frasl;all"
 * 输出："leetcode.com/problemset/all"
 * <p>
 * <p>
 * 提示：
 * 1 <= text.length <= 10^5
 * 字符串可能包含 256 个ASCII 字符中的任意字符。
 * <p>
 * Tag：字符串  字符串替换
 */
public class HtmlEntityParser implements Answer {

    public static void main(String[] args) {
        new HtmlEntityParser().answer();
    }

    @Override
    public void answer() {
        String input = "&&&&&&";
        System.out.println(entityParser(input));
    }

    /**
     * 「字符实体」都是由 & 开头的，所以我们只需要遍历一遍字符串，遇到 &后开始计算
     * 假设一个「字符实体」为 e，对应的字符为 c，那么可以通过判断 pos 位置开始，
     * 长度和 e 相同的子串是否和 e 相等，如果相等就可以替换。
     */
    public String entityParser(String text) {
        Map<String, String> map = initMap();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c != '&') {
                sb.append(c);
            } else {
                int end = i;
                String tempReplace = "";
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    if (i + key.length() <= text.length()) {
                        String tempSub = text.substring(i, i + key.length());
                        if (tempSub.equals(key)) {
                            end = i + key.length();
                            tempReplace = entry.getValue();
                            break;
                        }
                    }
                }
                i = end > i ? end - 1 : i;
                if (!tempReplace.isEmpty()) {
                    sb.append(tempReplace);
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    private Map<String, String> initMap() {
        Map<String, String> map = new HashMap<>(6);
        map.put("&quot;", "\"");
        map.put("&apos;", "'");
        map.put("&amp;", "&");
        map.put("&gt;", ">");
        map.put("&lt;", "<");
        map.put("&frasl;", "/");
        return map;
    }

    @Override
    public Object initData() {
        return null;
    }
}