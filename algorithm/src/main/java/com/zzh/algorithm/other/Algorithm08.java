package com.zzh.algorithm.other;

/**
 * @Description: 移除链表元素
 * @Author: zzh
 * @Crete 2023/8/29 21:09
 */
public class Algorithm08 {


    public static void main(String[] args) {

        ListNode head = new ListNode();
        head.val = 1;
        ListNode next = new ListNode();
        next.val = 2;
        head.next = next;
        ListNode next1 = new ListNode();
        next1.val = 2;
        next.next = next1;
        ListNode next2 = new ListNode();
        next2.val = 1;
        next1.next = next2;

        removeElements1(head, 2);
    }

    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode point = head;
        while (head != null && head.next != null) {
            if (head.next.val == val) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return point;
    }


    public static ListNode removeElements1(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode cur = pre;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return pre.next;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
