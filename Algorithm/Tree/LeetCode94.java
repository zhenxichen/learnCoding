import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetCode94 {
    /* LeetCode 94 二叉树的中序遍历 */
    /**
     * 给定一个二叉树，返回它的中序 遍历。
     * 示例:
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * 输出: [1,3,2]
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     */
    public static void main(String[] args) {

    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 用栈来迭代实现中序遍历（二叉树DFS使用栈，BFS使用队列）
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()) {
            while(curr != null) {
                // 先一路将左子树压栈
                stack.push(curr);
                curr = curr.left;
            }
            // 遍历到最左侧结点之后，从栈顶弹出元素
            curr = stack.pop();
            ans.add(curr.val);
            curr = curr.right;  // 去遍历右子树
        }
        return ans;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
