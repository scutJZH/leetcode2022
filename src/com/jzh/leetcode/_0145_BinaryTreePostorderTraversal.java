package com.jzh.leetcode;

import com.jzh.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。 左右头
 * 思路：
 *  解1：递归
 *  解2：参考前序遍历（辅助栈：有右压右，有左压左，先右后左，能得到头左右）
 *      可以有左压左，有右压右，先左后右，得到头右左
 *      再使用另外一个辅助栈，得到左右头
 */
public class _0145_BinaryTreePostorderTraversal {
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    public static void postorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        postorder(node.left, list);
        postorder(node.right, list);
        list.add(node.val);
    }

    public static List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> valStack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            valStack.add(node.val);
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }

        while (!valStack.isEmpty()) {
            list.add(valStack.pop());
        }

        return list;
    }
}
