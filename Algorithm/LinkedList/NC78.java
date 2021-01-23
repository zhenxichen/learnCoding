public class NC78 {
    /* NC78 反转链表 */
    /**
     * 输入一个链表，反转链表后，输出新链表的表头。
     * 示例 1:
     * 输入: {1, 2, 3}
     * 返回值: {3, 2, 1}
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Solution {
    public ListNode ReverseList(ListNode head) {
        if (head == null) { return null; }
        ListNode pre = null;
        ListNode cur = head;
        ListNode nex = null;
        while (cur.next != null) {
            nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }
        cur.next = pre;
        return cur;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}