package com.xx.util;


import com.xx.domain.ListNode;

/**
 * @author XuanXiao
 * @CreateDate 2022/7/18
 */
public class ArrayUtil {

    public static void swapNum(int[] nums, int indexOne, int indexTwo) {
        int temp = nums[indexOne];
        nums[indexOne] = nums[indexTwo];
        nums[indexTwo] = temp;
    }

    public static ListNode reverseLinkList(ListNode head) {
        ListNode point = head;
        ListNode newPoint = null;
        while (point != null) {
            ListNode cur = point;
            point = point.next;
            cur.next = newPoint;
            newPoint = cur;
        }
        return newPoint;
    }

}
