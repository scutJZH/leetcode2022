package com.jzh.leetcode;

import com.jzh.util.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗二叉树
 *
 * 思路：
 *  递归，利用后序遍历找头结点，根据头结点的位置，通过中序遍历找到头结点左右孩子的个数，从前序遍历中分隔左右孩子。
 *  因为会常常用到中序遍历的下标差来计算孩子个数，为了避免频繁遍历，故可以使用map来记录Node和中序遍历中下标的映射关系。
 */
public class _0106_ConstructBinaryTreeFromInorderAndPostorderTraversal {

    public static void main(String[] args) {
        int[] inorder = new int[]{9,3,15,20,7};
        int[] postorder = new int[]{9,15,7,20,3};

        TreeNode root = buildTree(inorder, postorder);
        System.out.println(1);
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length || inorder.length == 0) {
            return null;
        }

        // 因为会常常用到中序遍历的下标差来计算孩子个数，为了避免频繁遍历，故可以使用map来记录Node和中序遍历中下标的映射关系。
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return buildTree(postorder, 0, postorder.length - 1, map, 0, inorder.length - 1);
    }

    public static TreeNode buildTree(int[] postorder, int postL, int postR, Map<Integer, Integer> map, int inL, int inR) {
        if (postL > postR || inL > inR) {
            return null;
        }

        // 后序遍历最后一个节点是头结点
        TreeNode node = new TreeNode(postorder[postR]);

        int inorderIndex = map.get(postorder[postR]);

        // 通过中序遍历找到左右节点个数，找到后序遍历中左右孩子的分界
        node.left = buildTree(postorder, postL, postL + inorderIndex - inL - 1, map, inL, inorderIndex - 1);
        node.right = buildTree(postorder, postL + inorderIndex - inL, postR - 1, map, inorderIndex + 1, inR);

        return node;
    }
}
