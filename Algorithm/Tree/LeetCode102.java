package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode102 {
    /* LeetCode 102 二叉树的层序遍历 */
    /**
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     * 示例：
     * 二叉树：[3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     */
    public static void main(String[] args) {
        Solution102_1 solution1 = new Solution102_1();
        Solution102_2 solution = new Solution102_2();
    }
}

// 解法二：优化解法一，一次循环处理一层节点
// 理论上可以优化一定的内存占用
// 执行用时：1 ms, 在所有 Java 提交中击败了93.16%的用户
// 内存消耗：38.7 MB, 在所有 Java 提交中击败了81.04%的用户
// 但是实际上好像优化的效果也不是很明显...
class Solution102_2 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) { return ans; }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            ans.add(new ArrayList<>());
            for(int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                ans.get(level).add(node.val);
                if(node.left != null) { queue.add(node.left); }
                if(node.right != null) { queue.add(node.right); }
            }
            level++;
        }
        return ans;
    }
}

// 解法一：双队列(分别存储Node和层号)
// 执行用时：1 ms, 在所有 Java 提交中击败了93.16%的用户
// 内存消耗：38.7 MB, 在所有 Java 提交中击败了79.61%的用户
// 时间复杂度：O(n)   空间复杂度：O(n)
class Solution102_1 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) { return ans; }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> layers = new LinkedList<>();
        queue.add(root);
        layers.add(0);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            int layer = layers.poll().intValue();
            if(ans.size() < layer + 1) {
                ans.add(new ArrayList<>());
            }
            ans.get(layer).add(node.val);
            if(node.left != null) {
                queue.add(node.left);
                layers.add(layer + 1);
            }
            if(node.right != null) {
                queue.add(node.right);
                layers.add(layer + 1);
            }
        }
        return ans;
    }
}
