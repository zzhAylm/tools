package com.zzh.algorithm.dobblepointer;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/3/6 10:31
 */
public class Algorithm05 {

    public static void main(String[] args) {

    }

    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
