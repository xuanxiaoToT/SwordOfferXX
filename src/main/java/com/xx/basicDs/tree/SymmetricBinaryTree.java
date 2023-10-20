package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/20
 * <p>
 * 对称二叉树
 * LeetCode 101. Easy
 * <p>
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 * <p>
 * 提示：
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 * <p>
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 */
public class SymmetricBinaryTree implements Answer {

    public static void main(String[] args) {
        new SymmetricBinaryTree().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        TreeNode root = initData();
        System.out.println(isSymmetric(root, root));
    }

    public boolean isSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null)
            return false;
        if (root2 == null)
            return false;
        if (root1.value != root2.value)
            return false;
        return isSymmetric(root1.left, root2.right) &&
                isSymmetric(root1.right, root2.left);
    }

    /**
     * 输出数据
     */
    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}
