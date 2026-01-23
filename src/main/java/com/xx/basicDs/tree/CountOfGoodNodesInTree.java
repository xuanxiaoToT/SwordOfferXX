package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2024/2/6
 * <p>
 * 统计二叉树中好节点的数目
 * LeetCode 1448. Medium
 * <p>
 * 给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
 * 「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。
 * <p>
 * 示例 1：
 * 输入：root = [3,1,4,3,null,1,5]
 * 输出：4
 * 解释：图中蓝色节点为好节点。
 * 根节点 (3) 永远是个好节点。
 * 节点 4 -> (3,4) 是路径中的最大值。
 * 节点 5 -> (3,4,5) 是路径中的最大值。
 * 节点 3 -> (3,1,3) 是路径中的最大值。
 * <p>
 * 示例 2：
 * 输入：root = [3,3,null,4,2]
 * 输出：3
 * 解释：节点 2 -> (3, 3, 2) 不是好节点，因为 "3" 比它大。
 * <p>
 * 示例 3：
 * 输入：root = [1]
 * 输出：1
 * 解释：根节点是好节点。
 * <p>
 * 提示：
 * 二叉树中节点数目范围是 [1, 10^5] 。
 * 每个节点权值的范围是 [-10^4, 10^4] 。
 * <p>
 * Tag：树的遍历  好节点
 */
public class CountOfGoodNodesInTree implements Answer {

    public static void main(String[] args) {
        new CountOfGoodNodesInTree().answer();
    }

    @Override
    public void answer() {
        TreeNode root = DataFactory.generateTreeNode();
        System.out.println(numOfGoodNodes(root));
    }

    int count = 0;

    public int numOfGoodNodes(TreeNode root) {
        midTrans(root, Integer.MIN_VALUE);
        return count;
    }

    private void midTrans(TreeNode root, int max) {
        if (root == null) {
            return;
        }
        if (root.val >= max) {
            count++;
        }
        midTrans(root.left, Math.max(max, root.val));
        midTrans(root.right, Math.max(max, root.val));
    }

    @Override
    public Object initData() {
        return null;
    }
}