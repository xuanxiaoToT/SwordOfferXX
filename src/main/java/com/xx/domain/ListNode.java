package com.xx.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ListNode {

    public int value;
    public ListNode next;

    public ListNode(int value) {
        this.value = value;
    }

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    public ListNode() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Set<ListNode> set = new HashSet<>();
        sb.append(this.value);
        sb.append(" -> ");
        ListNode point = this.next;
        while (point != null && !set.contains(point)) {
            set.add(point);
            sb.append(point.value);
            sb.append(" -> ");
            point = point.next;
        }
        sb.append("null");
        return sb.toString();
    }
}
