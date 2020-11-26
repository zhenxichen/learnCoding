package Tree;

import java.util.*;

public class LeetCode103 {
    /* LeetCode 103 二叉树的锯齿形层次遍历 */
    /**
     * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回锯齿形层次遍历如下：
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     */
    public static void main(String[] args) {
        Solution103_Stack solution1 = new Solution103_Stack();
        Solution103_Deque solution2 = new Solution103_Deque();
    }
}

// 解法二：双向队列
// 执行用时：1 ms, 在所有 Java 提交中击败了98.52%的用户
// 内存消耗：38.9 MB, 在所有 Java 提交中击败了24.25%的用户
// 内存消耗居然比解法一还大？
class Solution103_Deque {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) { return ans; }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addFirst(root);
        int level = 0;
        while(!deque.isEmpty()) {
            int levelSize = deque.size();
            ans.add(new ArrayList<>());
            if(level % 2 == 0) {    // 从左往右
                for(int i = 0; i < levelSize; i++) {
                    TreeNode node = deque.poll();
                    ans.get(level).add(node.val);
                    if(node.left != null) { deque.add(node.left); }
                    if(node.right != null) { deque.add(node.right); }
                }
            } else {    // 从右往左
                for(int i = 0; i < levelSize; i++) {
                    TreeNode node = deque.pollLast();   // 取队列中的最后一个（也就是最右边的一个）
                    ans.get(level).add(node.val);
                    if(node.right != null) { deque.addFirst(node.right); }  // 注意需要从右向左插入队列
                    if(node.left != null) { deque.addFirst(node.left); }    // 这样最后在队列最前面的才会是左侧第一个
                }
            }
            level++;
        }
        return ans;
    }
}

// 解法一：利用栈来暂存从右向左的层的数字
// 执行用时：1 ms, 在所有 Java 提交中击败了98.52%的用户
// 内存消耗：38.7 MB, 在所有 Java 提交中击败了46.35%的用户
class Solution103_Stack {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            ans.add(new ArrayList<>());
            if(level % 2 == 0) {
                for(int i = 0; i < levelSize; i++) {
                    TreeNode node = queue.poll();
                    ans.get(level).add(node.val);
                    if(node.left != null) { queue.add(node.left); }
                    if(node.right != null) { queue.add(node.right); }
                }
            } else {
                Stack<Integer> stack = new Stack<>();
                for(int i = 0; i < levelSize; i++) {
                    TreeNode node = queue.poll();
                    stack.push(node.val);
                    if(node.left != null) { queue.add(node.left); }
                    if(node.right != null) { queue.add(node.right); }
                }
                while(!stack.isEmpty()) {
                    ans.get(level).add(stack.pop());
                }
            }
            level++;
        }
        return ans;
    }
}
