package com.zzh.algorithm.dobblepointer;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/3/4 20:28
 */
public class Algorithm02 {
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

    public static ListNode partition(ListNode head, int x) {

        ListNode left = new ListNode(-1);
        ListNode leftPoint = left;
        ListNode right = new ListNode(-1);
        ListNode rightPoint = right;

        while (head != null) {
            if (head.val < x) {
                leftPoint.next = head;
                leftPoint = leftPoint.next;
            } else {
                rightPoint.next = head;
                rightPoint = rightPoint.next;
            }
            ListNode temp = head.next;
            head.next = null;
            head = temp;
        }
        leftPoint.next = right.next;
        return left.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        partition(head, 3);
    }
}
