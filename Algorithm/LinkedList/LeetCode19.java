public class LeetCode19 {
    /* LeetCode 19 删除链表的倒数第 N 个结点 */
    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * 进阶：你能尝试使用一趟扫描实现吗？
     */

    // 解法：双指针
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode slow = head;
            ListNode fast = head;
            for (int i = 0; i < n; i++) {
                fast = fast.next;
            }
            ListNode prev = head;
            if (fast == null) {
                return head.next;
            }
            while (fast != null) {
                fast = fast.next;
                prev = slow;
                slow = slow.next;
            }
            prev.next = slow.next;
            return head;
        }
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() { }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
