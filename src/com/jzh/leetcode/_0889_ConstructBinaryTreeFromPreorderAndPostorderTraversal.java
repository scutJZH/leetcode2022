package com.jzh.leetcode;

import com.jzh.util.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个整数数组，preorder 和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder 是同一棵树的后序遍历，重构并返回二叉树。
 *
 * 思路：
 *  递归，利用前序遍历找头结点，根据头结点的位置，通过左孩子头结点，在前序遍历左孩子最前，在后序遍历左孩子的最后，通过后序遍历找到index - postL找到左孩子数量。
 *  因为会常常用到后序遍历的下标差来计算孩子个数，为了避免频繁遍历，故可以使用map来记录Node和后序遍历中下标的映射关系。
 */
public class _0889_ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    public static void main(String[] args) {
        int[] preorder = new int[]{1,2,4,5,3,6,7};
        int[] postorder = new int[]{4,5,2,6,7,3,1};
        TreeNode root = constructFromPrePost(preorder, postorder);
        System.out.println(1);
    }

    public static TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        if (preorder == null || postorder == null || preorder.length != postorder.length || preorder.length == 0) {
            return null;
        }
        // 因为会常常用到中序遍历的下标差来计算孩子个数，为了避免频繁遍历，记录Node和中序遍历中下标的映射关系
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            map.put(postorder[i], i);
        }

        return constructFromPrePost(preorder, 0, preorder.length - 1, map, 0, postorder.length - 1);
    }

    public static TreeNode constructFromPrePost(int[] preorder, int preL, int preR, Map<Integer, Integer> map, int postL, int postR) {
        if (preL > preR || postL > postR) {
            return null;
        }

        // 用前序遍历的第一个作为头结点
        TreeNode root = new TreeNode(preorder[preL]);

        // 注意越界
        if(preL + 1 > preR) {
            return root;
        }
        // 左孩子头结点在前序遍历左孩子最前，在后序遍历中左孩子中最后，通过后序遍历头结点的位置，以此找到左节点的个数，以此找到前序遍历中左孩子的右边界
        int index = map.get(preorder[preL + 1]);

        root.left = constructFromPrePost(preorder, preL + 1, preL + index - postL + 1, map, postL, index);
        root.right = constructFromPrePost(preorder, preL + index - postL + 2, preR, map, index + 1, postR - 1);
        return root;
    }

}
