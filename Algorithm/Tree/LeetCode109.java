
public class LeetCode109 {
    /* LeetCode 109 有序链表转换二叉搜索树 */
    /**
     * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     * 示例:
     * 给定的有序链表： [-10, -3, 0, 5, 9],
     * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     */
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        Solution solution = new Solution();
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// Follow up: 将时间复杂度缩小到O(n)?
// 改为通过中序遍历操纵公共链表结点，无需每次对链表求中间结点
class Solution {
    ListNode globalHead;

    public TreeNode sortedListToBST(ListNode head) {
        globalHead = head;
        int length = getLength(head);
        return transferBST(0, length - 1);
    }

    int getLength(ListNode head) {
        int length = 0;
        ListNode curr = head;
        while(curr != null) {
            length ++;
            curr = curr.next;
        }
        return length;
    }

    TreeNode transferBST(int left, int right) {
        if(left > right) {
            return null;
        }
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode();
        root.left = transferBST(left, mid - 1);
        root.val = globalHead.val;
        globalHead = globalHead.next;
        root.right = transferBST(mid + 1, right);
        return root;
    }
}

// 解法：分治算法
// 时间复杂度：O(nlogn)
class Solution1 {
    public TreeNode sortedListToBST(ListNode head) {
        return transferBST(head, null);
    }

    TreeNode transferBST(ListNode left, ListNode right) {
        // right表示链表末尾
        // （right本身不包含在链表中，即right是最后一个结点的下一个结点）
        // 因此 若left.next = right，返回left
        // 若left = right，返回null
        if(left == right) {
            return null;
        }
        TreeNode root;
        ListNode mid = getMedian(left, right);
        root = new TreeNode(mid.val);
        root.left = transferBST(left, mid);
        root.right = transferBST(mid.next, right);
        return root;
    }

    ListNode getMedian(ListNode head, ListNode right) {      // 获取链表的中点
        // 获取有序链表的中点：快慢指针
        ListNode slow = head;
        ListNode fast = head;
        while(fast != right && fast.next != right) {
            // 快指针走两步，慢指针走一步
            // 当快指针到达终点时，慢指针的位置即为中间点
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() { }
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}