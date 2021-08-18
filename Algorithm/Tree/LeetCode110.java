import java.util.HashMap;
import java.util.Map;

public class LeetCode110 {
    /* LeetCode 110 平衡二叉树 */
    /**
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 本题中，一棵高度平衡二叉树定义为：
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
     * 示例 1：
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：true
     */

    // 解法一：自顶向下递归
    // 时间复杂度：O(n^2) 空间复杂度：O(n)
    class Solution1 {
        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            }
            int left = height(root.left);
            int right = height(root.right);
            return Math.abs(left - right) <= 1
                    && isBalanced(root.left) && isBalanced(root.right);
        }

        public int height(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            return 1 + Math.max(leftHeight, rightHeight);
        }
    }

    // 解法一修改：用Map存储计算得到的高度
    // LeetCode上耗时反而更久了...
    class Solution1P {

        private Map<TreeNode, Integer> map;

        public boolean isBalanced(TreeNode root) {
            map = new HashMap<>();
            if (root == null) {
                return true;
            }
            int left = height(root.left);
            int right = height(root.right);
            return Math.abs(left - right) <= 1
                    && isBalanced(root.left) && isBalanced(root.right);
        }

        public int height(TreeNode root) {
            if (root == null) {
                return 0;
            }
            if (map.containsKey(root)) {
                return map.get(root);
            }
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            int height = 1 + Math.max(leftHeight, rightHeight);
            map.putIfAbsent(root, height);
            return height;
        }
    }

    // 解法二：自底向上递归（实质上是剪枝）
    // 时间复杂度：O(n) 空间复杂度：O(n)
    class Solution2 {
        public boolean isBalanced(TreeNode root) {
            return height(root) >= 0;
        }

        private int height(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            // 当发现当前树或子树为非平衡树时，直接返回-1
            if (leftHeight == -1 || rightHeight == -1
                    || Math.abs(leftHeight - rightHeight) > 1) {
                return -1;
            }
            return 1 + Math.max(leftHeight, rightHeight);
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
