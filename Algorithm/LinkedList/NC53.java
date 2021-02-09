public class NC53 {
    /* NC 53 删除链表的倒数第n个节点 */
    /**
     * 给定一个链表，删除链表的倒数第n个节点并返回链表的头指针
     * 例如，
     *  给出的链表为:1->2->3->4->5, n= 2.
     *  删除了链表的倒数第n个节点之后,链表变为1->2->3->5.
     * 备注：
     * 题目保证n一定是有效的
     * 请给出请给出时间复杂度为O(n)的算法
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    /**
     *
     * @param head ListNode类
     * @param n int整型
     * @return ListNode类
     */
    public ListNode removeNthFromEnd (ListNode head, int n) {
        // write code here
        ListNode second = head;        // 第二个结点比第一个节点领先n个
        ListNode first = head;        // 那么当第二个结点到达null时，第一个指针则为倒数第n个
        for (int i = 0; i < n; i++) {
            second = second.next;
        }
        ListNode prev = null;
        while (second != null) {
            second = second.next;
            prev = first;
            first = first.next;
        }
        if (prev == null) {
            return first.next;
        }
        prev.next = first.next;
        return head;
    }
}

class ListNode{
    int val;
    ListNode next = null;
}