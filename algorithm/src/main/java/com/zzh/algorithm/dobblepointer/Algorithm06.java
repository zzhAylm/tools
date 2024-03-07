package com.zzh.algorithm.dobblepointer;

/**
 * @Description: 双指针
 * @Author: zzh
 * @Crete 2024/3/6 10:39
 */
public class Algorithm06 {

    public static void main(String[] args) {

    }

//142. 环形链表 II
    public ListNode detectCycle(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode p = head;
                while (p != slow) {
                    p = p.next;
                    slow = slow.next;
                }
                return p;
            }
        }
        return null;
    }

    //160. 相交链表
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode point1 = headA;
        ListNode point2 = headB;
        boolean flag1 = true;
        boolean flag2 = true;
        while (point2 != point1) {
            if (point1 == null && flag1) {
                flag1 = false;
                point1 = headB;
            } else if (point1 == null) {
                return null;
            } else {
                point1 = point1.next;
            }
            if (point2 == null && flag2) {
                flag2 = false;
                point2 = headA;
            } else if (point2 == null) {
                return null;
            } else {
                point2 = point2.next;
            }
        }
        return point1;
    }


    //26. 删除有序数组中的重复项
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int index = 0;

        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] != nums[index]) {
                nums[++index] = nums[i];
            }
        }
        return index + 1;
    }


    //83. 删除排序链表中的重复元素
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null) {
            if (slow.val == fast.val) {
                slow.next = fast.next;
            } else {
                slow = slow.next;
            }
            fast = slow.next;
        }
        return head;
    }

    //27. 移除元素
    public int removeElement(int[] nums, int val) {
        if (nums == null) {
            return 0;
        }
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }

    //283. 移动零
    public void moveZeroes(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int num = nums[index];
                nums[index] = nums[i];
                nums[i] = num;
                index++;
            }
        }
    }


    //    167. 两数之和 II - 输入有序数组
    public int[] twoSum(int[] numbers, int target) {

        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{-1, -1};
    }

    public void reverseString(char[] s) {
        if (s.length <= 1) {
            return;
        }
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            char c = s[left];
            s[left] = s[right];
            s[right] = c;
            left++;
            right--;
        }
    }

    //    5. 最长回文子串
    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        String str = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            String sub = subMid(s, i, i + 1);
            if (sub.length() > str.length()) {
                str = sub;
            }
            String sub2 = subMid(s, i, i);
            if (sub2.length() > str.length()) {
                str = sub2;
            }
        }
        return str;
    }

    public String subMid(String str, int left, int right) {
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return str.substring(left + 1, right);
    }



}
