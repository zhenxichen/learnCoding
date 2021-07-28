import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode863 {
    /* LeetCode 863 二叉树中所有距离为 K 的结点 */
    /**
     * 给定一个二叉树（具有根结点root），一个目标结点target，和一个整数值 K 。
     * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
     *
     * 提示：
     * 1. 给定的树是非空的。
     * 2. 树上的每个结点都具有唯一的值0 <= node.val <= 500。
     * 3. 目标结点target是树上的结点。
     * 4. 0 <= K <= 1000.
     */

    public static class Solution {

        Map<Integer, TreeNode> parents;     // 记录所有节点的父节点
        List<Integer> ans;      // 记录最终答案

        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            // 先通过DFS查找到所有节点的父节点，并用哈希表记录
            parents = new HashMap<>();
            findParents(root);

            // 再从target开始进行DFS，找到所有距离为K的点
            ans = new ArrayList<>();
            findAllKDistanceNode(target, null, 0, k);
            return ans;
        }

        private void findParents(TreeNode node) {
            if (node.left != null) {
                parents.put(node.left.val, node);
                findParents(node.left);
            }
            if (node.right != null) {
                parents.put(node.right.val, node);
                findParents(node.right);
            }
        }

        private void findAllKDistanceNode(TreeNode node, TreeNode from, int depth, int k) {
            if (node == null) {
                return;
            }
            if (depth == k) {
                ans.add(node.val);
                return;
            }
            if (node.left != from) {
                findAllKDistanceNode(node.left, node, depth + 1, k);
            }
            if (node.right != from) {
                findAllKDistanceNode(node.right, node, depth + 1, k);
            }
            TreeNode parent = parents.get(node.val);
            if (parent != from) {
                findAllKDistanceNode(parent, node, depth + 1, k);
            }
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
}
