package com.xx.basicDs.tree.dictionaryTree;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/29
 * <p>
 * 搜索推荐系统
 * LeetCode  1268.  Medium
 * <p>
 * 给你一个产品数组 products 和一个字符串 searchWord ，products  数组中每个产品都是一个字符串。
 * 请你设计一个推荐系统，在依次输入单词 searchWord 的每一个字母后，推荐 products 数组中前缀与 searchWord 相同的最多三个产品。如果前缀相同的可推荐产品超过三个，请按字典序返回最小的三个。
 * 请你以二维列表的形式，返回在输入 searchWord 每个字母后相应的推荐产品的列表。
 * <p>
 * 示例 1：
 * 输入：products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * 输出：[
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * 解释：按字典序排序后的产品列表是 ["mobile","moneypot","monitor","mouse","mousepad"]
 * 输入 m 和 mo，由于所有产品的前缀都相同，所以系统返回字典序最小的三个产品 ["mobile","moneypot","monitor"]
 * 输入 mou， mous 和 mouse 后系统都返回 ["mouse","mousepad"]
 * <p>
 * 示例 2：
 * 输入：products = ["havana"], searchWord = "havana"
 * 输出：[["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * <p>
 * 示例 3：
 * 输入：products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 * 输出：[["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 * <p>
 * 示例 4：
 * 输入：products = ["havana"], searchWord = "tatiana"
 * 输出：[[],[],[],[],[],[],[]]
 * <p>
 * 提示：
 * 1 <= products.length <= 1000
 * 1 <= Σ products[i].length <= 2 * 10^4
 * products[i] 中所有的字符都是小写英文字母。
 * 1 <= searchWord.length <= 1000
 * searchWord 中所有字符都是小写英文字母。
 * <p>
 * Tag: 字典树  二分查找
 */
public class SearchRecommendationSystem implements Answer {
    public static void main(String[] args) {
        new SearchRecommendationSystem().answerOne();
    }

    @Override
    public void answerOne() {
        String[] products = {"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        String searchWord = "mouse";
        System.out.println(suggestedProducts(products, searchWord));
    }

    public TrieNode root = new TrieNode();

    /**
     * 字典树法
     */
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();
        for (String product : products) {
            insert(product);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : searchWord.toCharArray()) {
            sb.append(c);
            List<String> list = generateProduct(sb.toString());
            // list.sort(String::compareTo);
            result.add(list);
        }
        return result;
    }

    /**
     * 二分查找法
     * <p>
     * 如果我们直接将数组 products 中的所有字符串按照字典序进行排序，那么对于输入单词 searchWord 的某个前缀 prefix，
     * 我们只需要在排完序的数组中找到最小的三个字典序大于等于 prefix 的字符串，并依次判断它们是否以 prefix 为前缀即可。
     * 由于在排完序的数组中，以 prefix 为前缀的字符串的位置一定是连续的，因此我们可以使用二分查找，
     * 找出最小的字典序大于等于 prefix 的字符串 products[i]，
     * 并依次判断 products[i]，products[i + 1] 和 products[i + 2] 是否以 prefix 为前缀即可。
     */
    public List<List<String>> suggestedProducts2(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> ans = new ArrayList<>();
        int n = searchWord.length();
        int left = 0;
        int right = products.length;
        for (int i = 0; i < n; i++) {
            char c = searchWord.charAt(i);
            while (left < right && (products[left].length() <= i || products[left].charAt(i) < c)) {
                ++left;
            }
            while (left < right && products[right - 1].length() > i && products[right - 1].charAt(i) > c) {
                --right;
            }
            List<String> curr = new ArrayList<>();
            for (int j = left; j < right && j < left + 3; j++) {
                curr.add(products[j]);
            }
            ans.add(curr);

        }
        return ans;
    }

    /**
     * 简单粗暴直接遍历法
     */
    public List<List<String>> suggestedProducts3(String[] products, String searchWord) {
        Arrays.sort(products); // 先按字典序排序
        List<List<String>> ls = new ArrayList<>();
        for (int i = 1; i <= searchWord.length(); ++i) {
            String s = searchWord.substring(0, i); // 找出每个前缀
            List<String> l1 = new ArrayList<>();
            for (String str : products) {
                if (str.startsWith(s)) { // 将符合的前缀加到集合中
                    l1.add(str);
                }
                if (l1.size() == 3) { // 如果集合长度为3，就结束循环，不在继续寻找
                    break;
                }
            }
            ls.add(l1);
        }
        return ls;
    }

    public void insert(String str) {
        TrieNode last = root;
        for (Character c : str.toCharArray()) {
            if (last.childNodeList[c - 'a'] == null) {
                last.childNodeList[c - 'a'] = new TrieNode(c);
            }
            last = last.childNodeList[c - 'a'];
        }
        last.wordEndFlag = true;
        last.endWord = str;
    }

    public List<String> generateProduct(String searchWord) {
        List<String> list = new ArrayList<>();
        TrieNode last = root;
        for (Character character : searchWord.toCharArray()) {
            if (last.childNodeList[character - 'a'] == null) {
                return list;
            }
            last = last.childNodeList[character - 'a'];
        }
        //改为深度优先
        myDfs(last, list);
        return list;
    }

    public void myDfs(TrieNode trieNode, List<String> list) {
        if (list.size() >= 3) {
            return;
        }
        if (trieNode.wordEndFlag) {
            list.add(trieNode.endWord);
        }
        for (TrieNode next : trieNode.childNodeList) {
            if (next != null) {
                myDfs(next, list);
            }
        }
    }

    public class TrieNode {
        public Character value;
        public String endWord;
        public TrieNode[] childNodeList;
        public boolean wordEndFlag = false;

        public TrieNode() {
            this.value = null;
            this.childNodeList = new TrieNode[26];
        }

        public TrieNode(Character character) {
            this.value = character;
            this.childNodeList = new TrieNode[26];
            this.wordEndFlag = false;
        }
    }

    @Override
    public Object initData() {
        return null;
    }
}