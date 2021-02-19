public class NC102 {
    /* NC 102 在二叉树中找到两个节点的最近公共祖先 */
    /**
     * 给定一棵二叉树以及这棵树上的两个节点 o1 和 o2，请找到 o1 和 o2 的最近公共祖先节点。
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    /**
     *
     * @param root TreeNode类
     * @param o1 int整型
     * @param o2 int整型
     * @return int整型
     */
    public int lowestCommonAncestor (TreeNode root, int o1, int o2) {
        // write code here
        return dfs(root, o1, o2).val;
    }

    public TreeNode dfs (TreeNode root, int o1, int o2) {
        if (root == null || root.val == o1 || root.val == o2) {
            return root;
        }
        TreeNode left = dfs(root.left, o1, o2);
        TreeNode right = dfs(root.right, o1, o2);
        if (left == null) {    // 若没有结点在左侧，说明最近公共祖先节点在右侧
            return right;
        }
        if (right == null) {    // 同理，左侧没有，则说明公共祖先节点在右侧
            return left;
        }
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left = null;
    TreeNode right = null;
}