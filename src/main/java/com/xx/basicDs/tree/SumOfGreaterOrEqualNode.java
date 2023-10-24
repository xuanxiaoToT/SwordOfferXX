package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.Stack;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/28
 * <p>
 * 所有大于或等于节点的值之和
 * LeetCode 538. 把二叉搜索树转换为累加树
 * <p>
 * 给定一棵二叉搜索树，请将它的每个节点的值替换成树
 * 中大于或等于该节点值的所有节点值之和。假设二叉搜索树中节点
 * 的值唯一。
 * <p>
 * 例如，输入如图8.10（a）所示的二叉搜索树，由于有两
 * 个节点的值大于或等于6（即节点6和节点7），因此值为6节点的值
 * 替换成13，其他节点的值的替换过程与此类似，所有节点的值替换
 * 之后的结果如图8.10（b）所示。
 */
public class SumOfGreaterOrEqualNode implements Answer {
    public static void main(String[] args) {
        new SumOfGreaterOrEqualNode().answerTwo();
    }

    /**
     * something
     */
    @Override
    public void answerOne() {
        TreeNode treeNode = initData();
        diGuiTree(treeNode, 0);
        System.out.println(treeNode);
    }

    /**
     * 笨比，直接右中左遍历，然后一个值记录之期的和即可。
     */
    private void answerTwo() {
        TreeNode treeNode = initData();
        TreeNode cur = treeNode;
        Stack<TreeNode> stack = new Stack<>();
        int sum = 0;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.right;
            }
            cur = stack.pop();
            sum = sum + cur.val;
            cur.val = sum;
            cur = cur.left;
        }

        System.out.println(treeNode);
    }


    // 右中左
    private int diGuiTree(TreeNode node, int fatherValue) {
        if (node == null) {
            return 0;
        }
        int result;
        int rightValue = diGuiTree(node.right, fatherValue);
        result = fatherValue + node.val + rightValue;
        node.val = result;
        int leftVlue = diGuiTree(node.left, result);
        return result;
    }

    /**
     * something
     */
    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}