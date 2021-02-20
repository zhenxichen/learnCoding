import java.util.HashMap;
import java.util.Map;

public class NC12 {
    /* NC 12 重建二叉树 */
    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     */
    public static void main(String[] args) {
        Solution s = new Solution();
        s.reConstructBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{3, 2, 4, 1, 6, 5, 7});
    }
}

class Solution {
    Map<Integer, Integer> map;        // 存储值在pre[]中的位置
    Map<Integer, Integer> map2;        // 存储值在in[]中的位置

    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        int n = pre.length;
        map = new HashMap<>();
        map2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(pre[i], i);
            map2.put(in[i], i);
        }
        return reconstructInternal(pre, in, 0, n - 1);
    }

    public TreeNode reconstructInternal(int[] pre, int[] in,
                                        int inLeft, int inRight) {
        if (inLeft == inRight) {
            return new TreeNode(in[inLeft]);
        }
        if (inLeft > inRight) {
            return null;
        }
        int preIndex = Integer.MAX_VALUE;
        for (int i = inLeft; i <= inRight; i++) {
            preIndex = Math.min(map.get(in[i]), preIndex);
        }
        int rootVal = pre[preIndex];    // 在前序遍历中最早出现的即为root
        TreeNode root = new TreeNode(rootVal);
        int inIndex = map2.get(rootVal);
        root.left = reconstructInternal(pre, in, inLeft, inIndex - 1);
        root.right = reconstructInternal(pre, in, inIndex + 1, inRight);
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}