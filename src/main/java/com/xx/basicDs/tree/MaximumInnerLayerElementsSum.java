package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/7
 * <p>
 * 最大层内元素和
 * LeetCode 1161. Medium
 * <p>
 * 给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
 * 请返回层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。
 * <p>
 * 示例 1：
 * 输入：root = [1,7,0,7,-8,null,null]
 * 输出：2
 * 解释：
 * 第 1 层各元素之和为 1，
 * 第 2 层各元素之和为 7 + 0 = 7，
 * 第 3 层各元素之和为 7 + -8 = -1，
 * 所以我们返回第 2 层的层号，它的层内元素之和最大。
 * <p>
 * 示例 2：
 * 输入：root = [989,null,10250,98693,-89388,null,null,null,-32127]
 * 输出：2
 * <p>
 * 提示：
 * 树中的节点数在 [1, 10^4]范围内
 * -10^5 <= Node.val <= 10^5
 * <p>
 * Tag: 树的遍历 广度优先遍历
 */
public class MaximumInnerLayerElementsSum implements Answer {
    public static void main(String[] args) {
        new MaximumInnerLayerElementsSum().answerOne();
    }

    @Override
    public void answerOne() {
        TreeNode treeNode = DataFactory.generateTreeNode();
        System.out.println(maximumSumIndex(treeNode));
    }

    public int maximumSumIndex(TreeNode root) {
        int layer = 0;
        int max = Integer.MIN_VALUE;
        int result = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int sumTemp = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                sumTemp += poll.val;
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            layer++;
            if (sumTemp > max) {
                max = sumTemp;
                result = layer;
            }
        }
        System.out.println(max);
        return result;
    }

    @Override
    public Object initData() {
        return null;
    }
}
