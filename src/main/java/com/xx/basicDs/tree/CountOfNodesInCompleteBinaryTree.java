package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/23
 * <p>
 * 完全二叉树的节点个数
 * LeetCode 222. Medium
 * <p>
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * <p>
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，
 * 其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
 * 若最底层为第 h 层，则该层包含 1~ 2^h 个节点。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6]
 * 输出：6
 * <p>
 * 示例 2：
 * 输入：root = []
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：root = [1]
 * 输出：1
 * <p>
 * 提示：
 * 树中节点的数目范围是[0, 5 * 10^4]
 * 0 <= Node.val <= 5 * 10^4
 * 题目数据保证输入的树是 完全二叉树
 * <p>
 * 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？
 */
public class CountOfNodesInCompleteBinaryTree implements Answer {

    public static void main(String[] args) {
        new CountOfNodesInCompleteBinaryTree().answerOne();
    }

    /**
     * 解1：层序简单算
     */
    @Override
    public void answerOne() {
        TreeNode root = initData();
        Queue<TreeNode> outQueue = new LinkedList<TreeNode>();
        int count = 0;
        if (root != null) {
            outQueue.add(root);
        }
        while (!outQueue.isEmpty()) {
            TreeNode temp = outQueue.poll();
            count++;
            if (temp.left != null) {
                outQueue.add(temp.left);
            }
            if (temp.right != null) {
                outQueue.add(temp.right);
            }
        }
        System.out.println(count);
    }

    /**
     * 进阶:二分查找 + 位运算
     * 题解：https://leetcode.cn/problems/count-complete-tree-nodes/solutions/495655/wan-quan-er-cha-shu-de-jie-dian-ge-shu-by-leetco-2/?envType=study-plan-v2&envId=top-interview-150
     */
    public int answerTwo(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }


    /**
     * 输出数据
     */
    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}
