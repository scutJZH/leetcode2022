package com.jzh.leetcode;

import com.jzh.common.TreeNode;

import java.util.*;

/**
 * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
 * 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
 * 编码的字符串应尽可能紧凑。
 *
 * 思路：（利用BST的左节点都比头小，右节点都比头大来进行处理）
 *  序列化：
 *      ① 前序：不忽略空，按前序排序进行序列化
 *      ② 后序：不忽略空，按后序排序进行序列化
 *      ③ 按层：不忽略空，按层排序进行序列化
 *  反序列化：
 *      ① 前序：先按分隔符获取到元素数组，按前序遍历的方式重建树
 *      ② 后序：先按分隔符获取到元素数组，按后序遍历的方式重建树（用stack，pop出来就是头右左，找到头结点，依次遍历Stack，再按照左大右小重建树）
 *      ③ 按层：先按分隔符获取到元素数组，按层遍历的方式重建树
 */
public class _0449_SerializeAndDeserializeBst {

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        List<String> result = new ArrayList<>();
        postorderSerialize(root, result);
        String str = result.toString();
        return str.substring(1, str.length() - 1);
    }

    public static void postorderSerialize(TreeNode root, List<String> list) {
        if (root == null) {
            return;
        }

        postorderSerialize(root.left, list);
        postorderSerialize(root.right, list);
        list.add(String.valueOf(root.val));
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("".equals(data)) {
            return null;
        }

        String[] arr = data.split(",");
        Stack<Integer> stack = new Stack<>();
        for (String s : arr) {
            stack.add(Integer.valueOf(s.trim()));
        }
        return postorderDeserialize(Integer.MIN_VALUE, Integer.MAX_VALUE, stack);
    }

    public static TreeNode postorderDeserialize(int min, int max, Stack<Integer> stack) {
        if (stack.isEmpty() || stack.peek() < min || stack.peek() > max) {
            return null;
        }

        TreeNode node = new TreeNode(stack.pop());
        node.right = postorderDeserialize(node.val, max, stack);
        node.left = postorderDeserialize(min, node.val, stack);
        return node;
    }
}
