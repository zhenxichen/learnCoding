import java.util.Stack;

public class LeetCode98 {
    /**
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * 假设一个二叉搜索树具有如下特征：
     *  节点的左子树只包含小于当前节点的数。
     *  节点的右子树只包含大于当前节点的数。
     *  所有左子树和右子树自身必须也是二叉搜索树。
     */
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        Solution2 solution2 = new Solution2();
        Solution solution = new Solution();
    }
}



// 解法一：递归DFS
class Solution1 {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, null, null);   // 运用null而不用Integer.MIN_VALUE的原因是
            // 如果输入值为Integer.MIN_VALUE或MAX_VALUE则最终结果会出错
    }

    boolean dfs(TreeNode node, Integer lower, Integer higher) {
        if(node == null) {
            return true;
        }
        int val = node.val;
        if(lower != null && val <= lower) {
            return false;
        }
        if(higher != null && val >= higher) {
            return false;
        }
        if(!dfs(node.left, lower, val)) {
            return false;
        }
        if(!dfs(node.right, val, higher)) {
            return false;
        }
        return true;
    }
}

// 解法2：迭代中序遍历
class Solution2 {
    public boolean isValidBST(TreeNode root) {
        // BST的中序遍历应该是升序的
        double last = -Double.MAX_VALUE;    // 用Integer会导致输入为Integer.MIN_VALUE时出错
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()) {
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if(node.val <= last) {
                return false;
            }
            last = node.val;
            node = node.right;
        }
        return true;
    }
}

// 解法3：递归中序遍历
class Solution {
    long last = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if(root == null) {  // 递归基
            return true;
        }
        if(!isValidBST(root.left)){
            return false;
        }
        if(root.val <= last) {
            return false;
        }
        last = root.val;
        if(!isValidBST(root.right)){
            return false;
        }
        return true;
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

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}