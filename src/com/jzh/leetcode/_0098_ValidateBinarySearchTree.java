package com.jzh.leetcode;

import com.jzh.util.TreeNode;

/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 *     节点的左子树只包含 小于 当前节点的数。
 *     节点的右子树只包含 大于 当前节点的数。
 *     所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 思路：左子树为BST，右子树为BST，且满足左子树最大节点小于根节点，右子树最小节点大于根节点。
 */
public class _0098_ValidateBinarySearchTree {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(6);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        boolean isBST = isValidBST(node1);
        System.out.println(isBST);
    }

    public static boolean isValidBST(TreeNode root) {
        return process(root).isBST;
    }

    public static Info process(TreeNode root) {
        if (root == null) {
            return null;
        }

        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        int max = root.val;
        int min = root.val;
        boolean isBST = true;

        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            // 左子树为BST，左子树最大节点小于根节点
            isBST = isBST && leftInfo.isBST && leftInfo.max < root.val;
        }

        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            // 右子树为BST，右子树最小节点大于根节点
            isBST = isBST && rightInfo.isBST && rightInfo.min > root.val;
        }

        return new Info(max, min, isBST);
    }

    public static class Info {
        public int max;
        public int min;
        public boolean isBST;

        public Info(int max, int min, boolean isBST) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }
    }
}
