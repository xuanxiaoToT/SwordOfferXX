package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.DataFactory;

/**
 * @author XuanXiao
 * @CreateDate 2023/7/20
 * <p>
 * 删除链表中的节点
 * LeetCode 237.
 * <p>
 * 有一个单链表的 head，我们想删除它其中的一个节点 node。
 * 给你一个需要删除的节点 node 。你将 无法访问 第一个节点  head。
 * 链表的所有值都是 唯一的，并且保证给定的节点 node 不是链表中的最后一个节点。
 * 删除给定的节点。注意，删除节点并不是指从内存中删除它。这里的意思是：
 * ·给定节点的值不应该存在于链表中。
 * ·链表中的节点数应该减少 1。
 * ·node 前面的所有值顺序相同。
 * ·node 后面的所有值顺序相同。
 * <p>
 * 自定义测试：
 * ·对于输入，你应该提供整个链表 head 和要给出的节点 node。node 不应该是链表的最后一个节点，
 * 而应该是链表中的一个实际节点。
 * ·我们将构建链表，并将节点传递给你的函数。
 * ·输出将是调用你函数后的整个链表。
 * <p>
 * 示例 1：
 * 输入：head = [4,5,1,9], node = 5
 * 输出：[4,1,9]
 * 解释：指定链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9
 * <p>
 * 示例 2：
 * 输入：head = [4,5,1,9], node = 1
 * 输出：[4,5,9]
 * 解释：指定链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9
 */
public class DeleteTheLinkNode implements Answer {

    public static void main(String[] args) {
        new DeleteTheLinkNode().answerOne();
    }

    /**
     * 解1：
     */
    @Override
    public void answerOne() {
        ListNode listNode = initData();
        deleteNode(listNode);
    }

    /**
     * 无法知道前一个节点的值，所以用后一个节点的值来替代，并删除后一个节点。
     */
    public void deleteNode(ListNode node) {

        node.value = node.next.value;
        node.next = node.next.next;
    }

    /**
     * 输出数据
     */
    @Override
    public ListNode initData() {
        return DataFactory.generateLinkedList();
    }
}
