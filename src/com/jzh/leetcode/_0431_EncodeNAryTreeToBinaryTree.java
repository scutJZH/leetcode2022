package com.jzh.leetcode;

import com.jzh.common.Node;
import com.jzh.common.TreeNode;

import java.util.*;

/**
 * 设计一个算法，可以将 N 叉树编码为二叉树，并能将该二叉树解码为原 N 叉树。
 * 一个 N 叉树是指每个节点都有不超过 N 个孩子节点的有根树。类似地，一个二叉树是指每个节点都有不超过 2 个孩子节点的有根树。
 * 你的编码 / 解码的算法的实现没有限制，你只需要保证一个 N 叉树可以编码为二叉树且该二叉树可以解码回原始 N 叉树即可。
 *
 * 思路：
 *  转二叉树：将所有孩子都放到左叉树的右边界上(左边是我子树，右边是我兄弟)
 *
 */
public class _0431_EncodeNAryTreeToBinaryTree {

    // Encodes an n-ary tree to a binary tree.
    public static TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }

        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = encodeHelper(root.children);
        return newRoot;
    }

    public static TreeNode encodeHelper(List<Node> children) {
        if (children == null || children.size() == 0) {
            return null;
        }

        // 第一个孩子为左子树的头结点
        TreeNode first = encode(children.get(0));
        TreeNode pt = first;

        for (int i = 1; i < children.size(); i++) {
            // 右边都是兄弟
            pt.right = encode(children.get(i));
            pt = pt.right;
        }

        return first;
    }



    // Decodes your binary tree to an n-ary tree.
    public static Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }

        Node node = new Node(root.val);
        node.children = decodeHelper(root.left);

        return node;
    }

    public static List<Node> decodeHelper(TreeNode node) {
        List<Node> children = new ArrayList<>();
        if (node == null) {
            return children;
        }

        // 左孩子都是自己的children，右孩子都是自己的兄弟
        while (node != null) {
            children.add(decode(node));
            node = node.right;
        }

        return children;
    }
}
