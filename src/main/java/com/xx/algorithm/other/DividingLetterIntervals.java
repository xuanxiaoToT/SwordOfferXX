package com.xx.algorithm.other;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2023/9/13
 * <p>
 * 划分字母区间
 * LeetCode 763. Medium
 * <p>
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * 返回一个表示每个字符串片段的长度的列表。
 * <p>
 * 示例 1：
 * 输入：s = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
 * <p>
 * 示例 2：
 * 输入：s = "eccbbbbdec"
 * 输出：[10]
 * <p>
 * 提示：
 * 1 <= s.length <= 500
 * s 仅由小写英文字母组成
 */
public class DividingLetterIntervals implements Answer {
    public static void main(String[] args) {
        new DividingLetterIntervals().answerOne();
    }

    /**
     * 先计算该字母出现的最后位置，则这中间出现的字母，在计算其最后出现的位置的最大值。这个最大值就是应该断开的最右点。
     * fixme：可以将findMax方法省略掉。
     */
    @Override
    public void answerOne() {
        String data = initData();
        Map<Character, Integer> map = new HashMap<>();
        //计算每个字母出现的最远值。
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            map.put(c, i);
        }
        int left = 0;
        List<Integer> result = new ArrayList<>();
        while (left < data.length()) {
            char leftChar = data.charAt(left);
            int right = map.get(leftChar);
            int max = findMax(left, right, data, map);
            // 寻找此范围内的最大值，则说明此范围内有更远的字母需要处理。则循环判断是否有更远的
            while (max > right) {
                int tempRight = right;
                // 出现更远的，更新右侧的范围边界
                right = max;
                max = findMax(tempRight, max, data, map);
            }
            //范围确定
            result.add(max - left + 1);
            left = max + 1;
        }
        // System.out.println(Arrays.toString(dp));
        System.out.println(result);
    }

    private int findMax(int left, int right, String data, Map<Character, Integer> map) {
        int max = 0;
        for (int i = left; i <= right; i++) {
            char c = data.charAt(i);
            max = Math.max(max, map.get(c));
        }
        return max;
    }

    /**
     * 官解：
     * 由于同一个字母只能出现在同一个片段，显然同一个字母的第一次出现的下标位置和最后一次出现的下标位置必须出现在同一个片段。因此需要遍历字符串，得到每个字母最后一次出现的下标位置。
     * 在得到每个字母最后一次出现的下标位置之后，可以使用贪心的方法将字符串划分为尽可能多的片段，具体做法如下。
     * 从左到右遍历字符串，遍历的同时维护当前片段的开始下标 start 和结束下标 end，初始时 start=end=0。
     * 对于每个访问到的字母 c，得到当前字母的最后一次出现的下标位置 endC，则当前片段的结束下标一定不会小于 endC，因此令 end=max(end,endC)。
     * 当访问到下标 end 时，当前片段访问结束，当前片段的下标范围是 [start,end]，长度为end−start+1，将当前片段的长度添加到返回值，然后令start=end+1，继续寻找下一个片段。
     * 重复上述过程，直到遍历完字符串。
     * 上述做法使用贪心的思想寻找每个片段可能的最小结束下标，因此可以保证每个片段的长度一定是符合要求的最短长度，如果取更短的片段，则一定会出现同一个字母出现在多个片段中的情况。
     */
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<Integer>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }


    @Override
    public String initData() {
        // ababcbaca  defegde  hijhklij
        // return "ababcbacadefegdehijhklij";
        // aba  qwqw zxcvzx
        // return "abaqwqwzxcvzx";
        // return "eccbbdec";
        // return "abcdefga";
        return "qiejxqfnqceocmy";
    }
}
