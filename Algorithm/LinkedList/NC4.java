public class NC4 {
    /* NC 4 判断链表中是否有环 */
    /**
     * 判断给定的链表中是否有环。如果有环则返回true，否则返回false。
     * 你能给出空间复杂度O(1)的解法么？
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        boolean slowMove = false;
        while (fast != null) {
            fast = fast.next;
            if (slowMove) {
                slow = slow.next;
            }
            if (fast == slow) {
                return true;
            }
            slowMove = !slowMove;
        }
        return false;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}