package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

/**
 * @author 玄霄
 * @CreateDate 2022/9/29
 * 二叉搜索树中两个节点的值之和
 * <p>
 * 给定一棵二叉搜索树和一个值k，请判断该二叉搜索树中
 * 是否存在值之和等于k的两个节点。假设二叉搜索树中节点的值均唯
 * 一。例如，在如图8.12所示的二叉搜索树中，存在值之和等于12的
 * 两个节点（节点5和节点7），但不存在值之和为22的两个节点。
 */
public class SumOfTwoNodesInBinarySearchTree implements Answer {

    public static void main(String[] args) {
        new SumOfTwoNodesInBinarySearchTree().answerOne();
    }

    /**
     * 笨法1：全量遍历，每一个都让和减去，然后去搜索。n*h
     * 方法2：转为列表，然后双指针法。参考：TwoNumbersOfS
     * 方法3：转换为hash表。然后判断存在与否即可。
     * 方法4：按照BinarySearchTreeIterator的方式，采用双指针遍历。一个小向大，一个大向小。
     */
    @Override
    public void answerOne() {
        TreeNode treeNode = initData();
        boolean node = findNode(treeNode, 9, treeNode.right);
        System.out.println(node);
    }

    public boolean findNode(TreeNode root, int target, TreeNode it) {
        TreeNode cur = root;

        while (cur != null) {
            if (cur == it) {
                continue;
            }
            if (cur.value == target) {
                return true;
            } else {
                if (cur.value > target) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
        }
        return false;
    }

    /**
     * something
     */
    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}