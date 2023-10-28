package com.xx.basicDs.graph;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/28
 * <p>
 * 单词接龙
 * LeetCode 127. Hard
 * <p>
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
 * 每一对相邻的单词只差一个字母。
 * 对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。
 * 如果不存在这样的转换序列，返回 0 。
 * <p>
 * 示例 1：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * <p>
 * 示例 2：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 * <p>
 * 提示：
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有字符串 互不相同
 */
public class WordChain implements Answer {

    public static void main(String[] args) {
        new WordChain().answerOne();
    }

    /**
     * 解1：还是广度优先遍历的模板
     * 思路同：
     * {@link WordEvolution}
     * {@link MinimalGeneticChanges}
     */
    @Override
    public void answerOne() {
        List<String> wordList = initData();
        String beginWord = "hit", endWord = "cog";
        System.out.println(ladderLength(beginWord, endWord, wordList));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        HashSet<String> hasVisited = new HashSet<>();
        int step = 1;
        queue.add(beginWord);
        hasVisited.add(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                if (poll.equals(endWord)) {
                    return step;
                }
                List<String> nextWordList = getNextWordList(poll, hasVisited, wordList);
                for (String nextWord : nextWordList) {
                    queue.add(nextWord);
                    hasVisited.add(nextWord);
                }
            }
            step++;
        }
        return 0;
    }

    private List<String> getNextWordList(String poll, HashSet<String> hasVisited, List<String> wordList) {
        List<String> result = new ArrayList<>();
        for (String word : wordList) {
            if (!hasVisited.contains(word)) {
                int diff = 0;
                for (int i = 0; i < poll.length(); i++) {
                    char pollChar = poll.charAt(i);
                    char wordChar = word.charAt(i);
                    if (pollChar != wordChar) {
                        diff++;
                    }
                }
                if (diff == 1) {
                    result.add(word);
                }
            }
        }
        return result;
    }

    /**
     * 输出数据
     */
    @Override
    public List<String> initData() {
        //return Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        return Arrays.asList("hot", "dot", "dog", "lot", "log");
    }
}
