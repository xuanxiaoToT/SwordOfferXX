package com.xx.domain;

import lombok.Data;

@Data
public class BiLinkNode {
    public int value;

    public BiLinkNode previous;

    public BiLinkNode next;

    public BiLinkNode(int value) {
        this.value = value;
    }
}
