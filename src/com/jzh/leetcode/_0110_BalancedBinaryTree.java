package com.jzh.leetcode;

import com.jzh.common.TreeNode;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1 。
 *
 * 思路：
 *  递归：左子树为平衡二叉树、右子树为平衡二叉树、左右子树高度差不超过1
 */
public class _0110_BalancedBinaryTree {
    public static boolean isBalanced(TreeNode root) {
        return check(root).isBalanced;
    }

    public static Result check(TreeNode root) {
        if (root == null) {
            return new Result(0, true);
        }

        Result left = check(root.left);
        Result right = check(root.right);

        boolean isBalanced = (left.isBalanced && right.isBalanced) && (Math.abs(left.height - right.height) < 2);
        return new Result(Math.max(left.height, right.height) + 1, isBalanced);
    }

    public static class Result {
        public int height;
        public boolean isBalanced;

        public Result(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }
}
