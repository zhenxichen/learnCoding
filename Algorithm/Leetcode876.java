public class Leetcode876 {
    /* LeetCode 876 Middle of the Linked List */
    /**Given a non-empty, singly linked list with head node head, return a middle node of linked list.
     * If there are two middle nodes, return the second middle node. */
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    //最直接的想法当然是遍历两次
    //但是有更快的方法，即双指针（快的一次走2格，慢的一次走1格）
    public static void main(String[] args){
        Solution s = new Solution();
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {val = x;}
}

class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        boolean slowGo = true;      //初值的真假与出现双中间结点时返回左右有关
        while(fast.next != null){
            fast = fast.next;
            if(slowGo){
                slow = slow.next;
            }
            slowGo = !slowGo;
        }
        return slow;
    }
}
