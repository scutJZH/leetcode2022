package com.jzh.leetcode;

import com.jzh.util.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * 序列化是指将一个数据结构转化为位序列的过程，因此可以将其存储在文件中或内存缓冲区中，以便稍后在相同或不同的计算机环境中恢复结构。
 *
 * 设计一个序列化和反序列化 N 叉树的算法。一个 N 叉树是指每个节点都有不超过 N 个孩子节点的有根树。
 * 序列化 / 反序列化算法的算法实现没有限制。你只需要保证 N 叉树可以被序列化为一个字符串并且该字符串可以被反序列化成原树结构即可。
 *
 * 思路：
 *  序列化：深度优先遍历，可以用不同的分隔符来确定谁是谁的子树
 *
 *  反序列化：
 *      根据数组的首个元素创建根结点，将根结点入栈。遍历数组中的其余元素，对于每个元素，执行如下操作。
 *          ① 如果当前元素是左括号，则进入上一个结点的子树的后代，不执行任何操作。
 *          ② 如果当前元素是右括号，则当前子树的后代遍历结束，将栈顶结点出栈。
 *          ③ 如果当前元素是数字，则根据当前元素创建结点，并执行如下操作。
 *              i：如果上一个元素不是左括号，则栈顶结点和当前结点为兄弟结点，将栈顶结点出栈；如果上一个元素是左括号，则栈顶结点是当前结点的父结点，不将栈顶结点出栈。
 *              ii：此时的栈顶结点是当前结点的父结点，将当前结点添加到栈顶结点的子结点。
 *              iii：将当前结点入栈。
 *      遍历结束之后，即可得到原始 N 叉树，返回根结点。
 */
public class _0428_SerializeAndDeserializeNAryTree {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        node1.children = Arrays.asList(node3, node2, node4);
        node3.children = Arrays.asList(node5, node6);
        String data = serialize(node1);
        Node root = deserialize(data);
        System.out.println(data);
    }

    // Encodes a tree to a single string.
    public static String serialize(Node root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.val).append(",");
        if (root.children != null && !root.children.isEmpty()) {
            sb.append("[,");
            for (Node node : root.children) {
                sb.append(serialize(node));
            }
            sb.append("],");
        }

        return sb.toString();
    }


    // Decodes your encoded data to tree.
    public static Node deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        String[] strs = data.split(",");

        Stack<Node> stack = new Stack<>();
        Node root = new Node(Integer.parseInt(strs[0]), new ArrayList<>());
        stack.push(root);

        // 都想往孩子里加，看是否满足条件
        for (int i = 1; i < strs.length; i++) {
            if ("[".equals(strs[i])) {
                continue;
            } else if ("]".equals(strs[i])) {
                stack.pop();
            } else {
                Node node = new Node(Integer.parseInt(strs[i]), new ArrayList<>());
                // 如果上一个节点不是“[”，则新的节点是上一个节点的兄弟节点，需把上一个节点pop出来
                if (!"[".equals(strs[i - 1])) {
                    stack.pop();
                }
                stack.peek().children.add(node);
                stack.push(node);
            }
        }

        return root;
    }

}
