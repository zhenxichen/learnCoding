public class LeetCode206 {
    /* LeetCode 206 反转链表 */
    /**
     * 反转一个单链表。
     * 示例:
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * 进阶:
     * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     */
    public static void main(String[] args) {
        Solution1 s1 = new Solution1();     // 迭代解
        Solution2 s2 = new Solution2();     // 递归解
    }
}

// 迭代解
class Solution1 {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        ListNode nex = head;
        while (curr != null) {
            nex = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nex;
        }
        return pre;
    }
}

// 递归解
class Solution2 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);      // 从后往前进行翻转
        head.next.next = head;
        head.next = null;           // 使第一个节点的next为null, 防止形成环
        return newHead;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}