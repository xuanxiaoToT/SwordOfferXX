package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/7
 * <p>
 * 二叉树中的最长交错路径
 * LeetCode 1372. Medium
 * <p>
 * 给你一棵以 root 为根的二叉树，二叉树中的交错路径定义如下：
 * <p>
 * 选择二叉树中 任意 节点和一个方向（左或者右）。
 * 如果前进方向为右，那么移动到当前节点的的右子节点，否则移动到它的左子节点。
 * 改变前进方向：左变右或者右变左。
 * 重复第二步和第三步，直到你在树中无法继续移动。
 * 交错路径的长度定义为：访问过的节点数目 - 1（单个节点的路径长度为 0 ）。
 * <p>
 * 请你返回给定树中最长 交错路径 的长度。
 * <p>
 * 示例 1：
 * 输入：root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
 * 输出：3
 * 解释：蓝色节点为树中最长交错路径（右 -> 左 -> 右）。
 * <p>
 * 示例 2：
 * 输入：root = [1,1,1,null,1,null,null,1,1,null,1]
 * 输出：4
 * 解释：蓝色节点为树中最长交错路径（左 -> 右 -> 左 -> 右）。
 * <p>
 * 示例 3：
 * 输入：root = [1]
 * 输出：0
 * <p>
 * 提示：
 * 每棵树最多有 50000 个节点。
 * 每个节点的值在 [1, 100] 之间。
 * <p>
 * Tag: 树的遍历  之字形遍历
 */
public class LongestInterleavedPathInTree implements Answer {

    public static void main(String[] args) {
        new LongestInterleavedPathInTree().answerOne();
    }

    @Override
    public void answerOne() {
        TreeNode treeNode = DataFactory.generateTreeNode();
        longestInterleavedPath(treeNode, 0, 0);
        System.out.println(max);
    }

    int max = 0;

    //last为-1，表示左，last为1表示右，last为0表示没有。
    private void longestInterleavedPath(TreeNode root, int temp, int last) {
        if (root == null) {
            return;
        }
        max = Math.max(temp, max);
        if (last == 0) {
            longestInterleavedPath(root.left, 1, -1);
            longestInterleavedPath(root.right, 1, 1);
        }
        //注意：重置temp的时候应该为1，而不是0。因为当前的点下传到它的子节点的时候已经走了一条长度为 1 的边
        if (last == -1) {
            longestInterleavedPath(root.left, 1, -1);
            longestInterleavedPath(root.right, temp + 1, 1);
        }
        if (last == 1) {
            longestInterleavedPath(root.left, temp + 1, -1);
            longestInterleavedPath(root.right, 1, 1);
        }
    }

    @Override
    public Object initData() {
        return null;
    }
}