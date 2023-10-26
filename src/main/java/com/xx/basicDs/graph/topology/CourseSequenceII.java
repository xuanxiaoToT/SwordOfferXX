package com.xx.basicDs.graph.topology;

import com.xx.Answer;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/26
 * <p>
 * 课程表II
 * LeetCode 210 Medium
 * <p>
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，
 * 其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 * <p>
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：[0,1]
 * 解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * <p>
 * 示例 2：
 * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * 输出：[0,2,1,3]
 * 解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 * 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * <p>
 * 示例 3：
 * 输入：numCourses = 1, prerequisites = []
 * 输出：[0]
 * <p>
 * 提示：
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * 所有[ai, bi] 互不相同
 */
public class CourseSequenceII implements Answer {

    public static void main(String[] args) {
        new CourseSequenceII().answerOne();
    }

    /**
     * 解：先根据先修顺序构建出有向图graph，graph用一个
     * HashMap表示邻接表。同时，将每个节点的入度保存到数组
     * inDegrees中，“inDegrees[i]”表示节点i的入度。
     * 接下来用广度优先搜索算法实现拓扑排序。
     */
    @Override
    public void answerOne() {
        // 初始化数据
        int[][] prerequisites = initData();
        int numCourses = 2;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegrees = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        // 构造图
        for (int[] graphNode : prerequisites) {
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
        if (result.size() != numCourses) {
            return;
        }
        int[] array = result.stream().mapToInt(Integer::valueOf).toArray();


    }

    /**
     * 输入数据
     */
    @Override
    public int[][] initData() {
        //return new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        return new int[][]{{1, 0}, {0, 1}};
        //return new int[][]{{1, 0}};
    }
}
