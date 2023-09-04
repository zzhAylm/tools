package com.zzh.algorithm;

/**
 * @Description: 两两交换链表中的节点
 * @Author: zzh
 * @Crete 2023/9/1 21:30
 */
public class Algorithm11 {

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

    public static void main(String[] args) {
        ListNode head = new ListNode();
        head.val = 1;
        ListNode next = new ListNode();
        next.val = 2;
        head.next = next;
        ListNode next1 = new ListNode();
        next.next = next1;
        next1.val = 3;
        ListNode next2 = new ListNode();
        next1.next = next2;
        next2.val = 4;
        ListNode next3 = new ListNode();
        next2.next = next3;
        next3.val = 5;
        next3.next = null;

        swapPairs(head);
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dynamic = new ListNode();
        dynamic.next = head;
        ListNode cur = dynamic;
        ListNode next = cur.next;

        while (next != null && next.next != null) {
            ListNode second = next.next;
            next.next = second.next;
            cur.next = second;
            second.next = next;

            cur = next;
            next = cur.next;
        }
        return dynamic.next;

    }

}
