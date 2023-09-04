package com.zzh.algorithm;

/**
 * @Description: 链表反转
 * @Author: zzh
 * @Crete 2023/8/31 09:53
 */
public class Algorithm10 {


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

        reverseBetween(head, 2, 4);
    }


    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null || left == right) {
            return head;
        }
        ListNode cur = head;
        ListNode firstLast = null;
        int index = 1;
        if (left != 1) {
            while (index < left - 1) {
                cur = cur.next;
                index++;
            }
            firstLast = cur;
            cur = cur.next;
            index++;
        }
        ListNode secondLast = cur;
        ListNode pre = null;
        while (cur != null && index <= right) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            index++;
        }
        if (secondLast != null) {
            secondLast.next = cur;
        }
        if (firstLast != null) {
            firstLast.next = pre;
            return head;
        }
        return pre;

    }


    public static ListNode reverseBetween1(ListNode head, int left, int right) {
        if (head == null || head.next == null || left == right) {
            return head;
        }

        ListNode dynamic = new ListNode(-1);
        dynamic.next = head;
        ListNode pre = dynamic;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode firstLast = pre;
        ListNode cur = pre.next;
        ListNode secondFirst = cur;
        int index = left;
        while (cur != null && index <= right) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            index++;
        }
        if (secondFirst!=null){
            secondFirst.next = cur;
        }
        if (firstLast.val != -1) {
            firstLast.next = pre;
            return head;
        }
        return pre;


    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode pre = null;

        while (cur != null) {
            ListNode newNext = cur.next;
            cur.next = pre;
            pre = cur;
            cur = newNext;
        }
        return pre;
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
