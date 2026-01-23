package com.xx.basicDs.graph.topology;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/1
 * <p>
 * 参加会议的最多员工数
 * LeetCode 2127. Hard
 * <p>
 * 一个公司准备组织一场会议，邀请名单上有 n 位员工。公司准备了一张 圆形 的桌子，可以坐下 任意数目 的员工。
 * 员工编号为 0 到 n - 1 。每位员工都有一位 喜欢 的员工，每位员工 "当且仅当" 他被安排在喜欢员工的旁边，他才会参加会议。
 * 每位员工喜欢的员工 不会 是他自己。
 * 给你一个下标从 0 开始的整数数组 favorite ，其中 favorite[i] 表示第 i 位员工喜欢的员工。请你返回参加会议的 最多员工数目 。
 * <p>
 * 示例 1：
 * 输入：favorite = [2,2,1,2]
 * 输出：3
 * 解释：
 * 上图展示了公司邀请员工 0，1 和 2 参加会议以及他们在圆桌上的座位。
 * 没办法邀请所有员工参与会议，因为员工 2 没办法同时坐在 0，1 和 3 员工的旁边。
 * 注意，公司也可以邀请员工 1，2 和 3 参加会议。
 * 所以最多参加会议的员工数目为 3 。
 * <p>
 * 示例 2：
 * 输入：favorite = [1,2,0]
 * 输出：3
 * 解释：
 * 每个员工都至少是另一个员工喜欢的员工。所以公司邀请他们所有人参加会议的前提是所有人都参加了会议。
 * 座位安排同图 1 所示：
 * - 员工 0 坐在员工 2 和 1 之间。
 * - 员工 1 坐在员工 0 和 2 之间。
 * - 员工 2 坐在员工 1 和 0 之间。
 * 参与会议的最多员工数目为 3 。
 * <p>
 * 示例 3：
 * 输入：favorite = [3,0,1,4,1]
 * 输出：4
 * 解释：
 * 上图展示了公司可以邀请员工 0，1，3 和 4 参加会议以及他们在圆桌上的座位。
 * 员工 2 无法参加，因为他喜欢的员工 0 旁边的座位已经被占领了。
 * 所以公司只能不邀请员工 2 。
 * 参加会议的最多员工数目为 4 。
 * <p>
 * 提示：
 * n == favorite.length
 * 2 <= n <= 10^5
 * 0 <= favorite[i] <= n - 1
 * favorite[i] != i
 */
public class MaximumNumberOfEmployeesAttendingTheMeeting implements Answer {

    public static void main(String[] args) {
        new MaximumNumberOfEmployeesAttendingTheMeeting().answer();
    }

    /**
     * 参考：https://blog.csdn.net/guoxinian/article/details/122354123
     */
    @Override
    public void answer() {
        int[] favorite = initData();
        System.out.println(maximumInvitations(favorite));
    }

    /**
     * 仔细读题，仔细读题，仔细读题！
     * 因为是一个圆形桌，所以排列下来必须有个环(如果有环)。
     * 注意题目：当且仅当 坐在喜欢的员工旁时，才会参加。并且桌子是圆形！！
     * 所以：
     * 简单概括解决思路
     * 使用拓扑排序之后,剩下的一定是一个入度为1的环
     * --如果环的大小>2,则能参加会议的最多员工数目就为环的大小
     * --如果环的大小=2,则可以参加会议的最多员工数目就是2 + 从两个环节点能够延伸的树枝的最大深度。以及累加其它的2点环。
     * 2个的环，可以简单理解为自身相邻，此时再加人不会破坏原有的关系。
     */
    public int maximumInvitations(int[] favorite) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int[] inDegree = buildGraph(favorite, graph);
        // 用来存放每个节点的深度
        int[] dp = new int[favorite.length];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        //难点1：
        // 用dp来计算节点深度，这样遇到2个点的环时才好求得其长度。
        // 思路：层序遍历，每一层长度+1，但某个点的入度可能为2，应取最长的那个。所以：dp[poll] = Math.max(dp[poll], deep);
        // 另外：其指向的下一个点，则为dp[poll] + 1，即dp[num] = Math.max(dp[num], dp[poll] + 1);
        int deep = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                dp[poll] = Math.max(dp[poll], deep);
                Set<Integer> set = graph.get(poll);
                if (set != null && !set.isEmpty()) {
                    for (Integer num : set) {
                        dp[num] = Math.max(dp[num], dp[poll] + 1);
                        inDegree[num]--;
                        if (inDegree[num] == 0) {
                            queue.add(num);
                        }
                    }
                }
            }
            deep++;
        }
        //求环的个数 和 环的大小
        HashSet<Integer> hasVisited = new HashSet<>();
        int maxCircleLen = 0;
        int len2Count = 0;
        queue.clear();
        for (int i = 0; i < inDegree.length; i++) {
            //遍历全部入度为1的，即是有环的
            if (inDegree[i] == 1 && !hasVisited.contains(i)) {
                int count = computeCirCleLen(graph, hasVisited, i);
                if (count == 2) {
                    len2Count += 2 + dp[i] + dp[favorite[i]];
                } else {
                    maxCircleLen = Math.max(count, maxCircleLen);
                }
            }
        }
        return Math.max(maxCircleLen, len2Count);
    }

    /**
     * 求环上的节点数，广度优先遍历
     */
    private int computeCirCleLen(Map<Integer, Set<Integer>> graph, HashSet<Integer> hasVisited, int start) {
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            if (!hasVisited.contains(poll)) {
                count++;
                hasVisited.add(poll);
                Set<Integer> set = graph.get(poll);
                if (set != null) {
                    for (Integer next : set) {
                        if (!hasVisited.contains(next)) {
                            queue.add(next);
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * 有向图
     */
    public int[] buildGraph(int[] favorite, Map<Integer, Set<Integer>> graph) {
        int[] inDegree = new int[favorite.length];
        for (int i = 0; i < favorite.length; i++) {
            if (graph.containsKey(i)) {
                graph.get(i).add(favorite[i]);
            } else {
                Set<Integer> list = new HashSet<>();
                list.add(favorite[i]);
                graph.put(i, list);
            }
            inDegree[favorite[i]]++;
        }
        return inDegree;
    }

    @Override
    public int[] initData() {
        // return new int[]{3, 0, 1, 4, 1};
        // return new int[]{1, 2, 0};
        // return new int[]{2, 2, 1, 2};
        //return new int[]{1, 0, 0, 2, 1, 4, 7, 8, 9, 6, 7, 10, 8};
        // return new int[]{1, 2, 3, 4, 5, 6, 3, 8, 9, 10, 11, 8};
        return new int[]{1, 0, 3, 2, 5, 6, 7, 4, 9, 8, 11, 10, 11, 12, 10};
    }
}