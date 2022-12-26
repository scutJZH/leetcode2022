package com.jzh.leetcode;

import com.jzh.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树的根节点 root ，返回 它的 中序遍历. 左头右
 *
 * 思路：
 *  解1： 递归
 *  解2：
 *      ① 从头结点开始，将最左的孩子依次入栈。
 *      ② 弹出一个节点
 *      ③ 若有右孩子则将指针指向右孩子，并重复①；否则重复②
 */
public class _0094_BinaryTreeInorderTraversal {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.right = new TreeNode(2);
        node.right.left = new TreeNode(3);
        System.out.println(inorderTraversal(node));
        System.out.println(inorderTraversal2(node));
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    public static void inorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                // 将它和它的最左节点依次入栈
                stack.add(p);
                p = p.left;
            } else {
                p = stack.pop();
                list.add(p.val);
                p = p.right;
            }
        }

        return list;
    }
}
