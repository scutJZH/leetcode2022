package com.jzh.leetcode;

import com.jzh.common.TreeNode;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 * 思路：如果经过x节点，则是左子树高度+右子树高度+1。如果不经过x节点，则为max(左子树max, 右子树max)。（注意是路径长度，为节点数 - 1）
 */
public class _0543_DiameterOfBinaryTree {
    public static int diameterOfBinaryTree(TreeNode root) {
        // 路径长度，为节点数-1
        return process(root).maxLength - 1;
    }

    public static Info process(TreeNode node) {
        if (node == null) {
            return new Info(0, 0);
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int maxLength = Math.max(Math.max(leftInfo.maxLength, rightInfo.maxLength), leftInfo.height + rightInfo.height + 1);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        return new Info(maxLength, height);
    }
    public static class Info {
        public int maxLength;
        public int height;
        public Info(int maxLength, int height) {
            this.maxLength = maxLength;
            this.height = height;
        }
    }
}
