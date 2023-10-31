package com.xx.basicDs.tree.dictionaryTree;

import com.xx.domain.TrieNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/30
 * <p>
 * 添加与搜索单词 - 数据结构设计
 * LeetCode  211. Medium
 * <p>
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * 实现词典类 WordDictionary ：
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。
 * word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 * <p>
 * 示例：
 * 输入：
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * 输出：
 * [null,null,null,null,false,true,true,true]
 * <p>
 * 解释：
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // 返回 False
 * wordDictionary.search("bad"); // 返回 True
 * wordDictionary.search(".ad"); // 返回 True
 * wordDictionary.search("b.."); // 返回 True
 * <p>
 * 提示：
 * 1 <= word.length <= 25
 * addWord 中的 word 由小写英文字母组成
 * search 中的 word 由 '.' 或小写英文字母组成
 * 最多调用 104 次 addWord 和 search
 */
public class AddAndSearchForWords {

    public static void main(String[] args) {
        AddAndSearchForWords wordDictionary = new AddAndSearchForWords();

        wordDictionary.addWord("abcde");
        wordDictionary.addWord("abcdf");
        wordDictionary.addWord("abcgf");
        wordDictionary.addWord("abcd");
        wordDictionary.addWord("abcas");
        wordDictionary.addWord("abcf");

        //System.out.println(wordDictionary.search("pad"));
        //System.out.println(wordDictionary.search("bad"));
        //System.out.println(wordDictionary.search(".ad"));
        System.out.println(wordDictionary.search("abcd"));
    }

    private final TrieNode root;

    public AddAndSearchForWords() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode last = root;
        for (Character c : word.toCharArray()) {
            if (last.childNodeList[c - 'a'] == null) {
                last.childNodeList[c - 'a'] = new TrieNode(c);
            }
            last = last.childNodeList[c - 'a'];
        }
        last.wordEndFlag = true;
    }

    /**
     * todo:fix
     */
    public boolean search(String word) {
        TrieNode last = root;
        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(last);
        int index = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int falseCount = 0;
            char c = word.charAt(index);
            for (int i = 0; i < size; i++) {
                TrieNode poll = queue.poll();
                if (c != '.') {
                    if (poll.childNodeList[c - 'a'] == null) {
                        falseCount++;
                    } else {
                        if (index == word.length() - 1) {
                            return poll.childNodeList[c - 'a'].wordEndFlag;
                        }
                        queue.add(poll.childNodeList[c - 'a']);
                    }
                } else {
                    if (index == word.length() - 1) {
                        for (TrieNode trieNode : poll.childNodeList) {
                            if (trieNode != null && trieNode.wordEndFlag) {
                                return true;
                            }
                        }
                        return false;
                    } else {
                        for (TrieNode trieNode : poll.childNodeList) {
                            if (trieNode != null) {
                                queue.add(trieNode);
                            }
                        }
                    }
                }
            }
            if (falseCount == size) {
                return false;
            }
            index++;
        }
        return false;
    }
}
