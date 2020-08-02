import java.util.ArrayList;
import java.util.List;

public class LeetCode114 {
    /* LeetCode 114 二叉树展开为链表 */
    // tag: Binary Tree
    /**
     * 给定一个二叉树， 原地 将它展开为一个单链表。
     * 例如，给定二叉树
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     * 将其展开为：
     * 1
     *  \
     *   2
     *    \
     *     3
     *      \
     *       4
     *        \
     *         5
     *          \
     *           6
     */
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        solution1.flatten(new TreeNode(0));
    }
}

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

// Follow Up解法：寻找前驱结点
// 当前结点右子树的前驱结点，即为当前结点左子树的最右侧结点
class Solution {
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while(curr != null) {
            if(curr.left != null) {
                TreeNode next = curr.left;      // 获取当前结点的左子树
                TreeNode prev = next;
                while(prev.right != null) {     // 获取左子树的最右结点
                    prev = prev.right;
                }
                prev.right = curr.right;        // 将当前结点的右子树移动到前驱结点的右子树
                curr.right = next;              // 将当前结点的左子树移动到右侧
                curr.left = null;
            }
            curr = curr.right;
        }
    }
}


// 前序遍历： 直接用List存储所有结点
// 时间复杂度：O(n) 空间复杂度：O(n)
// Follow up: 能否用O(1)的空间复杂度解决问题？
class Solution1 {
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preOrder(root, list);
        TreeNode node = root;
        for(int i = 1; i < list.size(); i++) {
            TreeNode curr = list.get(i);
            node.left = null;
            node.right = curr;
            node = node.right;
        }
    }

    // 前序遍历
    void preOrder(TreeNode node, List<TreeNode> list) {
        if(node == null) { return; }
        list.add(node);
        preOrder(node.left, list);
        preOrder(node.right, list);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}