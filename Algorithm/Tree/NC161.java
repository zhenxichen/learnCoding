import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class NC161 {
    /* NC 161 二叉树的中序遍历 */

    // 迭代解
    public class Solution {
        /**
         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
         *
         *
         * @param root TreeNode类
         * @return int整型一维数组
         */
        public int[] inorderTraversal (TreeNode root) {
            // write code here
            List<Integer> ans = new ArrayList<>();
            Deque<TreeNode> stack = new ArrayDeque<>();
            TreeNode curr = root;
            while (curr != null || !stack.isEmpty()) {
                // 将左侧节点全部压入栈中
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
                // 从栈中取出，并获取右侧子树
                curr = stack.pop();
                ans.add(curr.val);
                curr = curr.right;
            }
            return ans.stream().mapToInt(Integer::valueOf).toArray();
        }
    }

    // 递归解
    class RecursiveSolution {
        List<Integer> ans;

        public int[] inorderTraversal (TreeNode root) {
            // write code here
            ans = new ArrayList<>();
            inOrder(root);
            return ans.stream().mapToInt(Integer::valueOf).toArray();
        }

        private void inOrder(TreeNode root) {
            if (root == null) {
                return;
            }
            inOrder(root.left);
            ans.add(root.val);
            inOrder(root.right);
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
}
