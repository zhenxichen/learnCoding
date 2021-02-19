public class NC3 {
    /* NC 3 链表中环的入口节点 */
    /**
     * 对于一个给定的链表，返回环的入口节点，如果没有环，返回null
     * 拓展：
     * 你能给出不利用额外空间的解法么？
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;    // 先通过快慢指针判断是否有环
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
            // 设到达环入口之前的距离为a, 环入口距离第一个交点的距离为b，第一个交点回到环入口的距离为c
            // 我们假设快慢指针第一次相交时，快指针绕环 n 圈
            // 根据快指针的速度是慢指针的两倍，我们可以得到以下式子：
            // a + b + n(b + c) = 2(a + b)
            // 从而可以得到 a = (n-1)(b+c) + c
            // 因此，a的距离等于绕环走n-1圈之后再回到入口的距离
            if (fast == slow) {    // 检测到相交
                fast = head;        // 将fast移回初始位置，并与慢指针同速度前进
                while (fast != slow) {    // 两者交点即为环的入口
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        // 若fast走到了Null，说明不存在环
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