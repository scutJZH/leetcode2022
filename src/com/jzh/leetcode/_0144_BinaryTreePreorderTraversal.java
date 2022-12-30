package com.jzh.leetcode;

import com.jzh.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给你二叉树的根节点 root ，返回它节点值的前序遍历。
 * 前序遍历：父节点、左子树、右子树
 *
 * 思路1：递归
 * 思路2：利用辅助栈，栈顶弹出，有右压右，有左压左，先右后左（头左右）
 */
public class _0144_BinaryTreePreorderTraversal {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.right = new TreeNode(2);
        node.right.left = new TreeNode(3);
        System.out.println(preorderTraversal(node));
        System.out.println(preorderTraversal2(node));
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);
        return list;
    }

    public static void preOrder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        list.add(node.val);
        preOrder(node.left, list);
        preOrder(node.right, list);
    }

    public static List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }

        return list;
    }
}
