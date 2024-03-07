package com.zzh.algorithm.dobblepointer;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/3/5 21:15
 */
public class Algorithm04 {

    public static void main(String[] args) {


    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = new ListNode();
        p.next = head;
        ListNode fast = p;
        ListNode slow = p;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return p.next;
    }
}
