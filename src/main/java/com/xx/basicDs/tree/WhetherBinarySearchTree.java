package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author XuanXiao
 * @CreateDate 2023/9/4
 * <p>
 * 验证二叉搜索树
 * LeetCode 098
 * <p>
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 示例 1：
 * 输入：root = [2,1,3]
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 */
public class WhetherBinarySearchTree implements Answer {

    public static void main(String[] args) {
        new WhetherBinarySearchTree().answerTwo();
    }

    /**
     * 解1：中序遍历，一定是有序的
     */
    @Override
    public void answerOne() {
        TreeNode root = initData();
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.value <= inorder) {
                System.out.println(false);
                return;
            }
            inorder = root.value;
            root = root.right;
        }
        System.out.println(true);
    }

    public void answerTwo() {
        TreeNode root = initData();
        boolean validBST = isValidBST(root, root.value, 0);
        System.out.println(validBST);
    }

    /**
     *
     */
    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.value <= lower || node.value >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.value) && isValidBST(node.right, node.value, upper);
    }


    /**
     * 输出数据
     */
    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}
