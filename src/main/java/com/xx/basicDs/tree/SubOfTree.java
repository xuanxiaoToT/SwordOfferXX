package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;

/**
 * @author XuanXiao
 * @CreateDate 2022/6/22
 * 树的子结构
 * 输入两棵二叉树 A 和 B ，判断 B 是不是 A 的子结构。(约定空树不是任意一个树的子结构)
 * <p>
 * B 是 A 的子结构， 即 A 中有出现和B相同的结构和节点值。
 */
public class SubOfTree implements Answer {

    public static void main(String[] args) {
        SubOfTree subOfTree = new SubOfTree();
        subOfTree.answerOne();
    }

    @Override
    public void answerOne() {
        TreeNode ori = new TreeNode();
        TreeNode target = new TreeNode();

        isSubStructure(ori, target);
    }

    public boolean isSubStructure(TreeNode ori, TreeNode target) {
        if (ori == null || target == null) {
            return false;
        }
        return validate(ori, target) || isSubStructure(ori.left, target) || isSubStructure(ori.right, target);
    }

    public boolean validate(TreeNode ori, TreeNode headTarget) {
        if (ori == null && headTarget == null) {
            return true;
        }
        if (ori == null || headTarget == null) {
            return false;
        }

        return (ori.value == headTarget.value) &&
                validate(ori.left, headTarget.left) &&
                validate(ori.right, headTarget.right);
    }

    @Override
    public TreeNode initData() {
        return new TreeNode();
    }
}