package Tree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class LeetCode101 {
    /* LeetCode 101 对称二叉树 */

    /**
     * 给定一个二叉树，检查它是否是镜像对称的。
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     * 1
     * / \
     * 2   2
     * / \ / \
     * 3  4 4  3
     * Follow up: 你可以运用递归和迭代两种方法解决这个问题吗？
     */
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        Solution2 solution2 = new Solution2();
        Solution solution = new Solution();
    }
}

// 迭代解优化：单队列（好像也基本没变快）
// 1 ms	37.7 MB
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) { return true; }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);   // LinkedList实现的队列可以插入null
        queue.add(root.right);  // 因此无需检验left和right是否为null
        while(!queue.isEmpty()) {
            TreeNode p = queue.poll();
            TreeNode q = queue.poll();
            if(p == null && q == null) { continue; }
            if(p == null || q == null) { return false; }
            if(p.val != q.val) { return false; }
            queue.add(p.left);
            queue.add(q.right);
            queue.add(p.right);
            queue.add(q.left);
        }
        return true;
    }
}

// 迭代解：双队列
// 1 ms	37.9 MB
class Solution2 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(root.left);
        queue2.add(root.right);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            if (queue1.isEmpty() || queue2.isEmpty()) {  // 一个队列空了而另一个没空
                return false;                           // 则说明结构不对称
            }
            TreeNode p = queue1.poll();
            TreeNode q = queue2.poll();
            if (p == null && q == null) {
                continue;
            }
            if (p == null || q == null) {
                return false;
            }
            if (p.val != q.val) {
                return false;
            }
            queue1.add(p.left);
            queue1.add(p.right);
            queue2.add(q.right);
            queue2.add(q.left);
        }
        return true;
    }
}

// 递归解
// 0 ms	36.6 MB
class Solution1 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return symmetric(root.left, root.right);
    }

    boolean symmetric(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        if (!symmetric(p.left, q.right)) {
            return false;
        }
        return symmetric(p.right, q.left);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}