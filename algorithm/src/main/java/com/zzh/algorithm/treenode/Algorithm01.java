package com.zzh.algorithm.treenode;

/**
 * @Description: 105. 从前序与中序遍历序列构造二叉树
 * @Author: zzh
 * @Crete 2024/2/26 10:08
 */
public class Algorithm01 {


    //
    public static void main(String[] args) {
        Algorithm01 algorithm01 = new Algorithm01();
        int[] preorder = {1, 2};
        int[] inorder = {2, 1};
        algorithm01.buildTree(preorder, inorder);

    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int length = preorder.length;
        if (length == 0) {
            return null;
        }
        if (length == 1) {
            return new TreeNode(preorder[0]);
        }
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);

    }

    public TreeNode build(int[] preorder, int left, int right, int[] inorder, int orderLeft, int orderRight) {
        int length = preorder.length;
        if (left > right || left > length || right > length) {
            return null;
        }
        TreeNode treeNode = new TreeNode(preorder[left]);
        if (left == right) {
            return treeNode;
        }
        int index = orderLeft;
        for (; index <= orderRight; index++) {
            if (preorder[left] == inorder[index]) {
                break;
            }
        }
        int count = index - orderLeft;
        treeNode.left = build(preorder, left + 1, left + count, inorder, orderLeft, index - 1);
        treeNode.right = build(preorder, left + 1 + count, right, inorder, index + 1, orderRight);
        return treeNode;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
