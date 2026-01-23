package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2023/7/20
 * <p>
 * 二叉搜索树的最近公共祖先
 * LeetCode 235
 * <p>
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
 * 最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大
 * （一个节点也可以是它自己的祖先）。”
 * <p>
 * 示例 1:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * <p>
 * 示例 2:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 */
public class LowestCommonAncestor implements Answer {

    public static void main(String[] args) {
        new LowestCommonAncestor().answer();
    }

    /**
     * 解1：注意是二叉搜索树，
     * <p>
     * 相比较题目《二叉树的最近公共祖先》判断更简单
     * {@link NearestCommonAncestorOfBinaryTree}
     */
    @Override
    public void answer() {
        TreeNode root = initData();
        TreeNode targetOne = new TreeNode(13);
        TreeNode targetTwo = new TreeNode(21);
        findLowestCommonAncestor(root, targetOne, targetTwo);
    }

    private TreeNode findLowestCommonAncestor(TreeNode root, TreeNode targetOne, TreeNode targetTwo) {
        int parentVal = root.val;
        int pVal = targetOne.val;
        int qVal = targetTwo.val;
        if (pVal > parentVal && qVal > parentVal) {
            return findLowestCommonAncestor(root.right, targetOne, targetTwo);
        } else if (pVal < parentVal && qVal < parentVal) {
            return findLowestCommonAncestor(root.left, targetOne, targetTwo);
        } else {
            return root;
        }
    }

    /**
     * 输出数据
     */
    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}
