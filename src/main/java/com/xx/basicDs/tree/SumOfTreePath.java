package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.Objects;

/**
 * @author XuanXiao
 * @CreateDate 2023/10/20
 * <p>
 * 路径总和
 * LeetCode 112. Medium
 * <p>
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。
 * 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 * 如果存在，返回 true ；否则，返回 false 。
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 * 解释：等于目标和的根节点到叶节点路径如上图所示。
 * <p>
 * 示例 2：
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 * 解释：树中存在两条根节点到叶子节点的路径：
 * (1 --> 2): 和为 3
 * (1 --> 3): 和为 4
 * 不存在 sum = 5 的根节点到叶子节点的路径。
 * <p>
 * 示例 3：
 * 输入：root = [], targetSum = 0
 * 输出：false
 * 解释：由于树是空的，所以不存在根节点到叶子节点的路径。
 * <p>
 * 提示：
 * 树中节点的数目在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */
public class SumOfTreePath implements Answer {

    public static void main(String[] args) {
        new SumOfTreePath().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {

    }

    public boolean hasPathSum(TreeNode node, int tempSum, int targetSum) {
        if (node.left == null && node.right == null) {
            return (tempSum + node.val) == targetSum;
        } else {
            if (node.left != null && node.right != null) {
                return hasPathSum(node.left, tempSum + node.val, targetSum) || hasPathSum(node.right, tempSum + node.val, targetSum);
            } else
                return hasPathSum(Objects.requireNonNullElseGet(node.left, () -> node.right), tempSum + node.val, targetSum);
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
