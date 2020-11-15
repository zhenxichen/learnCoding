package Tree;

import java.util.HashMap;
import java.util.Map;

public class LeetCode105 {
    /* LeetCode 105 从前序与中序遍历序列构造二叉树 */
    /**
     * 根据一棵树的前序遍历与中序遍历构造二叉树。
     * 注意:
     * 你可以假设树中没有重复的元素。
     * 例如，给出
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 返回如下的二叉树：
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Solution {
    Map<Integer, Integer> indexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        indexMap = new HashMap<>();
        int n = preorder.length;
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        TreeNode root = build(preorder, inorder, 0, n-1, 0, n-1);
        return root;
    }

    public TreeNode build(int[] preorder, int[] inorder, int preLeft,
                          int preRight, int inLeft, int inRight) {
        if (preRight < preLeft) {
            return null;
        }
        int rootValue = preorder[preLeft];
        TreeNode root = new TreeNode(rootValue);
        int index = indexMap.get(rootValue);
        int leftSize = index - inLeft;
        root.left = build(preorder, inorder, preLeft + 1, preLeft + leftSize,
                inLeft, index - 1);
        root.right = build(preorder, inorder, preLeft + leftSize + 1,
                preRight, index + 1, inRight);
        return root;
    }
}
