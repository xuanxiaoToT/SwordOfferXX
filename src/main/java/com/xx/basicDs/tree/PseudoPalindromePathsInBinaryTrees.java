package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;

/**
 * @author XuanXiao
 * @CreateDate 2023/11/25
 * <p>
 * 二叉树中的伪回文路径
 * LeetCode 1457  Medium
 * <p>
 * 给你一棵二叉树，每个节点的值为 1 到 9 。我们称二叉树中的一条路径是 「伪回文」的，
 * 当它满足：路径经过的所有节点值的排列中，存在一个回文序列。
 * 请你返回从根到叶子节点的所有路径中 伪回文 路径的数目。
 * <p>
 * 示例 1：
 * 输入：root = [2,3,1,3,1,null,1]
 * 输出：2
 * 解释：上图为给定的二叉树。总共有 3 条从根到叶子的路径：红色路径 [2,3,3] ，绿色路径 [2,1,1] 和路径 [2,3,1] 。
 * 在这些路径中，只有红色和绿色的路径是伪回文路径，因为红色路径 [2,3,3] 存在回文排列 [3,2,3] ，绿色路径 [2,1,1] 存在回文排列 [1,2,1] 。
 * <p>
 * 示例 2：
 * 输入：root = [2,1,1,1,3,null,null,null,null,null,1]
 * 输出：1
 * 解释：上图为给定二叉树。总共有 3 条从根到叶子的路径：绿色路径 [2,1,1] ，路径 [2,1,3,1] 和路径 [2,1] 。
 * 这些路径中只有绿色路径是伪回文路径，因为 [2,1,1] 存在回文排列 [1,2,1] 。
 * <p>
 * 示例 3：
 * 输入：root = [9]
 * 输出：1
 * <p>
 * 提示：
 * 给定二叉树的节点数目在范围 [1, 10^5] 内
 * 1 <= Node.val <= 9
 * <p>
 * Tag: 树的递归遍历 深度优先遍历 回溯 伪回文
 */
public class PseudoPalindromePathsInBinaryTrees implements Answer {

    public static void main(String[] args) {
        new PseudoPalindromePathsInBinaryTrees().answer();
    }

    /**
     * 解1：DFS+回溯
     */
    @Override
    public void answer() {
        TreeNode root = initData();
        System.out.println(pseudoPalindromicPaths(root));
    }

    public int pseudoPalindromicPaths(TreeNode root) {
        int[] nums = new int[10];
        dfsTree(root, nums);
        return result;
    }

    /**
     * 深度优先遍历
     * 注意，需要回溯，也就是退档nums的值
     */
    int result = 0;

    private void dfsTree(TreeNode node, int[] nums) {
        nums[node.val]++;
        if (node.left == null && node.right == null) {
            if (isPseudoPalindromicPath(nums)) {
                result++;
            }
            nums[node.val]--;
            return;
        }
        if (node.left != null) {
            dfsTree(node.left, nums);
        }
        if (node.right != null) {
            dfsTree(node.right, nums);
        }
        nums[node.val]--;
    }

    /**
     * 判断是否是伪回文
     * 奇数的个数不能大于1
     */
    private boolean isPseudoPalindromicPath(int[] nums) {
        int oneCount = 0;
        for (int num : nums) {
            if (num % 2 != 0) {
                oneCount++;
                if (oneCount > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 输出数据
     */
    @Override
    public TreeNode initData() {
        TreeNode treeNode = new TreeNode(2);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(1);
        treeNode.left = node1;
        treeNode.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
        return treeNode;
    }
}
