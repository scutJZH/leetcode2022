package com.jzh.common;

import java.util.List;

public class Node {
    public int val;
    public Node next;
    public List<Node> children;
    public Node random;

    public Node() {}

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }

}
