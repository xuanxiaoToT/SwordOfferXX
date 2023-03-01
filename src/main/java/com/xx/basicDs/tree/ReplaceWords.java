package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TrieNode;

/**
 * @author XuanXiao
 * @CreateDate 2022/10/12
 * <p>
 * 英语中有一个概念叫词根。在词根后面加上若干字符就
 * 能拼出更长的单词。例如，"an"是一个词根，在它后面加
 * 上"other"就能得到另一个单词"another"。现在给定一个由词根组
 * 成的字典和一个英语句子，如果句子中的单词在字典中有它的词
 * 根，则用它的词根替换该单词；如果单词没有词根，则保留该单
 * 词。请输出替换后的句子。
 *
 * 例如，如果词根字典包含字符串
 * ["cat"，"bat"，"rat"]，英语句子为"the cattle was rattled by
 * the battery"，则替换之后的句子是"the cat was rat by the
 * bat"。
 */
public class ReplaceWords implements Answer {

    public static void main(String[] args) {
        new ReplaceWords().answerOne();
    }

    /**
     * 1.用前缀树，参考 @{TrieDemo}
     * 2.用set直接替代，感觉比这快。
     */
    @Override
    public void answerOne() {
        String[] strings = initData();
        String oriStr = "the cattle was rattled by the battery";
        TrieDemo trieDemo = new TrieDemo();
        // 初始化
        for (String string : strings) {
            trieDemo.insert(string);
        }
        for (String s : oriStr.split(" ")) {
            // trieDemo.
        }
    }


    private String findFix(TrieNode root, String str) {
        TrieNode last = root;
        StringBuilder sb = new StringBuilder();
        for (Character character : str.toCharArray()) {
            if (last.getChildNodeList()[character - 'a'] == null) {
                return null;
            }
            if (last.isWordEndFlag()) {
                sb.append(last.getChildNodeList()[character - 'a'].getValue());
                return sb.toString();
            }
        }
        return null;
    }

    /**
     * something
     */
    @Override
    public String[] initData() {
        return new String[]{"cat", "bat", "rat"};
    }
}