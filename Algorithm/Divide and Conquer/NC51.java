import java.util.ArrayList;
import java.util.List;

public class NC51 {
    /* 合并K个已排序的链表 */
    /**
     * 合并k个已排序的链表并将其作为一个已排序的链表返回。分析并描述其复杂度。
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        if (lists == null) { return null; }
        int k = lists.size();
        ListNode head = mergeLists(lists, 0, k - 1);
        return head;
    }

    private ListNode mergeLists(List<ListNode> lists, int left, int right) {
        if (left > right) {
            return null;
        }
        if (left == right) {
            return lists.get(left);
        }
        int mid = (left + right) / 2;
        ListNode l1 = mergeLists(lists, left, mid);
        ListNode l2 = mergeLists(lists, mid + 1, right);
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) { return l2; }
        if (l2 == null) { return l1; }
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l2.next, l1);
            return l2;
        }
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