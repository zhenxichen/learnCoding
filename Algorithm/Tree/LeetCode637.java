import java.util.*;

public class LeetCode637 {
    /* LeetCode 637 二叉树的层平均值 */
    /**
     * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
     * 示例 1：
     * 输入：
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 输出：[3, 14.5, 11]
     * 解释：
     * 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
     */
}

// 解法：层次遍历（BFS）
// 虽然这题没有涉及，但是引申到一个问题：大数据量下如何求平均值？
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Double> ans = new ArrayList<>();
        int curr = 1;
        int count = 0;
        int next = 0;
        if (root == null) {
            return null;
        }
        queue.offer(root);
        double sum = 0;     // 此处用int会超出上限
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            sum += node.val;
            count++;
            if(node.left != null) {
                queue.offer(node.left);
                next ++;
            }
            if(node.right != null) {
                queue.offer(node.right);
                next ++;
            }
            if (count == curr) {
                double avg = (double) sum / count;
                ans.add(avg);
                count = 0;
                sum = 0.0;
                curr = next;
                next = 0;
            }
        }
        return ans;
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
