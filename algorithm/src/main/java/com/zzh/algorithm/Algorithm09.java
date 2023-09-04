package com.zzh.algorithm;

/**
 * @Description: 链表设计
 * @Author: zzh
 * @Crete 2023/8/30 11:59
 */
public class Algorithm09 {

    /**
     * 你可以选择使用单链表或者双链表，设计并实现自己的链表。
     * <p>
     * 单链表中的节点应该具备两个属性：val 和 next 。val 是当前节点的值，next 是指向下一个节点的指针/引用。
     * <p>
     * 如果是双向链表，则还需要属性 prev 以指示链表中的上一个节点。假设链表中的所有节点下标从 0 开始。
     * <p>
     * 实现 MyLinkedList 类：
     * <p>
     * MyLinkedList() 初始化 MyLinkedList 对象。
     * int get(int index) 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1 。
     * void addAtHead(int val) 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。
     * void addAtTail(int val) 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
     * void addAtIndex(int index, int val) 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。如果 index 比长度更大，该节点将 不会插入 到链表中。
     * void deleteAtIndex(int index) 如果下标有效，则删除链表中下标为 index 的节点。
     */
    public static void main(String[] args) {

    }

    public static class Node {
        public Node(int val) {
            this.val = val;
        }

        public Node() {
        }

        private int val;
        private Node next;
    }

    public static class MyLinkedList {
        private Node node;

        public MyLinkedList() {
            node = new Node();
        }

        public int get(int index) {
            if (index < 0) return -1;
            int num = 0;
            Node header = this.node;
            while (header != null && num != index) {
                header = header.next;
                num++;
            }
            return header == null ? -1 : header.val;
        }

        public void addAtHead(int val) {
            Node newHeader = new Node(val);
            newHeader.next = node;
            this.node = newHeader;
        }

        public void addAtTail(int val) {
            if (node == null) {
                node = new Node(val);
                return;
            }
            Node point = node;
            while (point.next != null) {
                point = point.next;
            }
            point.next = new Node(val);
        }

        public void addAtIndex(int index, int val) {
            if (index < 0) {
                return;
            }
            if (index == 0) {
                this.addAtHead(val);
                return;
            }
            if (node == null) {
                return;
            }

            int num = 0;
            Node point = node;
            while (num < index && point.next != null) {
                num++;
            }

            Node next = point.next;
            Node newNode = new Node(val);
            point.next = newNode;
            newNode.next = next;
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || node == null) {
                return;
            }
            if (index == 0) {
                node = node.next;
                return;
            }

            int num = 0;
            Node point = node;
            while (num < index && point.next != null) {
                num++;
                point = point.next;
            }
            if (point.next != null) {
                point.next = point.next.next;
            }
        }
    }
}
