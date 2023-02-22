package com.jzh.leetcode;

import com.jzh.common.TreeNode;

/**
 * 给定一个二叉树，找到其中最大的二叉搜索树（BST）子树，并返回该子树的大小。其中，最大指的是子树节点数最多的。
 * 二叉搜索树（BST）中的所有节点都具备以下属性：
 *     左子树的值小于其父（根）节点的值。
 *     右子树的值大于其父（根）节点的值。
 * 注意：子树必须包含其所有后代。
 *
 *
 * 思路：
 *  二叉搜索树的条件：左右子树是二叉搜索树，左子树max小于自己，右子树min大于自己。
 *  所以返回体需要：①左右子树是否为二叉搜索树，②子树的max和min，③左右子树节点数，④左右子树最大BST节点数
 */
public class _0333_LargestBstSubtree {
    public static int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).largestBSTNodeNum;
    }

    public static Info process(TreeNode node) {
        if (node == null) {
            return null;
        }

        boolean isBST = true;
        int max = node.val;
        int min = node.val;
        int nodeNum = 1;
        int leftBSTNodeNum = 0;
        int rightBSTNodeNum = 0;

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        if (leftInfo != null) {
            isBST = isBST && leftInfo.isBST && node.val > leftInfo.max;
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
            nodeNum += leftInfo.nodeNum;
            leftBSTNodeNum = leftInfo.largestBSTNodeNum;
        }

        if (rightInfo != null) {
            isBST = isBST && rightInfo.isBST && node.val < rightInfo.min;
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
            nodeNum += rightInfo.nodeNum;
            rightBSTNodeNum = rightInfo.largestBSTNodeNum;
        }

        int largestBSTNodeNum = isBST ? nodeNum : Math.max(Math.max(leftBSTNodeNum, rightBSTNodeNum), 1);

        return new Info(isBST, max, min, nodeNum, largestBSTNodeNum);
    }

    public static class Info {
        public boolean isBST;
        public int max;
        public int min;
        public int nodeNum;
        public int largestBSTNodeNum;

        public Info(boolean isBST, int max, int min, int nodeNum, int largestBSTNodeNum) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
            this.nodeNum = nodeNum;
            this.largestBSTNodeNum = largestBSTNodeNum;
        }
    }


}
