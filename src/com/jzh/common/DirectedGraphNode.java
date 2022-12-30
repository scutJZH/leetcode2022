package com.jzh.common;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraphNode {
    public int label;
    public List<DirectedGraphNode> neighbors;

    public DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<DirectedGraphNode>();
    }

    public DirectedGraphNode(int x, List<DirectedGraphNode> neighbors) {
        label = x;
        this.neighbors = neighbors;
    }
}
