package Tree;

public class LeetCode104 {
    /* LeetCode 104 二叉树的最大深度 */
    /**
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3 。
     */
    public static void main(String[] args) {
        Solution104_DFS solution = new Solution104_DFS();
    }
}

class Solution104_DFS {
    public int maxDepth(TreeNode root) {
        return dfs(root, 0);
    }

    int dfs(TreeNode node, int depth) {
        if(node == null) { return depth; }
        int max = depth;
        max = Math.max(max, dfs(node.left, depth + 1));
        max = Math.max(max, dfs(node.right, depth + 1));
        return max;
    }
}
