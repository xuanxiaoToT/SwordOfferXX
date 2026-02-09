package com.xx.basicDs.tree.balanceTree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 将二叉搜索树变平衡
 * LeetCode 1382. Medium
 * <p>
 * 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。如果有多种构造方法，请你返回任意一种。
 * 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的 。
 * <p>
 * 示例 1：
 * 输入：root = [1,null,2,null,3,null,4,null,null]
 * 输出：[2,1,3,null,null,null,4]
 * 解释：这不是唯一的正确答案，[3,1,4,null,2,null,null] 也是一个可行的构造方案。
 * <p>
 * 示例 2：
 * 输入: root = [2,1,3]
 * 输出: [2,1,3]
 * <p>
 * 提示：
 * 树节点的数目在 [1, 10^4] 范围内。
 * 1 <= Node.val <= 10^5
 * <p>
 * Tag: 平衡二叉树
 */
public class BalanceBinarySearchTree implements Answer {
    public static void main(String[] args) {
        new BalanceBinarySearchTree().answer();
    }

    @Override
    public void answer() {
        TreeNode treeNode = DataFactory.generateTreeNode();
        System.out.println(balanceBST(treeNode));
    }

    public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        dfs(root, list);
        return generateBinarySearchTree(list, 0, list.size() - 1);
    }

    /**
     * 模板题
     * 参考{@link ConvertAnOrderedArrayIntoBinarySearchTree}
     */
    public TreeNode generateBinarySearchTree(List<TreeNode> list, int left, int right) {
        if (left <= right) {
            int mid = (left + right) / 2;
            TreeNode midNode = list.get(mid);
            midNode.left = generateBinarySearchTree(list, left, mid - 1);
            midNode.right = generateBinarySearchTree(list, mid + 1, right);
            return midNode;
        }
        return null;
    }

    private void dfs(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        dfs(root.left, list);
        list.add(root);
        dfs(root.right, list);
    }

    @Override
    public Object initData() {
        return null;
    }
}
