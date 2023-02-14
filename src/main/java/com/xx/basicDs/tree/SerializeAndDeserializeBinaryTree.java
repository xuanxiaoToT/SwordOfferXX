package com.xx.basicDs.tree;

import com.xx.Answer;
import com.xx.domain.TreeNode;
import com.xx.util.DataFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author XuanXiao
 * @CreateDate 2022/9/22
 * 序列化和反序列化二叉树
 * <p>
 * 请设计一个算法将二叉树序列化成一个字符串，并能将
 * 该字符串反序列化出原来二叉树的算法。
 */
public class SerializeAndDeserializeBinaryTree implements Answer {

    public static void main(String[] args) {
        new SerializeAndDeserializeBinaryTree().answerOne();
    }

    /**
     * 注意一定要按照完全二叉树来进行序列化和反序列化，null值用特殊的字符串，如'#'替代。
     */
    @Override
    public void answerOne() {
        // TreeNode treeNode = initData();
        // String s = treeLevelSerialize(treeNode);
        String[] pre = new String[]{"1", "2", "3", "4", "5", "6", "7"};
        TreeNode treeNodePre = treeDeSerialize(pre, 0, pre.length - 1);
        System.out.println(treeNodePre);

    }

    public TreeNode treeDeserialize(String str) {
        //将层序反序列化，利用数学公式  i是父节点，2i是left，2i+1是right
        String[] split = str.split(",");
        for (int i = 0; i < split.length; i++) {
            //略
        }
        return null;
    }

    public String treePreSerialize(TreeNode node) {
        if (node == null) {
            return "#";
        }
        String leftStr = treePreSerialize(node.left);
        String rightStr = treePreSerialize(node.right);
        return String.valueOf(node.value) + "," + leftStr + "," + rightStr;
    }

    //前序遍历的反序列化，其他中序，后续同理。切割即可。
    public TreeNode treeDeSerialize(String[] nodeStr, int start, int end) {
        if (start < 0 || end >= nodeStr.length || start > end) {
            return null;
        }
        String rootStr = nodeStr[start];
        if ("#".equals(rootStr)) {
            return null;
        } else {
            TreeNode treeNode = new TreeNode(Integer.parseInt(rootStr));
            int mid = (start + end + 1) / 2;
            treeNode.left = treeDeSerialize(nodeStr, start + 1, mid);
            treeNode.right = treeDeSerialize(nodeStr, mid + 1, end);
            return treeNode;
        }

    }

    public String treeLevelSerialize(TreeNode node) {
        // 可以利用层序遍历，把null也显示出来
        List<String> list = new ArrayList<>();
        Queue<TreeNode> outQueue = new LinkedList<>();
        if (node != null) {
            outQueue.add(node);
        }
        while (!outQueue.isEmpty()) {
            TreeNode temp = outQueue.poll();
            if (temp != null) {
                list.add(String.valueOf(temp.value));
                outQueue.add(temp.left);
                outQueue.add(temp.right);
            } else {
                // # 表示null
                list.add("#");
            }
        }
        return String.join(",", list);
    }

    /**
     * something
     */
    @Override
    public TreeNode initData() {
        return DataFactory.generateTreeNode();
    }
}