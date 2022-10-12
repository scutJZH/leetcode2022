package com.jzh.algorithm;

// 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
// 提醒一下，二叉搜索树满足下列约束条件：
//
//    节点的左子树仅包含键 小于 节点键的节点。
//    节点的右子树仅包含键 大于 节点键的节点。
//    左右子树也必须是二叉搜索树。
//
// 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同

import com.jzh.util.TreeNode;

// 思路：由于对二叉搜索树进行中序遍历（先读左子树，再读自己，最后读右子树）是从小到大排序的，故反向中序遍历并进行累加即可得到累加树
public class _0538_ConvertBstToGreaterTree {
    public static void main(String[] args) {
        TreeNode node0 = new TreeNode(4);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(3);
        TreeNode node8 = new TreeNode(8);

        node0.left = node1;
        node0.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node4.right = node7;
        node6.right = node8;

        _0538_ConvertBstToGreaterTree a = new _0538_ConvertBstToGreaterTree();

        TreeNode root = a.convertBST(node0);
    }

    private int total;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.right != null) {
            convertBST(root.right);
        }
        total += root.val;
        root.val = total;
        if (root.left != null) {
            convertBST(root.left);
        }
        return root;
    }

}
