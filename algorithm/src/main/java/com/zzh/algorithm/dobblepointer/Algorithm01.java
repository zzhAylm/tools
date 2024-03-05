package com.zzh.algorithm.dobblepointer;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/3/4 20:08
 */
public class Algorithm01 {

    public static void main(String[] args) {

    }

    public class ListNode {
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode point = head;
        ListNode point1 = list1;
        ListNode point2 = list2;

        while (point1 != null && point2 != null) {
            if (point1.val > point2.val) {
                point.next = point2;
                point2 = point2.next;
            } else {
                point.next = point1;
                point1 = point1.next;
            }
            point = point.next;
        }
        if (point1 != null) {
            point.next = point1;
        }

        if (point2 != null) {
            point.next = point2;
        }
        return head.next;
    }
}
