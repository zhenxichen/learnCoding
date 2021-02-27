import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class NC45 {
    /* NC 45 二叉树遍历 */
    /**
     * 分别按照二叉树先序，中序和后序打印所有的节点。
     */
    public static void main(String[] args) {
        Solution_Recursion sr = new Solution_Recursion();       // 递归解
        Solution_Iteration si = new Solution_Iteration();       // 迭代解
    }
}

// 迭代解
class Solution_Iteration {
    /**
     *
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */
    public int[][] threeOrders (TreeNode root) {
        // write code here
        int[][] ans = new int[3][];
        if (root == null) {
            return ans;
        }
        List<Integer> pre = new ArrayList<>();
        preOrder(root, pre);
        ans[0] = pre.stream().mapToInt(Integer::valueOf).toArray();
        List<Integer> in = new ArrayList<>();
        inOrder(root, in);
        ans[1] = in.stream().mapToInt(Integer::valueOf).toArray();
        List<Integer> post = new ArrayList<>();
        postOrder(root, post);
        ans[2] = post.stream().mapToInt(Integer::valueOf).toArray();
        return ans;
    }

    public void preOrder(TreeNode root, List<Integer> list) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public void inOrder(TreeNode root, List<Integer> list) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);       // 不断将左子节点压入栈中
                curr = curr.left;
            }
            // 压栈结束后，逐步弹出并遍历右侧节点
            curr = stack.pop();
            list.add(curr.val);
            curr = curr.right;
        }
    }

    public void postOrder(TreeNode root, List<Integer> list) {
        // 若进行先右后左的前序遍历，顺序为根-右-左
        // 反向后顺序即为左-右-根
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        if (root == null) {
            return;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            stack2.push(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        while (!stack2.isEmpty()) { list.add(stack2.pop()); }
    }
}

// 递归解
class Solution_Recursion {
    /**
     *
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */
    public int[][] threeOrders (TreeNode root) {
        // write code here
        int[][] ans = new int[3][];
        List<Integer> pre = new ArrayList<>();
        preOrder(root, pre);
        ans[0] = pre.stream().mapToInt(Integer::valueOf).toArray();
        List<Integer> in = new ArrayList<>();
        inOrder(root, in);
        ans[1] = in.stream().mapToInt(Integer::valueOf).toArray();
        List<Integer> post = new ArrayList<>();
        postOrder(root, post);
        ans[2] = post.stream().mapToInt(Integer::valueOf).toArray();
        return ans;
    }

    public void preOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    public void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }

    public void postOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postOrder(root.left, list);
        postOrder(root.right, list);
        list.add(root.val);
    }
}

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
}