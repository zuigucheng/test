package com.zui.test.tree;

import lombok.AllArgsConstructor;

/**
 * @author zui
 * @date 2019.11.15 9:13
 */
public class BinaryTree {

    public static void main(String[] args) {
        TreeNode A = new TreeNode(null,1,null);
        TreeNode B = new TreeNode(A, 2, null);
        pre(B);
    }

    public static void pre(TreeNode treeNode){
        System.out.println(treeNode.data);

        if (treeNode.left!=null){
            pre(treeNode.left);
        }
        if (treeNode.right!=null){
            pre(treeNode.right);
        }
    }
}

@AllArgsConstructor
class TreeNode{
    TreeNode left;
    int data;
    TreeNode right;
}
