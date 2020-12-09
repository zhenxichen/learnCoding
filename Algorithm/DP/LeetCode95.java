import java.util.ArrayList;
import java.util.List;

public class LeetCode95 {
    /* LeetCode 95 不同的二叉搜索树 II */
    /**
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
     * 提示：
     * 0 <= n <= 8
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.generateTrees(3);
    }
}


class Solution {
    List<TreeNode>[][] dp;

    public List<TreeNode> generateTrees(int n) {
        dp = new List[n + 1][n + 1];
        // 因为n会取0，所以需要考虑这种特殊情况
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateTree(1, n);
    }

    List<TreeNode> generateTree(int min, int max) {
        List<TreeNode> ret = new ArrayList<>();
        if(min > max) {
            ret.add(null);
            return ret;
        }
        if (dp[min][max] != null) {
            return dp[min][max];
        }
        if (min == max) {
            ret.add(new TreeNode(min));
            dp[min][min] = ret;
            return ret;
        }
        for (int i = min; i <= max; i++) {
            int rootVal = i;
            List<TreeNode> leftTrees = generateTree(min, rootVal - 1);
            List<TreeNode> rightTrees = generateTree(rootVal + 1, max);
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(rootVal, left, right);
                    ret.add(root);
                }
            }
        }
        dp[min][max] = ret;
        return ret;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}