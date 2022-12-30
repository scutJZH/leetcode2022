package com.jzh.lintcode;

import com.jzh.common.DirectedGraphNode;

import java.util.*;

/**
 * 给定一个有向图，图节点的拓扑排序定义如下:
 *     对于图中的每一条有向边 A -> B , 在拓扑排序中A一定在B之前.
 *     拓扑排序中的第一个节点可以是图中的任何一个没有其他节点指向它的节点.
 * 针对给定的有向图找到任意一种拓扑排序的顺序.
 *
 * 思路：
 *  解1：使用入度，先统计每个节点的入度，然后先打印入度为0的节点，并依次将这些节点的邻居入度-1，然后重复以上步骤。
 *  解2：dfs，按照深度倒序打印
 *  解3：按照点次顺序打印，点次大小为邻居的点次大小之和（注意越界），如果没有邻居，点次为1（邻居中有重复的节点也直接进行统计）
 */
public class _0127_TopoSort {
    public static void main(String[] args) {
        DirectedGraphNode node0 = new DirectedGraphNode(0);
        DirectedGraphNode node1 = new DirectedGraphNode(1);
        DirectedGraphNode node2 = new DirectedGraphNode(2);
        DirectedGraphNode node3 = new DirectedGraphNode(3);
        DirectedGraphNode node4 = new DirectedGraphNode(4);
        DirectedGraphNode node5 = new DirectedGraphNode(5);

        node0.neighbors.addAll(Arrays.asList(node1, node2, node3));
        node1.neighbors.add(node4);
        node2.neighbors.addAll(Arrays.asList(node4, node5));
        node3.neighbors.addAll(Arrays.asList(node4, node5));


        ArrayList<DirectedGraphNode> list = new ArrayList<>(Arrays.asList(node0, node1, node2, node3, node4, node5));

        ArrayList<DirectedGraphNode> list2 = topSort3(list);
        for (DirectedGraphNode node : list2) {
            System.out.println(node.label);
        }

    }

    public static ArrayList<DirectedGraphNode> topSort1(ArrayList<DirectedGraphNode> graph) {
        if (graph == null || graph.size() == 0) {
            return new ArrayList<>();
        }

        Map<DirectedGraphNode, Integer> map = new HashMap<>(); // 入度
        for (DirectedGraphNode node : graph) {
            if (!map.containsKey(node)) {
                map.put(node, 0);
            }
            for (DirectedGraphNode neighbor : node.neighbors) {
                map.put(neighbor, map.getOrDefault(neighbor, 0) + 1);
            }
        }

        Queue<DirectedGraphNode> queue = new LinkedList<>(); // 入度为0的节点
        for (Map.Entry<DirectedGraphNode, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            result.add(node);

            for (DirectedGraphNode neighbor : node.neighbors) {
                map.put(neighbor, map.get(neighbor) - 1);
                if (map.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return result;
    }

    public static ArrayList<DirectedGraphNode> topSort2(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, DeepRecord> deepMap = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            dfs(node, deepMap);
        }

        List<DeepRecord> list = new ArrayList<>(deepMap.values());
        list.sort((o1, o2) -> o2.deep - o1.deep);

        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        for (DeepRecord record : list) {
            result.add(record.node);
        }

        return result;
    }

    public static class DeepRecord {
        DirectedGraphNode node;
        int deep;

        public DeepRecord(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    public static DeepRecord dfs(DirectedGraphNode node, Map<DirectedGraphNode, DeepRecord> deepMap) {
        if (deepMap.containsKey(node)) {
            return deepMap.get(node);
        }

        int deep = 1;
        for (DirectedGraphNode neighbor : node.neighbors) {
            deep = Math.max(dfs(neighbor, deepMap).deep + 1, deep);
        }

        DeepRecord record = new DeepRecord(node, deep);
        deepMap.put(node, record);
        return record;
    }

    public static ArrayList<DirectedGraphNode> topSort3(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, CountRecord> countMap = new HashMap<>();

        for (DirectedGraphNode node : graph) {
            getCount(node, countMap);
        }

        List<CountRecord> list = new ArrayList<>(countMap.values());
        list.sort((o1, o2) -> Long.compare(o2.count, o1.count));

        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        for (CountRecord countRecord : list) {
            result.add(countRecord.node);
        }

        return result;
    }

    public static CountRecord getCount(DirectedGraphNode node, Map<DirectedGraphNode, CountRecord> map) {
        if (map.containsKey(node)) {
            return map.get(node);
        }

        long count = 1; // 注意相加后越界
        for (DirectedGraphNode neighbor : node.neighbors) {
            count += getCount(neighbor, map).count;
        }

        CountRecord countRecord = new CountRecord(node, count);
        map.put(node, countRecord);
        return countRecord;
    }

    public static class CountRecord {
        DirectedGraphNode node;
        long count;

        public CountRecord(DirectedGraphNode node, long count) {
            this.node = node;
            this.count = count;
        }
    }
}
