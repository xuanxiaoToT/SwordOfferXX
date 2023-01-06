package com.xx.domain;

import lombok.Data;

@Data
public class BiChildLinkNode {
    public int value;

    public BiChildLinkNode previous;

    public BiChildLinkNode next;

    public BiChildLinkNode child;

    public BiChildLinkNode(int value) {
        this.value = value;
    }
}