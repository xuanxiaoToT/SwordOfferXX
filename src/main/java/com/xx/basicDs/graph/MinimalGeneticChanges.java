package com.xx.basicDs.graph;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/27
 * <p>
 * 最小基因变化
 * LeetCode 433 Medium
 * <p>
 * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 * 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。（变化后的基因必须位于基因库 bank 中）
 * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。
 * 如果无法完成此基因变化，返回 -1 。
 * 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
 * <p>
 * 示例 1：
 * 输入：start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
 * 输出：2
 * <p>
 * 示例 3：
 * 输入：start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
 * 输出：3
 * <p>
 * 提示：
 * start.length == 8
 * end.length == 8
 * 0 <= bank.length <= 10
 * bank[i].length == 8
 * start、end 和 bank[i] 仅由字符 ['A', 'C', 'G', 'T'] 组成
 */
public class MinimalGeneticChanges implements Answer {

    public static void main(String[] args) {
        new MinimalGeneticChanges().answer();
    }

    /**
     * 解1：每个基因序列相当于一个图节点。本质上是计算图的可达性。
     * 利用广度优先遍历，每一层就算一步。
     * 如果用深度优先遍历，则无法求得最小值。这意味着需要维护一个全局的步骤。
     * 而广度，知道到达即可停止，此时就是最小步骤值。
     * 思路解法同： {@link WordEvolution}
     */
    @Override
    public void answer() {
        String start = "AAAAACCC", end = "AACCCCCC";
        String[] bank = new String[]{"AAAACCCC", "AAACCCCC", "AACCCCCC"};
        System.out.println(minMutation(start, end, bank));
    }

    public int minMutation(String startGene, String endGene, String[] bank) {
        Queue<String> queue = new LinkedList<>();
        HashSet<String> hasVisited = new HashSet<>();
        queue.add(startGene);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                System.out.println(poll);
                if (poll.equals(endGene)) {
                    return step;
                } else {
                    List<String> nextListInBank = getNextListInBank(poll, bank, hasVisited);
                    queue.addAll(nextListInBank);
                }
            }
            step++;
        }
        return -1;
    }

    private List<String> getNextListInBank(String poll, String[] bank, HashSet<String> hasVisited) {
        List<String> result = new ArrayList<>();
        for (String s : bank) {
            int diffCount = 0;
            if (!hasVisited.contains(s)) {
                for (int i = 0; i < poll.length(); i++) {
                    char bankChar = s.charAt(i);
                    char pollChar = poll.charAt(i);
                    if (bankChar != pollChar) {
                        diffCount++;
                    }
                }
                if (diffCount == 1) {
                    result.add(s);
                    hasVisited.add(s);
                }
            }
        }
        return result;
    }


    /**
     * 输出数据
     */
    @Override
    public Object initData() {
        return null;
    }
}
