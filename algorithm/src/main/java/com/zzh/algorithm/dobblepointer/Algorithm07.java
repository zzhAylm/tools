package com.zzh.algorithm.dobblepointer;

/**
 * @Description: 二叉树
 * @Author: zzh
 * @Crete 2024/3/7 10:23
 */
public class Algorithm07 {


    //    104. 二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    public int maxDepth1(TreeNode root) {
        traverse(root, 0);
        return res;
    }

    int res = 0;

    //    这不奇怪，因为本文开头就说了前序位置是刚刚进入节点的时刻，后序位置是即将离开节点的时刻。
    public void traverse(TreeNode node, int dept) {
        if (node == null) {
            return;
        }
        dept = dept + 1;
        res = Math.max(res, dept);
        //       前序遍历,进入这个节点做什么
        traverse(node.left, dept);
        //       中序遍历，遍历完左子树后准备做什么
        traverse(node.right, dept);
        //       后序遍历，离开这个节点做什么

    }


    public void printLevel(TreeNode root) {
        traverse2(root, 0);
    }

    public void traverse2(TreeNode node, int dept) {
        if (node == null) {
            return;
        }
        dept = dept + 1;
        System.out.println("dept=" + dept);
        traverse2(node.left, dept);
        traverse2(node.right, dept);
    }


    //    543. 二叉树的直径
    public int diameterOfBinaryTree(TreeNode root) {
        traverse3(root);
        return maxDept;
    }

    int maxDept = 0;

    public int traverse3(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = traverse3(node.left);
        int right = traverse3(node.right);
        maxDept = Math.max(maxDept, left + right);
        return Math.max(left, right) + 1;
    }
}
