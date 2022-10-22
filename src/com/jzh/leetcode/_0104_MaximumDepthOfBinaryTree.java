package com.jzh.leetcode;

import com.jzh.util.TreeNode;

// 给定一个二叉树，找出其最大深度。
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
// 说明: 叶子节点是指没有子节点的节点。
public class _0104_MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        TreeNode node0 = new TreeNode(4);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(2);

        node0.left = node1;
        node0.right = node2;
        node2.left = node3;
        node2.right = node4;

        System.out.println(new _0104_MaximumDepthOfBinaryTree().maxDepth(node0));
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
