package com.xx.basicDs.tree;

import com.xx.domain.TrieNode;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/12
 * <p>
 * 实现 Trie (前缀树)
 * LeetCode 208.
 * <p>
 * 请设计实现一棵前缀树Trie，它有如下操作。
 * ● 函数insert，在前缀树中添加一个字符串。
 * ● 函数search，查找字符串。如果前缀树中包含该字符串，则返回
 * true；否则返回false。
 * ● 函数startWith，查找字符串前缀。如果前缀树中包含以该前缀开
 * 头的字符串，则返回true；否则返回false。
 * <p>
 * 示例：
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 */
public class TrieDemo {

    public static void main(String[] args) {
        TrieDemo trieDemo = new TrieDemo();
        trieDemo.insert("abc");
        trieDemo.insert("abd");

        System.out.println(trieDemo.search("abe"));
        System.out.println(trieDemo.startWith("ab"));
    }

    public TrieNode root = new TrieNode();

    public void insert(String str) {
        TrieNode last = root;
        for (Character c : str.toCharArray()) {
            if (last.getChildNodeList()[c - 'a'] == null) {
                last.getChildNodeList()[c - 'a'] = new TrieNode(c);
            }
            last = last.getChildNodeList()[c - 'a'];
        }
        last.setWordEndFlag(true);
    }

    public boolean search(String str) {
        TrieNode last = root;
        for (Character character : str.toCharArray()) {
            if (last.getChildNodeList()[character - 'a'] == null) {
                return false;
            }
            last = last.getChildNodeList()[character - 'a'];
        }

        return last.isWordEndFlag();
    }

    public boolean startWith(String str) {
        TrieNode last = root;
        for (Character character : str.toCharArray()) {
            if (last.getChildNodeList()[character - 'a'] == null) {
                return false;
            }
            last = last.getChildNodeList()[character - 'a'];
        }
        return true;
    }
}