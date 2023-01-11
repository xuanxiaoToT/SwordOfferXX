package com.xx.basicDs.graph;

import com.xx.Answer;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 玄霄
 * @CreateDate 2022/12/22
 * 单词演变
 * <p>
 * 输入两个长度相同但内容不同的单词（beginWord和
 * endWord）和一个单词列表，求从beginWord到endWord的演变序列的
 * 最短长度，要求每步只能改变单词中的一个字母，并且演变过程中
 * 每步得到的单词都必须在给定的单词列表中。如果不能从beginWord
 * 演变到endWord，则返回0。假设所有单词只包含英文小写字母。
 * <p>
 * 例如，如果beginWord为"hit"，endWord为"cog"，单词列表为
 * ["hot"，"dot"，"dog"，"lot"，"log"，"cog"]，则演变序列的最短
 * 长度为5，一个可行的演变序列为"hit"→"hot"→"dot"→"dog"→"cog"。
 */
public class WordEvolution implements Answer {

    public static void main(String[] args) {
        new WordEvolution().answerTwo();
    }

    /**
     * 解1:类似求解树的深度
     */
    @Override
    public void answerOne() {
        String beginWord = "hit";
        String endWord = "cog";
        HashSet<String> hashSet = new HashSet<>();
        String[] data = initData();
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int length = 1;
        while (!queue.isEmpty()) {
            // 注意不能直接i < queue.size()  因为queue.size()会变化，导致for循环次数每次都会重新计算
            // ！！！！！错误点
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String pollStr = queue.poll();
                if (pollStr.equals(endWord)) {
                    return;
                }
                for (String dataStr : data) {
                    if (OneLetterApart(pollStr, dataStr) && !hashSet.contains(dataStr)) {
                        hashSet.add(dataStr);
                        queue.add(dataStr);
                        System.out.println(dataStr + "  " + length);
                    }
                }
            }
            length++;
        }
        System.out.println("done~!");
    }

    private boolean OneLetterApart(String str1, String str2) {
        int sum = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                sum++;
            }
        }
        return sum == 1;
    }

    /**
     * 双向广度优先搜索
     * 这个题目是关于单一起始节点、单一目标节点的最短距离问题。
     * 前面的解法是从起始节点出发不断朝着目标节点的方向搜索，直到到
     * 达目标节点。
     * <p>
     * 针对这类问题有一种常见的优化方法，即在从起始节点
     * 出发不断朝着目标节点的方向搜索的同时，也从目标节点出发不断朝
     * 着起始节点的方向搜索。这种双向搜索的方法能够缩小搜索空间，从
     * 而提高搜索的时间效率。
     * <p>
     * 两边各自用各自的visitedSet，当有重复集时，便是相遇的时候。这个时候算距离即可
     */
    private void answerTwo() {
        String beginWord = "hit";
        String endWord = "cog";
        HashSet<String> hashSetUp = new HashSet<>();
        HashSet<String> hashSetDown = new HashSet<>();
        // 放交集的地方
        HashSet<String> resSet = new HashSet<>();
        String[] data = initData();
        Queue<String> queueUp = new LinkedList<>();
        Queue<String> queueDown = new LinkedList<>();
        queueUp.add(beginWord);
        queueDown.add(endWord);
        int length = 1;
        while (!queueUp.isEmpty() && !queueDown.isEmpty()) {
            int size = queueUp.size();
            for (int i = 0; i < size; i++) {
                String pollUpStr = queueUp.poll();
                String pollDownStr = queueDown.poll();
                for (String dataStr : data) {
                    if (OneLetterApart(pollUpStr, dataStr) && !hashSetUp.contains(dataStr)) {
                        hashSetUp.add(dataStr);
                        queueUp.add(dataStr);
                        // System.out.println(dataStr + "  " + length);
                    }
                }
                for (String dataStr : data) {
                    if (OneLetterApart(pollDownStr, dataStr) && !hashSetDown.contains(dataStr)) {
                        hashSetDown.add(dataStr);
                        queueDown.add(dataStr);
                        // System.out.println(dataStr + "  " + length);
                    }
                }
                resSet.addAll(hashSetUp);
                resSet.retainAll(hashSetDown);
                if (!resSet.isEmpty()) {
                    System.out.println(hashSetUp);
                    System.out.println(hashSetDown);
                    System.out.println(length);
                    return;
                }
            }
            length++;
        }
    }

    /**
     * 输入数据
     */
    @Override
    public String[] initData() {
        return new String[]{"hot", "dot", "dog", "lot", "log", "cog"};
    }
}