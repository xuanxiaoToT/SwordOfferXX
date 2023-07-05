package com.xx.basicDs.graph;

import com.xx.Answer;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/22
 * <p>
 * 开密码锁
 * <p>
 * 一个密码锁由4个环形转轮组成，每个转轮由0～9这10个
 * 数字组成。每次可以上下拨动一个转轮，如可以将一个转轮从0拨到
 * 1，也可以从0拨到9。
 * 密码锁有若干死锁状态，一旦4个转轮被拨到
 * 某个死锁状态，这个锁就不可能打开。密码锁的状态可以用一个长
 * 度为4的字符串表示，字符串中的每个字符对应某个转轮上的数字。
 * 输入密码锁的密码和它的所有死锁状态，请问至少需要拨动转轮多
 * 少次才能从起始状态"0000"开始打开这个密码锁？如果锁不可能打
 * 开，则返回-1。
 * <p>
 * 例如，如果某个密码锁的密码是"0202"，它的死锁状态列表是
 * ["0102"，"0201"]，那么至少需要拨动转轮6次才能打开这个密码锁，
 * 一个可行的开锁状态序列
 * 是"0000"→"1000"→"1100"→"1200"→"1201"→"1202"→"0202"。虽
 * 然序列"0000"→"0001"→"0002"→"0102"→"0202"更短，只需要拨动4
 * 次转轮，但它包含死锁状态"0102"，因此这是一个无效的开锁序列。
 * <p>
 * 思路：一般而言，如果一个问题是关于某事
 * 物状态的改变，那么可以考虑把问题转换成图搜索的问题。事物的每
 * 个状态是图中的一个节点，如果一个状态能够转变到另一个状态，那
 * 么这两个状态对应的节点之间有一条边相连。
 */
public class OpenPasswordLock implements Answer {

    public static void main(String[] args) {
        new OpenPasswordLock().answerOne();
    }

    /**
     * 解:四个转轮固定
     * 可以改为双向广度优先搜索
     */
    @Override
    public void answerOne() {
        String[] lockData = initData();
        String target = "0202";
        Queue<String> queue = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        queue.add("0000");
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String pollStr = queue.poll();
                if (pollStr.equals(target)) {
                    System.out.println(depth);
                    return;
                }
                // flag：表明本层是否会造成死锁。
                boolean flag = true;
                for (String lockDatum : lockData) {
                    if (lockDatum.equals(pollStr)) {
                        flag = false;
                        break;
                    }
                }
                for (int j = 0; j < pollStr.length(); j++) {
                    if (flag) {
                        char tempAdd = pollStr.charAt(j) + 1 > '9' ? '0' : (char) (pollStr.charAt(j) + 1);
                        char tempSub = pollStr.charAt(j) - 1 < '0' ? '9' : (char) (pollStr.charAt(j) - 1);
                        StringBuilder sbAdd = new StringBuilder();
                        sbAdd.append(pollStr);
                        sbAdd.setCharAt(j, tempAdd);
                        if (!set.contains(sbAdd.toString())) {
                            queue.add(sbAdd.toString());
                            set.add(sbAdd.toString());
                        }
                        StringBuilder sbSub = new StringBuilder();
                        sbSub.append(pollStr);
                        sbSub.setCharAt(j, tempSub);
                        queue.add(sbSub.toString());
                    }
                }
            }
            depth++;
        }
    }

    /**
     * 输入数据
     */
    @Override
    public String[] initData() {
        return new String[]{"0102", "0201"};
    }
}