package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.BiChildLinkNode;

/**
 * @author XuanXiao
 * @CreateDate 2022/8/30
 * <p>
 * 展平多级双向链表
 * <p>
 * 在一个多级双向链表中，节点除了有两个指针分别指向
 * 前后两个节点，还有一个指针指向它的子链表，并且子链表也是一
 * 个双向链表，它的节点也有指向子链表的指针。请将这样的多级双
 * 向链表展平成普通的双向链表，即所有节点都没有子链表。
 * <p>
 * 展平的规则是一个节点的子链展平之后将插入该节点和它的下一个节点之
 * 间。
 */
public class FlattenMultiLevelBiLinkList implements Answer {

    public static void main(String[] args) {
        new FlattenMultiLevelBiLinkList().answer();
    }

    /**
     * 由于子链的节点还可能拥有子链，因此需要递归的进行
     * 时间复杂度为O(n)，空间为O(k)，即层数
     */
    @Override
    public void answer() {
        BiChildLinkNode head = initData();
        flattenNode(head);
    }

    /**
     * 关注子链的头尾节点即可。
     */
    private FlattenNodeResult flattenNode(BiChildLinkNode head) {
        BiChildLinkNode point = head;
        BiChildLinkNode tail = head;
        while (point != null) {
            if (point.child != null) {
                FlattenNodeResult flattenChild = flattenNode(point.child);
                BiChildLinkNode nextPoint = point.next;
                point.next = flattenChild.head;
                flattenChild.head.previous = point;
                flattenChild.tail = nextPoint;
                if (nextPoint != null) {
                    nextPoint.previous = flattenChild.tail;
                }
                tail = point;
                point = nextPoint;
            } else {
                tail = point;
                point = point.next;
            }
        }
        return new FlattenNodeResult(head, tail);
    }

    /**
     * something
     */
    @Override
    public BiChildLinkNode initData() {
        return new BiChildLinkNode(5);
    }


    public class FlattenNodeResult {
        public BiChildLinkNode head;
        public BiChildLinkNode tail;

        public FlattenNodeResult(BiChildLinkNode head, BiChildLinkNode tail) {
            this.head = head;
            this.tail = tail;
        }
    }
}