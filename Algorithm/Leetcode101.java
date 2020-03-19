import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Leetcode101 {
    /* Leetcode 101 Symmetric Tree */
    /**Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     * Note:
     * Bonus points if you could solve it both recursively and iteratively.
     * */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public static void main(String[] args){
        //解法：同时进行左到右和右到左两个方向的BFS，进行比较
        //Java解运用的是迭代解法，递归解应该会考虑用C++来实现
        Solution s = new Solution();
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){val = x;}
}

class Solution {
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q1 = new LinkedList<>();     //Java中Queue是抽象类, 因此new需要用LinkedList
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.add(root);
        q2.add(root);
        while(!q1.isEmpty() && !q2.isEmpty()){
            TreeNode t1 = q1.poll();
            TreeNode t2 = q2.poll();
            //先处理null的情况
            if(t1 == null && t2 == null){
                continue;
            }
            if(t1 == null || t2 == null){
                return false;
            }
            //剩余情况均有值
            if(t1.val != t2.val){ return false; }
            q1.add(t1.left);                           //t1从左往右BFS，t2从右往左BFS
            q1.add(t1.right);
            q2.add(t2.right);
            q2.add(t2.left);
        }
        return true;
    }
}
