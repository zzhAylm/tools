package com.zzh.algorithm;

/**
 * @Description: 倒数地K个节点
 * @Author: zzh
 * @Crete 2023/8/17 18:52
 */
public class Algorithm02 {

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
        print(node);
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
