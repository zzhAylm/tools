package com.zzh.algorithm.dobblepointer;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/3/5 15:52
 */
public class Algorithm03 {

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

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        ListNode header = new ListNode();
        ListNode point = header;
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode head : lists) {
            if (head != null) {
                priorityQueue.add(head);
            }
        }
        while (!priorityQueue.isEmpty()) {
            ListNode poll = priorityQueue.poll();
            ListNode next = poll.next;
            if (next != null) {
                priorityQueue.add(next);
            }
            poll.next = null;
            point.next = poll;
            point = poll;
        }
        return header.next;

    }
}
