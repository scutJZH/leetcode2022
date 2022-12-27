package com.jzh.leetcode;

import com.jzh.util.TreeNode;

/**
 * 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
 *
 * 思路：
 *  解1：找到头结点，进行中序遍历，找到下一个节点
 *
 *  解2：从结构上分析中序顺序，按规律找到后继节点，由于二叉搜索树中序遍历是单调递增，如果能找到存在值大于x节点的，则x节点一定有后继节点
 *      i: x有右孩子，x的后继节点为右孩子最左节点
 *      ii: // x没有右孩子，x往上找，一直找到某个祖先是某个节点的左孩子，该节点为后继节点(节点有parent指针时可用)
 *          如果节点x的右子树为空，则后继节点一定在祖先节点中，则需要从根节点开始遍历寻找节点x的祖先节点。
 *      iii: 则没有后继节点
 */
public class _0285_InorderSuccessorInBst {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        if (p.right != null) {
            // 找到右孩子的最左节点
            TreeNode pt = p.right;
            while (pt.left != null) {
                pt = pt.left;
            }
            successor = pt;
        } else {
            // 找p的祖先节点中有没有比p节点更大的节点
            TreeNode node = root;
            while (node != null) {
                if (node.val > p.val) {
                    successor = node;
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
        }
        return successor;
    }
}
