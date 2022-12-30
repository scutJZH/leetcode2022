package com.jzh.leetcode;

import com.jzh.common.TreeNode;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 思路：递归
 * 子树返回体：①是否已经有公共祖先；②是否含有p；③是否含有q
 */
public class _0236_LowestCommonAncestorOfABinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return findAncestor(root, p, q).ancestor;
    }

    public static Info findAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return new Info(null, false, false);
        }

        Info leftInfo = findAncestor(root.left, p, q);
        Info rightInfo = findAncestor(root.right, p, q);

        boolean findP = leftInfo.findP || rightInfo.findP || root == p;
        boolean findQ = leftInfo.findQ || rightInfo.findQ || root == q;

        TreeNode ancestor = null;
        if (leftInfo.ancestor != null) {
            ancestor = leftInfo.ancestor;
        } else if (rightInfo.ancestor != null) {
            ancestor = rightInfo.ancestor;
        } else if (findP && findQ) {
            ancestor = root;
        }

        return new Info(ancestor, findP, findQ);
    }

    public static class Info {
        public TreeNode ancestor;
        public boolean findP;
        public boolean findQ;

        public Info(TreeNode ancestor, boolean findP, boolean findQ) {
            this.ancestor = ancestor;
            this.findP = findP;
            this.findQ = findQ;
        }
    }
}
