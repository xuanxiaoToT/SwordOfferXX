package com.xx.basicDs.graph.topology;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2022/12/27
 * <p>
 * 课程顺序
 * LeetCode 207. 课程表
 * <p>
 * n门课程的编号为0～n-1。输入一个数组
 * prerequisites，它的每个元素prerequisites[i]表示两门课程的先
 * 修顺序。如果prerequisites[i]=[ai，bi]，那么必须先修完bi才能
 * 修ai。请根据总课程数n和表示先修顺序的prerequisites得出一个
 * 可行的修课序列。如果有多个可行的修课序列，则输出任意一个可
 * 行的序列；如果没有可行的修课序列，则输出空序列。
 * <p>
 * 例：总共有4门课程，先修顺序prerequisites为[[1，0]，[2，0]，[3，1]，[3，2]]，
 * 一个可行的修课序列是0→2→1→3。
 * <p>
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * <p>
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 *
 * <p>
 * PS：拓扑排序
 * 一种常用的拓扑排序算法是每次从有向无环图中取出一个入度为0
 * 的节点添加到拓扑排序序列之中，然后删除该节点及所有以它为起点
 * 的边。重复这个步骤，直到图为空或图中不存在入度为0的节点。
 */
public class CourseSequence implements Answer {

    public static void main(String[] args) {
        new CourseSequence().answer();
    }

    /**
     * 解：先根据先修顺序构建出有向图graph，graph用一个
     * HashMap表示邻接表。同时，将每个节点的入度保存到数组
     * inDegrees中，“inDegrees[i]”表示节点i的入度。
     * 接下来用广度优先搜索算法实现拓扑排序。
     */
    @Override
    public void answer() {
        // 初始化数据
        int[][] data = initData();
        int numCourses = 2;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegrees = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        // 构造图
        for (int[] graphNode : data) {
            int key = graphNode[1];
            int value = graphNode[0];
            inDegrees[value] = inDegrees[value] + 1;
            if (graph.containsKey(key)) {
                graph.get(key).add(value);
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.add(value);
                graph.put(key, temp);
            }
        }
        // 从入度为0的开始进行深度优先遍历
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }
        // 广度优先遍历来实现拓扑排序
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            result.add(poll);
            List<Integer> list = graph.get(poll);
            if (list != null && !list.isEmpty()) {
                for (Integer next : list) {
                    // 入度-1
                    inDegrees[next] = inDegrees[next] - 1;
                    if (inDegrees[next] == 0) {
                        queue.add(next);
                    }
                }
            }
        }
        System.out.println(result.size());
    }

    /**
     * 输入数据
     */
    @Override
    public int[][] initData() {
        //return new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        //return new int[][]{{1, 0}, {0, 1}};
        return new int[][]{{1, 0}};
    }
}