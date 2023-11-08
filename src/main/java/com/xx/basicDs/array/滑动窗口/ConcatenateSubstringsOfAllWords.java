package com.xx.basicDs.array.滑动窗口;

import com.xx.Answer;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/7
 * <p>
 * 串联所有单词的子串
 * LeetCode 30  Hard
 * <p>
 * 给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
 * s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
 * 例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。
 * "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
 * 返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案。
 * <p>
 * 示例 1：
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：因为 words.length == 2 同时 words[i].length == 3，连接的子字符串的长度必须为 6。
 * 子串 "barfoo" 开始位置是 0。它是 words 中以 ["bar","foo"] 顺序排列的连接。
 * 子串 "foobar" 开始位置是 9。它是 words 中以 ["foo","bar"] 顺序排列的连接。
 * 输出顺序无关紧要。返回 [9,0] 也是可以的。
 * <p>
 * 示例 2：
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 * 解释：因为 words.length == 4 并且 words[i].length == 4，所以串联子串的长度必须为 16。
 * s 中没有子串长度为 16 并且等于 words 的任何顺序排列的连接。
 * 所以我们返回一个空数组。
 * <p>
 * 示例 3：
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 * 解释：因为 words.length == 3 并且 words[i].length == 3，所以串联子串的长度必须为 9。
 * 子串 "foobarthe" 开始位置是 6。它是 words 中以 ["foo","bar","the"] 顺序排列的连接。
 * 子串 "barthefoo" 开始位置是 9。它是 words 中以 ["bar","the","foo"] 顺序排列的连接。
 * 子串 "thefoobar" 开始位置是 12。它是 words 中以 ["the","foo","bar"] 顺序排列的连接。
 * <p>
 * 提示：
 * 1 <= s.length <= 10^4
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] 和 s 由小写英文字母组成
 * <p>
 * Tag：滑动窗口 字符串  哈希
 */
public class ConcatenateSubstringsOfAllWords implements Answer {

    public static void main(String[] args) {
        new ConcatenateSubstringsOfAllWords().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        //String s = "barfoothefoobarman";
        //String[] words = new String[]{"foo", "bar"};
        //String s = "wordgoodgoodgoodbestword";
        //String[] words = new String[]{"word","good","best","word"};
        //String s = "barfoofoobarthefoobarman";
        //String[] words = new String[]{"bar", "foo", "the"};
        //String s = "wordgoodgoodgoodbestword";
        //String[] words = new String[]{"word", "good", "best", "good"};
        String s = "ababababab";
        String[] words = new String[]{"ababa", "babab"};
        System.out.println(findSubstring(s, words));
    }


    /**
     * 官解：采用滑动窗口，每次滑动长度n
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> trueMap = Arrays.stream(words).collect(Collectors.toMap(Function.identity(), str -> 1, (o, n) -> o + 1));
        int n = words[0].length();
        int maxLen = n * words.length;
        //出错点，先进行参数校验！
        if (s.length() < maxLen) {
            return result;
        }
        for (int i = 0; i < n; i++) {
            int left = i;
            int right = i + maxLen;
            if (right > s.length()) {
                return result;
            }
            //初始化map，仅第一次需要。
            //出错点:注意使用substring时，先判断是否越界
            Map<String, Integer> subMap = initMap(s.substring(left, right), n);
            while (right <= s.length()) {
                //进行map的比较，如果相同则表明这个子串符合。
                if (compareMap(trueMap, subMap)) {
                    result.add(left);
                }
                //滑动窗口，左侧要丢弃的单词。
                String removeLeftWord = s.substring(left, left + n);
                subMap.put(removeLeftWord, subMap.get(removeLeftWord) - 1);
                if (subMap.get(removeLeftWord) == 0) {
                    subMap.remove(removeLeftWord);
                }
                left = left + n;
                //滑动窗口，右侧要加入的单词。
                if (right + n <= s.length()) {
                    String nextRightWord = s.substring(right, right + n);
                    if (subMap.containsKey(nextRightWord)) {
                        subMap.put(nextRightWord, subMap.get(nextRightWord) + 1);
                    } else {
                        subMap.put(nextRightWord, 1);
                    }
                }
                right = right + n;
            }
        }
        return result;
    }

    private boolean compareMap(Map<String, Integer> trueMap, Map<String, Integer> subMap) {
        if (trueMap.size() != subMap.size()) {
            return false;
        }
        for (Map.Entry<String, Integer> entry : trueMap.entrySet()) {
            String key = entry.getKey();
            if (!subMap.containsKey(key) || !Objects.equals(subMap.get(key), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    private Map<String, Integer> initMap(String s, int n) {
        Map<String, Integer> subMap = new HashMap<>();
        int left = 0;
        int right = n;
        while (right <= s.length()) {
            String temp = s.substring(left, right);
            if (subMap.containsKey(temp)) {
                subMap.put(temp, subMap.get(temp) + 1);
            } else {
                subMap.put(temp, 1);
            }
            left += n;
            right += n;
        }
        return subMap;
    }

    /**
     * 刚开始写出来的方法，超时
     */
    public List<Integer> findSubstringMy(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        Set<String> set = Arrays.stream(words).collect(Collectors.toSet());
        int n = words[0].length();
        int maxLen = words[0].length() * words.length;
        int left = 0;
        int right = maxLen - 1;
        while (right < s.length()) {
            String firstWord = s.substring(left, right + 1);
            if (preliminaryJudgment(firstWord, set, n)) {
                if (isValid(s.substring(left, right + 1), words)) {
                    result.add(left);
                }
            }
            left++;
            right++;
        }
        return result;
    }

    private boolean preliminaryJudgment(String subStr, Set<String> set, int n) {
        for (int i = 0; i < subStr.length(); i += n) {
            if (!set.contains(subStr.substring(i, i + n))) {
                return false;
            }
        }
        return true;
    }


    private boolean isValid(String subStr, String[] words) {
        Map<String, Integer> map = Arrays.stream(words).collect(Collectors.toMap(Function.identity(), str -> 1, (o, n) -> o + 1));
        int start = 0;
        int n = words[0].length();
        do {
            String temp = subStr.substring(start, start + n);
            if (map.containsKey(temp)) {
                start = start + n;
                if (map.get(temp) == 1) {
                    map.remove(temp);
                } else {
                    map.put(temp, map.get(temp) - 1);
                }
            } else {
                return false;
            }
        } while (!map.isEmpty() && start < subStr.length());
        return true;
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
