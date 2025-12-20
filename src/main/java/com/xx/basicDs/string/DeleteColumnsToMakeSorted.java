package com.xx.basicDs.string;

import com.xx.Answer;

import java.util.HashSet;
import java.util.Set;

/**
 * 删列造序
 * LeetCode 944  Easy
 * <p>
 * 给你由 n 个小写字母字符串组成的数组 strs，其中每个字符串长度相等。
 * 这些字符串可以每个一行，排成一个网格。例如，strs = ["abc", "bce", "cae"] 可以排列为：
 * abc
 * bce
 * cae
 * 你需要找出并删除 不是按字典序非严格递增排列的 列。在上面的例子（下标从 0 开始）中，
 * 列 0（'a', 'b', 'c'）和列 2（'c', 'e', 'e'）都是按字典序非严格递增排列的，而列 1（'b', 'c', 'a'）不是，所以要删除列 1 。
 * 返回你需要删除的列数。
 * <p>
 * 示例 1：
 * 输入：strs = ["cba","daf","ghi"]
 * 输出：1
 * 解释：网格示意如下：
 * cba
 * daf
 * ghi
 * 列 0 和列 2 按升序排列，但列 1 不是，所以只需要删除列 1 。
 * <p>
 * 示例 2：
 * 输入：strs = ["a","b"]
 * 输出：0
 * 解释：网格示意如下：
 * a
 * b
 * 只有列 0 这一列，且已经按升序排列，所以不用删除任何列。
 * <p>
 * 示例 3：
 * 输入：strs = ["zyx","wvu","tsr"]
 * 输出：3
 * 解释：网格示意如下：
 * zyx
 * wvu
 * tsr
 * 所有 3 列都是非升序排列的，所以都要删除。
 * <p>
 * 提示：
 * n == strs.length
 * 1 <= n <= 100
 * 1 <= strs[i].length <= 1000
 * strs[i] 由小写英文字母组成
 */
public class DeleteColumnsToMakeSorted implements Answer {

    public static void main(String[] args) {
        String[] strs = new String[]{"rrjk", "furt", "guzm"};

        DeleteColumnsToMakeSorted ans = new DeleteColumnsToMakeSorted();
        System.out.println(ans.minDeletionSize(strs));
    }

    /**
     * 按照列来遍历
     * 速度最快，击败100%
     */
    public int minDeletionSize(String[] strs) {
        if (strs.length == 1)
            return 0;
        int result = 0;
        for (int i = 0; i < strs[0].length(); i++) {
            char last = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                char current = strs[j].charAt(i);
                if (current < last) {
                    result++;
                    break;
                } else {
                    last = current;
                }
            }
        }
        return result;
    }

    /**
     * 按照单词遍历
     */
    public int minDeletionSize2(String[] strs) {

        String last = strs[0];
        // 缺点：需要记录遍历过的列数
        Set<Integer> hasError = new HashSet<>();

        for (int j = 1; j < strs.length; j++) {
            String str = strs[j];
            compareStr(last, str, hasError);
            last = strs[j];
        }
        return hasError.size();
    }

    private void compareStr(String last, String str, Set<Integer> hasError) {
        for (int i = 0; i < last.length(); i++) {
            if (hasError.contains(i)) {
                continue;
            }
            char lastChar = last.charAt(i);
            char currentChar = str.charAt(i);
            if (currentChar < lastChar) {
                hasError.add(i);
            }
        }
    }

    @Override
    public void answerOne() {

    }

    @Override
    public Object initData() {
        return null;
    }
}
