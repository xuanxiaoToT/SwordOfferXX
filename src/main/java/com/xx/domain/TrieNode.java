package com.xx.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 玄霄
 * @CreateDate 2022/10/12
 */
@Data
@AllArgsConstructor
public class TrieNode {

    private Character value;


    /**
     * 改进1：换用map替代，这样查找更高效。
     * 改进2：只用用26的数组替代，查找时直接childNodeList[C-'a']
     */
    // private List<TrieNode> childNodeList;
    private TrieNode[] childNodeList;

    private boolean wordEndFlag = false;

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