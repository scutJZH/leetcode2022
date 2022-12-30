package com.jzh.leetcode;

import com.jzh.common.TreeNode;

import java.util.*;

/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * 思路
 * 序列化：
 * ① 前序：不忽略空，按前序排序进行序列化
 * ② 中序：有歧义，不可以使用
 * ③ 后序：不忽略空，按后序排序进行序列化
 * ④ 按层：不忽略空，按层排序进行序列化
 * <p>
 * 反序列化：
 * ① 前序：先按分隔符获取到元素数组，按前序遍历的方式重建树（用queue，头左右）
 * ② 后序：先按分隔符获取到元素数组，按后序遍历的方式重建树（用stack，pop出来就是头右左）
 * ③ 按层：先按分隔符获取到元素数组，按层遍历的方式重建树
 */
public class _0297_SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        String s = "1, 2, 3, null, null, 4, 5";
        TreeNode root = deserialize(s);
        s = serialize1(root);
        root = deserialize1(s);
        System.out.println(s);
    }


    // 按层遍历编码
    public static String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        // 按层遍历
        List<String> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    list.add(String.valueOf(node.val));
                    queue.add(node.left);
                    queue.add(node.right);
                } else {
                    list.add("null");
                }
            }
        }

        String s = list.toString();
        return s.substring(1, s.length() - 1);
    }

    // 按层遍历解码
    public static TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] arr = data.split(",");

        if (arr.length == 0 || "null".equals(arr[0])) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(arr[0].trim()));
        queue.add(root);
        int i = 1;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (i < arr.length) {
                if (!"null".equals(arr[i])) {
                    node.left = new TreeNode(Integer.parseInt(arr[i].trim()));
                    queue.add(node.left);
                }
                i++;
            }
            if (i < arr.length) {
                if (!"null".equals(arr[i])) {
                    node.right = new TreeNode(Integer.parseInt(arr[i].trim()));
                    queue.add(node.right);
                }
                i++;
            }
        }
        return root;
    }

    // 前序遍历编码
    public static String serialize1(TreeNode root) {
        if (root == null) {
            return "";
        }
        List<String> result = new ArrayList<>();
        preorderSerialize(root, result);
        String s = result.toString();
        return s.substring(1, s.length() - 1);
    }

    public static void preorderSerialize(TreeNode node, List<String> list) {
        if (node == null) {
            list.add("null");
            return;
        }
        list.add(String.valueOf(node.val));
        preorderSerialize(node.left, list);
        preorderSerialize(node.right, list);
    }

    // 前序遍历解码
    public static TreeNode deserialize1(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] arr = data.split(", ");

        if (arr.length == 0 || "null".equals(arr[0])) {
            return null;
        }

        Queue<String> queue = new LinkedList<>();
        for (String s : arr) {
            queue.add(s.trim());
        }

        return preorderDeserialize(queue);
    }

    public static TreeNode preorderDeserialize(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }

        String s = queue.poll();
        if ("null".equals(s)) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(s));
        node.left = preorderDeserialize(queue);
        node.right = preorderDeserialize(queue);

        return node;
    }

    public static String serialize2(TreeNode root) {
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
            list.add("null");
            return;
        }

        postorderSerialize(root.left, list);
        postorderSerialize(root.right, list);
        list.add(String.valueOf(root.val));
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        if ("".equals(data)) {
            return null;
        }

        String[] arr = data.split(",");
        Stack<String> stack = new Stack<>();
        for (String s : arr) {
            stack.add(s.trim());
        }
        return postorderDeserialize(stack);
    }

    public static TreeNode postorderDeserialize(Stack<String> stack) {
        if (stack.isEmpty()) {
            return null;
        }

        String s = stack.pop();

        if ("null".equals(s)) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(s));
        node.right = postorderDeserialize(stack);
        node.left = postorderDeserialize(stack);

        return node;
    }

}
