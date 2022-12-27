package com.jzh.leetcode;

import com.jzh.util.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个整数数组preorder 和 inorder，其中preorder 是二叉树的先序遍历， inorder是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 * 思路：
 * 递归，利用前序遍历找头结点，根据头结点的位置，通过中序遍历找到头结点左右孩子的个数，从前序遍历中分隔左右孩子。
 * 因为会常常用到中序遍历的下标差来计算孩子个数，为了避免频繁遍历，故可以使用map来记录Node和中序遍历中下标的映射关系。
 */
public class _0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};

        TreeNode root = buildTree(preorder, inorder);
        System.out.println(1);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length || preorder.length == 0) {
            return null;
        }
        // 因为会常常用到中序遍历的下标差来计算孩子个数，为了避免频繁遍历，记录Node和中序遍历中下标的映射关系
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1, map, 0, inorder.length - 1);
    }

    public static TreeNode buildTree(int[] preorder, int preL, int preR, Map<Integer, Integer> map, int inL, int inR) {
        if (preL > preR || inL > inR) {
            return null;
        }

        // 用前序遍历的第一个作为头结点
        TreeNode root = new TreeNode(preorder[preL]);

        // 找到中序遍历中该结点的位置，以此找到左节点的个数，以此找到左孩子的右边界
        int index = map.get(preorder[preL]);
        int leftPreR = preL + index - inL;

        root.left = buildTree(preorder, preL + 1, leftPreR, map, inL, index - 1);
        root.right = buildTree(preorder, leftPreR + 1, preR, map, index + 1, inR);
        return root;
    }
}
