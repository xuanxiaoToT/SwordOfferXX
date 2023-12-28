package com.xx.basicDs.tree.dictionaryTree;

import com.xx.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author XuanXiao
 * @CreateDate 2023/12/28
 * <p>
 * 单词搜索II
 * LeetCode 212  Hard
 * <p>
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 * <p>
 * 示例 1：
 * 输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * 输出：["eat","oath"]
 * <p>
 * 示例 2：
 * 输入：board = [["a","b"],["c","d"]], words = ["abcb"]
 * 输出：[]
 * <p>
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] 是一个小写英文字母
 * 1 <= words.length <= 3 * 10^4
 * 1 <= words[i].length <= 10
 * words[i] 由小写英文字母组成
 * words 中的所有字符串互不相同
 * <p>
 * 提示：求路径类的不能用广度优先遍历，因为无法确定哪些已经遍历
 * <p>
 * Tag：深度优先遍历  字典树  回溯  减枝
 */
public class WordSearchII implements Answer {

    public static void main(String[] args) {
        new WordSearchII().answerOne();
    }

    @Override
    public void answerOne() {
        char[][] board = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = new String[]{"oath", "pea", "eat", "rain"};
        System.out.println(findWords(board, words));
    }

    /**
     * 用字典树剪枝
     * fix: flag可以不需要，在原数组上修改。
     * 修改endFlag标志，可以做到去重的效果。
     */
    public List<String> findWords(char[][] board, String[] words) {
        //将words转换为字典树
        DicTree dicTree = new DicTree();
        for (String word : words) {
            dicTree.insert(word);
        }

        int m = board.length;
        int n = board[0].length;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                findPath(board, dicTree.root, i, j, new StringBuilder(), result);
            }
        }
        return result.stream().distinct().collect(Collectors.toList());
    }

    private void findPath(char[][] board, TreeNode lastNode, int i, int j, StringBuilder tempStr, List<String> result) {
        char c = board[i][j];
        TreeNode thisNode = lastNode.childNodeList[c - 'a'];
        if (board[i][j] == '#' || thisNode == null) {
            return;
        }

        tempStr.append(c);
        board[i][j] = '#';
        int m = board.length;
        int n = board[0].length;

        if (thisNode.wordEndFlag) {
            result.add(tempStr.toString());
            //de-duplicate
            thisNode.wordEndFlag = false;
        }
        if (i - 1 >= 0 && board[i - 1][j] != '#') {
            findPath(board, thisNode, i - 1, j, tempStr, result);
        }
        if (i + 1 < m && board[i + 1][j] != '#') {
            findPath(board, thisNode, i + 1, j, tempStr, result);
        }
        if (j - 1 >= 0 && board[i][j - 1] != '#') {
            findPath(board, thisNode, i, j - 1, tempStr, result);
        }
        if (j + 1 < n && board[i][j + 1] != '#') {
            findPath(board, thisNode, i, j + 1, tempStr, result);
        }
        board[i][j] = c;
        tempStr.deleteCharAt(tempStr.length() - 1);
    }

    private class DicTree {

        public TreeNode root = new TreeNode();

        public void insert(String str) {
            TreeNode last = root;
            for (Character c : str.toCharArray()) {
                if (last.childNodeList[c - 'a'] == null) {
                    last.childNodeList[c - 'a'] = new TreeNode(c);
                }
                last = last.childNodeList[c - 'a'];
            }
            last.wordEndFlag = true;
        }
    }

    private class TreeNode {
        private Character value;

        /**
         * 改进1：换用map替代，这样查找更高效。
         * 改进2：只用用26的数组替代，查找时直接childNodeList[C-'a']
         */
        public TreeNode[] childNodeList;

        public boolean wordEndFlag = false;

        public TreeNode() {
            this.value = null;
            this.childNodeList = new TreeNode[26];
        }

        public TreeNode(Character character) {
            this.value = character;
            this.childNodeList = new TreeNode[26];
            this.wordEndFlag = false;
        }
    }

    @Override
    public Object initData() {
        return null;
    }
}