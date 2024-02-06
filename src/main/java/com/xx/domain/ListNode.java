package com.xx.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Set<ListNode> set = new HashSet<>();
        sb.append(this.val);
        sb.append(" -> ");
        ListNode point = this.next;
        while (point != null && !set.contains(point)) {
            set.add(point);
            sb.append(point.val);
            sb.append(" -> ");
            point = point.next;
        }
        sb.append("null");
        return sb.toString();
    }
}
