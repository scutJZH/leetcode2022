package com.jzh.leetcode;

import com.jzh.common.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
 * 树的 最大宽度 是所有层中最大的 宽度 。
 * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
 * 题目数据保证答案将会在32位 带符号整数范围内。
 *
 * 思路：对节点进行编码，按层遍历，并利用左子树的下标为2n，右子树的下标为2n+1的规律来找到下一层的宽度(考虑编号溢出，因为int是32位，所以n层树的节点树为 2 ^(n+1)-1，考虑到int是32位，故用64位的long可以满足)
 */
public class _0662_MaximumWidthOfBinaryTree {

    public static void main(String[] args) {
//        TreeNode node = new TreeNode(1);
//        node.left = new TreeNode(3);
//        node.right = new TreeNode(2);
//        node.left.left = new TreeNode(5);
//        node.left.right = new TreeNode(3);
//        node.right.right = new TreeNode(9);
//        System.out.println(widthOfBinaryTree(node));
        String data = "1,1,1,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,null,1,1,null,1,null,1,null,1,null,1,null";
        TreeNode root = _0297_SerializeAndDeserializeBinaryTree.deserialize(data);
        System.out.println(widthOfBinaryTree(root));
    }

    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 存储每个节点的下标
        Map<TreeNode, Long> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        map.put(root, 1L);
        int maxWidth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            long l = 0;
            long r = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                long index = map.get(node);
                if (node.left != null) {
                    queue.add(node.left);
                    long childIndex = 2 * index;
                    map.put(node.left, childIndex);
                    // 找到该层最左节点
                    if (l == 0) {
                        l = childIndex;
                    }
                    // 找到该层最后一个节点
                    r = childIndex;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    long childIndex = 2 * index + 1;
                    map.put(node.right, childIndex);
                    // 找到该层最左节点
                    if (l == 0) {
                        l = childIndex;
                    }
                    // 找到该层最后一个节点
                    r = childIndex;
                }
            }
            maxWidth = Math.max(maxWidth, (int)(r - l + 1));
        }

        return maxWidth;
    }
}
