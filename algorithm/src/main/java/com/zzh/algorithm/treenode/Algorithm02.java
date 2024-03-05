package com.zzh.algorithm.treenode;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/2/26 11:22
 */
public class Algorithm02 {

    public static void main(String[] args) {

    }

    int res;
    int count;

    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return res;
    }

    public void traverse(TreeNode treeNode, int k) {
        if (treeNode == null) {
            return;
        }
        traverse(treeNode.left, k);
        count++;
        if (count == k) {
            res = treeNode.val;
            return;
        }
        traverse(treeNode.right, k);
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
