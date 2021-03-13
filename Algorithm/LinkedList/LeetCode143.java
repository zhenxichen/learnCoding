public class LeetCode143 {
    /* LeetCode 143 重排链表 */
    /**
     * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * 示例 1:
     * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
     */
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        s.reorderList(head);
    }
}

// 主要分成三个部分：寻找链表中点 + 翻转链表后半段 + 合并链表
class Solution {
    public void reorderList(ListNode head) {
        ListNode mid = getMidNode(head);
        ListNode newMid = reverseList(mid.next);
        mid.next = null;        // 需要打断两个链表，否则会产生环
        mergeList(head, newMid);
    }

    // 用快慢指针找到链表的中点
    private ListNode getMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 翻转链表
    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode nex = null;
        while (cur != null) {
            nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }
        return pre;
    }

    // 合并链表
    private void mergeList(ListNode l1, ListNode l2) {
        ListNode temp1;
        ListNode temp2;
        while (l1 != null && l2 != null) {
            temp1 = l1.next;
            temp2 = l2.next;
            l1.next = l2;
            l1 = temp1;
            l2.next = l1;
            l2 = temp2;
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}