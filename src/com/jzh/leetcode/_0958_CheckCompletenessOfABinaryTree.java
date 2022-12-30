package com.jzh.leetcode;

import com.jzh.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树的root，确定它是否是一个完全二叉树。
 *
 * 在一个完全二叉树中，除了最后一个关卡外，所有关卡都是完全被填满的，
 *
 * 思路：
 *  如果有右孩子没左孩子则不是完全二叉树
 *  如果儿女不双全，则之后的节点都不应该有孩子（按层遍历）
 */
public class _0958_CheckCompletenessOfABinaryTree {

    public static void main(String[] args) {
        TreeNode root = _0297_SerializeAndDeserializeBinaryTree.deserialize("1,2,3,null,null,7,8");
        System.out.println(isCompleteTree(root));
    }

    public static boolean isCompleteTree(TreeNode root) {
        if(root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // 所有之前的节点都是儿女双全
        boolean flag = true;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.left != null || node.right != null) {
                // 存在子节点时，如果之前已经有节点不是儿女双全，则是不完全二叉树
                if (!flag) {
                    return false;
                }
                // 当有右孩子但没左孩子时，为不完全二叉树
                if (node.left == null) {
                    return false;
                }

                // 当只有一个孩子时，则为儿女不双全，将flag置为false
                if (node.right == null) {
                    flag = false;
                    queue.add(node.left);
                } else {
                    // 儿女双全时，直接入队
                    queue.add(node.left);
                    queue.add(node.right);
                }
            } else {
                // 当没有子女时，将flag设为false
                flag = false;
            }
        }

        return true;
    }
}
