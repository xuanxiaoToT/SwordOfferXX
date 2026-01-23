package com.xx.algorithm.backTracking;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2022/11/24
 * <p>
 * 分割回文子字符串
 * LeetCode 131. 分割回文串
 * <p>
 * 输入一个字符串，要求将它分割成若干子字符串，使每
 * 个子字符串都是回文。请列出所有可能的分割方法。
 * <p>
 * 例如，输入"google"，将输出3种符合条件的分割方法，分别是
 * ["g"，"o"，"o"，"g"，"l"，"e"]、["g"，"oo"，"g"，"l"，"e"]
 * 和["goog"，"l"，"e"]。
 * <p>
 * 示例 1：
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * <p>
 * 示例 2：
 * 输入：s = "a"
 * 输出：[["a"]]
 */
public class SplitPalindromeSubstring implements Answer {

    public static void main(String[] args) {
        new SplitPalindromeSubstring().answer();
    }

    /**
     * 1.用普通回溯法做，其本质就是找出所有的回文子串。
     */
    @Override
    public void answer() {
        String initData = initData();
        List<List<String>> result = new ArrayList<>();
        diGui(initData, 0, new ArrayList<>(), result);
        System.out.println(result);
    }

    private void diGui(String str, int start, List<String> temp, List<List<String>> result) {
        if (start == str.length()) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < str.length(); i++) {
            if (isHuiWen(str, start, i)) {
                temp.add(str.substring(start, i + 1));
                diGui(str, i + 1, temp, result);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean isHuiWen(String data, int start, int end) {
        while (start < end) {
            if (data.charAt(start) != data.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    /**
     * something
     */
    @Override
    public String initData() {
        return "google";
    }
}