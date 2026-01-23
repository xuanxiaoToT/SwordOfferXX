package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;

/**
 * 分裂二叉树的最大乘积
 * LeetCode  1339. Medium
 * <p>
 * 给你一棵二叉树，它的根为 root 。请你删除 1 条边，使二叉树分裂成两棵子树，且它们子树和的乘积尽可能大。
 * 由于答案可能会很大，请你将结果对 10^9 + 7 取模后再返回。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6]
 * 输出：110
 * 解释：删除红色的边，得到 2 棵子树，和分别为 11 和 10 。它们的乘积是 110 （11*10）
 * <p>
 * 示例 2：
 * 输入：root = [1,null,2,3,4,null,null,5,6]
 * 输出：90
 * 解释：移除红色的边，得到 2 棵子树，和分别是 15 和 6 。它们的乘积为 90 （15*6）
 * <p>
 * 示例 3：
 * 输入：root = [2,3,9,10,7,8,6,5,4,11,1]
 * 输出：1025
 * <p>
 * 示例 4：
 * 输入：root = [1,1]
 * 输出：1
 * <p>
 * 提示：
 * 每棵树最多有 50000 个节点，且至少有 2 个节点。
 * 每个节点的值在 [1, 10000] 之间。
 * <p>
 * Tag：树的遍历  深度优先遍历  取模
 */
public class MaximumProductOfSplittedBinaryTree implements Answer {
    public long maxVal = 0;
    public int mask = 1000000007;

    public static void main(String[] args) {
        new MaximumProductOfSplittedBinaryTree().answer();
    }

    @Override
    public void answer() {
        long sum1 = 500000000;
        long sum2 = 500000000;
        long temp = ((sum1 - 1) * sum2) % mask;
        System.out.println(temp);
    }

    public int maxProduct(TreeNode root) {
        int sum = computeSum(root);
        computeResult(root, sum);
        return Math.toIntExact(maxVal % mask);
    }

    /**
     * 思路没问题
     * 但是错误原因：
     * 一直给leftTemp取模，这样会导致 原先较大的数，取模之后不一定仍然较大。
     * 所以应该是最终结果再取模，直接用long型
     */
    private long computeResult(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        long leftSum = computeResult(root.left, sum);
        long rightSum = computeResult(root.right, sum);
        long leftTemp = ((sum - rightSum) * rightSum);
        long rightTemp = ((sum - leftSum) * leftSum);
        long maxTemp = Math.max(leftTemp, rightTemp);
        maxVal = Math.max(maxVal, maxTemp);
        return root.val + leftSum + rightSum;
    }

    private int computeSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = computeSum(root.left);
        int rightSum = computeSum(root.right);
        return root.val + leftSum + rightSum;
    }


    @Override
    public Object initData() {
        return null;
    }
}
