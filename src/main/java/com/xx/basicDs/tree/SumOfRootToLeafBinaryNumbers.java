package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;

/**
 * 从根到叶的二进制数之和
 * LeetCode 1022. Easy
 * <p>
 * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。
 * 例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 * 返回这些数字之和。题目数据保证答案是一个 32 位 整数。
 * <p>
 * 示例 1：
 * 输入：root = [1,0,1,0,1,0,1]
 * 输出：22
 * 解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 * <p>
 * 示例 2：
 * 输入：root = [0]
 * 输出：0
 * <p>
 * 提示：
 * 树中的节点数在 [1, 1000] 范围内
 * Node.val 仅为 0 或 1
 * <p>
 * Tag：树的遍历
 */
public class SumOfRootToLeafBinaryNumbers implements Answer {
    int result = 0;

    public static void main(String[] args) {
        new SumOfRootToLeafBinaryNumbers().answer();
    }

    @Override
    public void answer() {

    }

    public int sumRootToLeaf(TreeNode root) {
        result = 0;
        computeVal(root, "");
        return result;
    }

    public void computeVal(TreeNode root, String str) {
        if (root == null) {
            return;
        }
        str = str + root.val;
        if (root.left == null && root.right == null) {
            int anInt = Integer.parseInt(str, 2);
            result += anInt;
            return;
        }
        computeVal(root.left, str);
        computeVal(root.right, str);
    }

    @Override
    public Object initData() {
        return null;
    }
}
