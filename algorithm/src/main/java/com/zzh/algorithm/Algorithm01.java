package com.zzh.algorithm;

/**
 * @Description: 反转
 * @Author: zzh
 * @Crete 2023/8/17 18:52
 */
public class Algorithm01 {

    public static void main(String[] args) {
        Node node = new Node();
        node.value = "1";
        node.next = new Node();
        node.next.value = "2";
        node.next.next = new Node();
        node.next.next.value = "3";
        node.next.next.next = new Node();
        node.next.next.next.value = "4";
        node.next.next.next.next = new Node();
        node.next.next.next.next.value = "5";
        print(ReverseList(node));
    }

    public static Node ReverseList(Node node) {
        Node header = node;
        Node next = null;
        Node pre = null;
        while (header != null) {
            next = header.next;
            header.next = pre;
            pre = header;
            header = next;
        }
        return pre;
    }

    public static class Node {
        private String value;
        private Node next;
    }

    public static void print(Node header) {
        while (header != null) {
            System.out.println(header.value);
            header = header.next;
        }
    }






}
