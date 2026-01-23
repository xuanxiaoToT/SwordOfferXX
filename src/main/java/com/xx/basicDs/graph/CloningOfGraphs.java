package com.xx.basicDs.graph;

import com.xx.Answer;
import com.xx.basicDs.linked.RandomListCopy;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/26
 * <p>
 * 克隆图
 * LeetCode 133. Medium
 * <p>
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 * <p>
 * 测试用例格式：
 * 简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1（val = 1），第二个节点值为 2（val = 2），以此类推。该图在测试用例中使用邻接列表表示。
 * 邻接列表 是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
 * 给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆图的引用返回。
 * <p>
 * 示例 1：
 * 输入：adjList = [[2,4],[1,3],[2,4],[1,3]]
 * 输出：[[2,4],[1,3],[2,4],[1,3]]
 * 解释：
 * 图中有 4 个节点。
 * 节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
 * 节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
 * 节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
 * 节点 4 的值是 4，它有两个邻居：节点 1 和 3 。
 * <p>
 * 示例 2：
 * 输入：adjList = [[]]
 * 输出：[[]]
 * 解释：输入包含一个空列表。该图仅仅只有一个值为 1 的节点，它没有任何邻居。
 * <p>
 * 示例 3：
 * 输入：adjList = []
 * 输出：[]
 * 解释：这个图是空的，它不含任何节点。
 * <p>
 * 示例 4：
 * 输入：adjList = [[2],[1]]
 * 输出：[[2],[1]]
 * <p>
 * 提示：
 * 节点数不超过 100 。
 * 每个节点值 Node.val 都是唯一的，1 <= Node.val <= 100。
 * 无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
 * 由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
 * 图是连通图，你可以从给定节点访问到所有节点。
 */
public class CloningOfGraphs implements Answer {

    public static void main(String[] args) {
        CloningOfGraphs cloning = new CloningOfGraphs();
        Node root = cloning.initData();
        cloning.answerTwo(root);
        System.out.println("ddd");
    }

    @Override
    public void answer() {
        Node oriRoot = initData();
        if (oriRoot == null) {
            return;
        }
        Node root = graphBfs(oriRoot);
        System.out.println("asd");
    }

    /**
     * 解法有些繁琐
     * 仅需要关注是否被遍历即可，遍历到了说明之前加过了。
     * 参考{@link RandomListCopy}
     */
    private Node graphBfs(Node oriRoot) {
        Node newRoot = null;
        Map<Integer, Node> newNodeMap = new HashMap<>();
        HashSet<Integer> flag = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(oriRoot);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                boolean handle = false;
                if (!flag.contains(poll.val)) {
                    if (!newNodeMap.containsKey(poll.val)) {
                        Node temp = new Node(poll.val, new ArrayList<>());
                        if (newRoot == null) {
                            newRoot = temp;
                        }
                        newNodeMap.put(poll.val, temp);
                    }
                    // System.out.println(poll.val);
                    flag.add(poll.val);
                    handle = true;
                }
                List<Node> neighbors = poll.neighbors;
                for (Node neighbor : neighbors) {
                    if (handle) {
                        if (newNodeMap.containsKey(neighbor.val)) {
                            newNodeMap.get(poll.val).neighbors.add(newNodeMap.get(neighbor.val));
                        } else {
                            Node temp = new Node(neighbor.val, new ArrayList<>());
                            newNodeMap.get(poll.val).neighbors.add(temp);
                            newNodeMap.put(temp.val, temp);
                        }
                    }
                    if (!flag.contains(neighbor.val)) {
                        queue.add(neighbor);
                    }
                }
            }
        }
        return newRoot;
    }

    /**
     * 官解：
     * 为了知道整张图的结构以及对应节点的值，我们需要从给定的节点出发，进行「图的遍历」，并在遍历的过程中完成图的深拷贝。
     */
    public Node answerTwo(Node node) {
        if (node == null) {
            return node;
        }

        HashMap<Node, Node> visited = new HashMap<>();

        // 将题目给定的节点添加到队列
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(node);
        // 克隆第一个节点并存储到哈希表中
        visited.put(node, new Node(node.val, new ArrayList<>()));

        // 广度优先搜索
        while (!queue.isEmpty()) {
            // 取出队列的头节点
            Node n = queue.poll();
            for (Node neighbor : n.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    // 如果没有被访问过，就克隆并存储在哈希表中
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    // 将邻居节点加入队列中
                    queue.add(neighbor);
                }
                // 更新当前节点的邻居列表
                visited.get(n).neighbors.add(visited.get(neighbor));
            }
        }

        return visited.get(node);
    }


    @Override
    public Node initData() {
        Node node1 = new Node(1, new ArrayList<>());
        Node node2 = new Node(2, new ArrayList<>());
        Node node3 = new Node(3, new ArrayList<>());
        Node node4 = new Node(4, new ArrayList<>());
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        return node1;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Node {
        public int val;
        public List<Node> neighbors;
    }
}