package com.jzh.algorithm;

import com.jzh.util.TreeNode;

import java.util.HashMap;
import java.util.Map;

// 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
//百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
public class _0236_LowestCommonAncestorOfABinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 从root开始递归查找，如果p和q在同一个子树中，则还有更近的公共祖先，否则自身为最近的公共祖先。
        TreeNode node = findPublicNode(root, p, q);
        System.out.println(node.val);
        return node;
    }

    public static TreeNode findPublicNode(TreeNode node, TreeNode p, TreeNode q) {
        if (node == p || node == q) {
            return node;
        }
        // 如果某一边没有子节点，则公共祖先肯定在另一边
        if (node.left == null) {
            return findPublicNode(node.right, p, q);
        }
        if (node.right == null) {
            return findPublicNode(node.left, p, q);
        }
        // 两边都不为空的情况
        boolean leftResult = findNode(node.left, p, q);
        boolean rightResult = findNode(node.right, p, q);
        // 若两边都存在p或q，则当前节点为公共祖先，若某一边不存在，则公共祖先在另一边
        if (leftResult && rightResult) {
            return node;
        } else if (leftResult) {
            return findPublicNode(node.left, p, q);
        } else {
            return findPublicNode(node.right, p, q);
        }
    }

    public static boolean findNode(TreeNode node, TreeNode p, TreeNode q) {
        if (node == p || node == q) {
            return true;
        }
        if (node.left == null && node.right == null) {
            return false;
        }
        boolean leftResult = false;
        boolean rightResult = false;
        if (node.left != null) {
            leftResult = findNode(node.left, p, q);
        }
        if (node.right != null) {
            rightResult = findNode(node.right, p, q);
        }
        return leftResult || rightResult;
    }
}
