package com.zui.test.tree;

import lombok.AllArgsConstructor;

/**
 * @author zui
 * @date 2019.11.15 9:13
 */
public class BinaryTree {

    public static void main(String[] args) {
        TreeNode a = new TreeNode(null, 1, null);
        TreeNode b = new TreeNode(a, 2, null);
        pre(b);
    }

    public static void pre(TreeNode treeNode) {
        System.out.println(treeNode.data);
        if (treeNode.left != null) {
            pre(treeNode.left);
        }
        if (treeNode.right != null) {
            pre(treeNode.right);
        }
    }
}

class TreeNode {
    public TreeNode(TreeNode left, int data, TreeNode right) {
        this.left = left;
        this.data = data;
        this.right = right;
    }

    TreeNode left;
    int data;
    TreeNode right;
}
