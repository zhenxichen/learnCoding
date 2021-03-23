public class LeetCode142 {
    /* LeetCode 142 环形链表 II */
    /**
     *  给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     *  为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     *  如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
     *  说明：不允许修改给定的链表。
     *  进阶：你是否可以使用 O(1) 空间解决此题？
     *  示例 1：
     *  输入：head = [3,2,0,-4], pos = 1
     *  输出：返回索引为 1 的链表节点
     *  解释：链表中有一个环，其尾部连接到第二个节点。
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

// 详细题解可见：https://github.com/xuanqizi/learnCoding/blob/master/Algorithm/LinkedList/NC3.java
class Solution {
    public ListNode detectCycle(ListNode head) {
        // 先用快慢指针探测是否有环
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {     // 相交说明存在环
                fast = head;
                while (slow != fast) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
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