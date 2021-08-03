import java.util.HashMap;
import java.util.Map;

public class LeetCode662 {
    /* LeetCode 662 二叉树最大宽度 */
    /**
     * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。
     * 这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
     * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
     */

    // 解法一：DFS
    class Solution {

        Map<Integer, Integer> leftSide;     // 用map记录最左侧的节点位置
        int ans;

        public int widthOfBinaryTree(TreeNode root) {
            leftSide = new HashMap<>();
            ans = 0;
            dfs(root, 1, 1);
            return ans;
        }

        private void dfs(TreeNode root, int depth, int pos) {
            if (root == null) {
                return;
            }
            leftSide.putIfAbsent(depth, pos);
            ans = Math.max(ans, pos - leftSide.get(depth) + 1);
            dfs(root.left, depth + 1, 2 * pos);
            dfs(root.right, depth + 1, 2 * pos + 1);
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
