package com.xx.algorithm.greedy;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XuanXiao
 * @CreateDate 2023/12/26
 * <p>
 * 文本左右对齐
 * LeetCode  68.  Hard
 * <p>
 * 给定一个单词数组 words 和一个长度 maxWidth ，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 * 你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * <p>
 * 注意:
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 * <p>
 * 示例 1:
 * 输入: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * 输出:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * <p>
 * 示例 2:
 * 输入:words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * 输出:
 * [
 * "What   must   be",
 * "acknowledgment  ",
 * "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 * 因为最后一行应为左对齐，而不是左右两端对齐。
 * 第二行同样为左对齐，这是因为这行只包含一个单词。
 * <p>
 * 示例 3:
 * 输入:words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"]，maxWidth = 20
 * 输出:
 * [
 * "Science  is  what we",
 * "understand      well",
 * "enough to explain to",
 * "a  computer.  Art is",
 * "everything  else  we",
 * "do                  "
 * ]
 * <p>
 * 提示:
 * 1 <= words.length <= 300
 * 1 <= words[i].length <= 20
 * words[i] 由小写英文字母和符号组成
 * 1 <= maxWidth <= 100
 * words[i].length <= maxWidth
 * <p>
 * Tag：字符串拼接  贪心算法
 */
public class AlignTextLeftAndRight implements Answer {

    public static void main(String[] args) {
        new AlignTextLeftAndRight().answer();
    }


    @Override
    public void answer() {
        String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;

        //String[] words = new String[]{"What", "must", "be", "acknowledgment", "shall", "be"};
        //int maxWidth = 16;
        System.out.println(fullJustify(words, maxWidth));
    }

    /**
     * 先把单词都放到List，再考虑往中间插空格。
     * <p>
     * todo：可不可以直接边遍历，边插入空格？
     * 优化：速度仅超过7%
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<List<String>> list = new ArrayList<>();
        Map<Integer, Integer> lengthMap = new HashMap<>();
        List<String> tempList = null;
        int tempLength = 0;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (tempList == null) {
                tempList = new ArrayList<>();
                tempList.add(word);
                tempLength = word.length();
            } else {
                if (tempLength + tempList.size() + word.length() <= maxWidth) {
                    tempList.add(word);
                    tempLength = tempLength + word.length();
                } else {
                    list.add(tempList);
                    lengthMap.put(list.size() - 1, tempLength);
                    tempList = new ArrayList<>();
                    tempList.add(word);
                    tempLength = word.length();
                }
            }
            if (i == words.length - 1) {
                list.add(tempList);
                lengthMap.put(list.size() - 1, tempLength);
            }
        }
        return arrangeToString(list, lengthMap, maxWidth);
    }

    private List<String> arrangeToString(List<List<String>> list, Map<Integer, Integer> lengthMap, int maxWidth) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int totalLength = lengthMap.get(i);
            int totalBlank = maxWidth - totalLength;
            List<String> tempList = list.get(i);

            //文本的最后一行应为左对齐，且单词之间不插入额外的空格。
            if (i == list.size() - 1) {
                String str = String.join(" ", tempList);
                str = str + addBlank(maxWidth - str.length());
                result.add(str);
                break;
            }

            if (tempList.size() == 1) {
                String str = tempList.get(0) + addBlank(totalBlank);
                assert str.length() == maxWidth;
                result.add(str);
            }
            if (tempList.size() == 2) {
                String str = tempList.get(0) + addBlank(totalBlank) + tempList.get(1);
                assert str.length() == maxWidth;
                result.add(str);
            }
            if (tempList.size() > 2) {
                int gapBlank = totalBlank / (tempList.size() - 1);
                int remainder = totalBlank % (tempList.size() - 1);
                StringBuilder str = new StringBuilder();
                for (int j = 0; j < tempList.size(); j++) {
                    String temp = tempList.get(j);
                    str.append(temp);
                    if (j < tempList.size() - 1) {
                        if (j < remainder) {
                            str.append(addBlank(gapBlank + 1));
                        } else {
                            str.append(addBlank(gapBlank));
                        }
                    }
                }
                assert str.toString().length() == maxWidth;
                result.add(str.toString());
            }

        }
        return result;
    }

    //返回长度为 n 的由空格组成的字符串
    private String addBlank(int num) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            builder.append(' ');
        }
        return builder.toString();
    }

    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
