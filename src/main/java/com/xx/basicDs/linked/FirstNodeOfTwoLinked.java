package com.xx.basicDs.linked;

import com.xx.Answer;
import com.xx.domain.ListNode;
import com.xx.util.DataFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @author XuanXiao
 * @CreateDate 2022/7/14
 * <p>
 * 链表的相交节点
 * 链表相交
 * <p>
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 * 图示两个链表在节点 c1 开始相交：
 * <p>
 * 题目数据 保证 整个链式结构中不存在环。
 * <p>
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * <p>
 * <p>
 * 示例 1：
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Intersected at '8'
 * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * <p>
 * 示例 2：
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Intersected at '2'
 * 解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。
 * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 * <p>
 * 示例 3：
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
 * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 这两个链表不相交，因此返回 null 。
 * <p>
 * 提示：
 * listA 中节点数目为 m
 * listB 中节点数目为 n
 * 0 <= m, n <= 3 * 104
 * 1 <= Node.val <= 105
 * 0 <= skipA <= m
 * 0 <= skipB <= n
 * 如果 listA 和 listB 没有交点，intersectVal 为 0
 * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
 */
public class FirstNodeOfTwoLinked implements Answer {

    public static void main(String[] args) {
        new FirstNodeOfTwoLinked().answer();
    }

    /**
     * 长度法
     * 求出两个链表的长度，然后计算差值。
     */
    @Override
    public void answer() {
        ListNode root1 = initData();
        ListNode root2 = initData();
        int len1 = findLinkLength(root1);
        int len2 = findLinkLength(root2);
        System.out.println(len1);
        // if (len2 > len1) {
        //     int p = 0;
        //     ListNode r2 = root2;
        //     int n = len2 - len1;
        //     //  先走n步，最后一起。略
        // }

    }

    /**
     * 用set存储历史
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<ListNode>();
        ListNode temp = headA;
        while (temp != null) {
            visited.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while (temp != null) {
            if (visited.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    /**
     * 快慢指针
     * <p>
     * 设「第一个公共节点」为 node ，「链表 headA」的节点数量为 a ，「链表 headB」的节点数量为 b ，「两链表的公共尾部」的节点数量为 c ，则有：
     * <p>
     * 头节点 headA 到 node 前，共有 a−c 个节点；
     * 头节点 headB 到 node 前，共有 b−c 个节点；
     * <p>
     * 考虑构建两个节点指针 A, B 分别指向两链表头节点 headA , headB ，做如下操作：
     * <p>
     * 指针 A 先遍历完链表 headA ，再开始遍历链表 headB ，当走到 node 时，共走步数为：
     * a+(b−c)
     * 指针 B 先遍历完链表 headB ，再开始遍历链表 headA ，当走到 node 时，共走步数为：
     * b+(a−c)
     * 如下式所示，此时指针 A , B 重合，并有两种情况：
     * <p>
     * a+(b−c)=b+(a−c)
     * 若两链表 有 公共尾部 (即 c>0 ) ：指针 A , B 同时指向「第一个公共节点」node 。
     * 若两链表 无 公共尾部 (即 c=0 ) ：指针 A , B 同时指向 null 。
     * 因此返回 A 即可。
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        while (A != B) {
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }


    private int findLinkLength(ListNode node) {
        if (node == null) {
            return 0;
        }
        int len = 1;
        ListNode point = node;
        while (point != null) {
            point = point.next;
            len++;
        }
        return len - 1;
    }

    @Override
    public ListNode initData() {
        return DataFactory.generateLinkedList();
    }
}